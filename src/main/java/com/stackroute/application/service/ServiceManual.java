package com.stackroute.application.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.BaseModel;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildResult;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueItem;
import com.stackroute.application.exception.ModelNotFoundException;
import com.stackroute.application.exception.ModelVariableNotFoundException;
import com.stackroute.application.messenger.ReportingServiceProducer;
import com.stackroute.application.model.ManualModel;
import com.stackroute.application.model.ProduceLiveStatusModel;
import com.stackroute.application.model.ProduceManualModel;

@Component
@Configuration
public class ServiceManual {
	

	private String jenkinsURL;
	private String jenkinsUserName;
	private String jenkinsPassword;
	@Autowired
	ProduceManualModel produceManualModel;
	@Autowired
	ReportingServiceProducer reportingServiceProducer;
	@Autowired
	ProduceLiveStatusModel produceLiveStatusModel;
	private String jobName;
	
	//we have taken a list of ManualModels.
//	As if the consumer data increases the list will be able to handle it.
private List<ManualModel> storage = new ArrayList<ManualModel>();
	
	public void init_variables() throws IOException {
		// This for loading variables from jenkins_var.properties		
				Properties properties= new Properties();
				
				String resourceName = "jenkins_var.properties"; // could also be a constant
				
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
				
				try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
					properties.load(resourceStream);
				}
				jenkinsURL = properties.getProperty("jenkinsURL");
				jenkinsUserName=properties.getProperty("jenkinsUserName");
				jenkinsPassword=properties.getProperty("jenkinsPassword");
				System.out.println("****" +jenkinsURL+" "+jenkinsUserName+" "+ jenkinsPassword);
		// loading variables ends here 
	}
	
	public void put(ManualModel message) throws ModelNotFoundException,ModelVariableNotFoundException, URISyntaxException, InterruptedException, IOException{

		init_variables();
		
		if(message == null) throw new ModelNotFoundException("Message is empty");
		if(message.getProjectId()==null) throw new ModelVariableNotFoundException("Please enter valid project Id");
		if(message.getClonedPath()==null) throw new ModelVariableNotFoundException("Please enter valid path");
		if(message.getRepoUrl()==null) throw new ModelVariableNotFoundException("Please enter valid url");
//		if(message.getTimeStamp()!=null) throw new ModelVariableNotFoundException("Please ensure that you are sending data without time span");
		
		storage.add(message);//Adds the message to the list
		// sets the project-id of the message in produceManualModel in order to send
		// data to kafaka
//		produceManualModel.setProjectId(message.getProjectId()); 
		
		
		
		//here "vamsi" is the username and "vamsi123" is the password for jenkins server
	JenkinsServer jenkins = new JenkinsServer(new URI(jenkinsURL), jenkinsUserName, jenkinsPassword);
		
	String url=message.getRepoUrl(); //git or svn url comes from here
//	String url="https://github.com/spidervamsi/jenkinsTest";

	
	
	// String config is the xml configuration data read by jenkins server	
	//This is the pipeline config
	String config="<?xml version='1.0' encoding='UTF-8'?>\n" + 
			"<flow-definition plugin=\"workflow-job@2.15\">\n" + 
			"  <actions/>\n" + 
			"  <description></description>\n" + 
			"  <keepDependencies>false</keepDependencies>\n" + 
			"  <properties/>\n" + 
			"  <definition class=\"org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition\" plugin=\"workflow-cps@2.41\">\n" + 
			"    <scm class=\"hudson.plugins.git.GitSCM\" plugin=\"git@3.6.0\">\n" + 
			"      <configVersion>2</configVersion>\n" + 
			"      <userRemoteConfigs>\n" + 
			"        <hudson.plugins.git.UserRemoteConfig>\n" + 
			"          <url>"+url+"</url>\n" + 
			"        </hudson.plugins.git.UserRemoteConfig>\n" + 
			"      </userRemoteConfigs>\n" + 
			"      <branches>\n" + 
			"        <hudson.plugins.git.BranchSpec>\n" + 
			"          <name>*/master</name>\n" + 
			"        </hudson.plugins.git.BranchSpec>\n" + 
			"      </branches>\n" + 
			"      <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>\n" + 
			"      <submoduleCfg class=\"list\"/>\n" + 
			"      <extensions/>\n" + 
			"    </scm>\n" + 
			"    <scriptPath>Jenkinsfile</scriptPath>\n" + 
			"    <lightweight>true</lightweight>\n" + 
			"  </definition>\n" + 
			"  <triggers/>\n" + 
			"  <disabled>false</disabled>\n" + 
			"</flow-definition>";

//{"projectId":"12","clonedPath":"path","repoUrl":"https://github.com/spidervamsi/CGI_jenkinsPipeline"}

		//sets the jobName as "job"+project-id. So that every builds name is different
		String jobName="job"+message.getProjectId();
		this.jobName=jobName;
		
		try {
		jenkins.createJob(jobName, config,true); //creates the job
	    System.out.println(jobName);
          	
		}
		catch(HttpResponseException e) {
			System.out.println("This job already exists "+e);
		}
		finally {
			System.out.println("im in 148\n");
			JobWithDetails job= jenkins.getJob(jobName);
			job.build(true);
			System.out.println("im in 150\n");
		}
//		producer logic starts here
//		TODO place the producer logic in separate method
		produceManualModel.setBuildStatus("build not started");
		produceManualModel.setProjectId(message.getProjectId());
		System.out.println("im in 156\n");
		produceLiveStatusModel.setBuildPercentage("0");//set the initial percentage as zero
		produceLiveStatusModel.setProjectId(message.getProjectId());	
		int buildPreviousNumber=jenkins.getJob(jobName).getLastBuild().getNumber();
		int presentBuildNumber=jenkins.getJob(jobName).getLastBuild().getNumber();
		String buildStatus="build teriminated";//intial build status
		float per=0;//per does the calculations of percentage
		long count=0;
		String percentage="0";//the string format of percentage
		String consoleOutput=null;
		int buildStarted=0;
		while(1>0) {
			Thread.sleep(1000); //waits 1sec before every check
			presentBuildNumber=jenkins.getJob(jobName).getLastBuild().getNumber();
			buildStatus="yet to start";
			consoleOutput="yet to start";
			System.out.println(buildPreviousNumber+" "+presentBuildNumber+"\n");
			Build build=jenkins.getJob(jobName).getLastBuild();
			BuildWithDetails buildWithDetails= build.details();
			if(buildPreviousNumber!=presentBuildNumber||buildStarted==1) {
				count++;
				build=jenkins.getJob(jobName).getLastBuild();
				buildWithDetails= build.details();
				consoleOutput=buildWithDetails.getConsoleOutputText();
				if(buildWithDetails.isBuilding()) {
	        		  System.out.println("build started");
	        		  buildStatus="build started";
	        		  produceLiveStatusModel.setBuildStatus(buildStatus);
	        		  //per calculates the percentage done
System.out.println("this is estimated time "+buildWithDetails.getEstimatedDuration()+"\n");
	        		  per=buildWithDetails.getEstimatedDuration()-count*1000;
	        	         per=per/buildWithDetails.getEstimatedDuration();
	        	         per=1-per;
	        	         if(per>1)
	        	        	 per=(float) 0.999;
	        	         percentage=Float.toString(per*100);        	         
	        	         if(per<0)
	        	        	 percentage="0";	        	         
	        	  }
	        	  else {
	        		 
	        		  if(jenkins.getJob(this.jobName).hasLastSuccessfulBuildRun()) {
	        			  percentage="100";
	        			  System.out.println("build Success");
	        			  buildStatus="build Success";
	        			  
	        		  }else {
	        			  buildStatus="build failed";
	        			  percentage="100";
	        			  System.out.println("build failed");
	        			  buildStatus="build Failed";
	        		  }	  
	        		  //updates the final result into produceManualModel and sends to re
	        		  produceManualModel.setBuildStatus(buildStatus);
	        		  produceManualModel.setConsoleOutput(consoleOutput);
	        		  
	        		  produceLiveStatusModel.setBuildStatus(buildStatus);
	        		  produceLiveStatusModel.setBuildPercentage(percentage);
	        		  produceLiveStatusModel.setConsoleOutput(consoleOutput);
	        		  reportingServiceProducer.sendLiveMessage(produceLiveStatusModel);  
	        		  break;
	        	  }
			}	
			
			if(buildWithDetails.isBuilding()) {
				buildStarted = 1;
			}
			
			
//			updates the result in every run into produceLiveStatusModel and sends to socket through reportingServiceProducer service
			produceLiveStatusModel.setBuildStatus(buildStatus);
			produceLiveStatusModel.setBuildPercentage(percentage);
			produceLiveStatusModel.setConsoleOutput(consoleOutput);
			reportingServiceProducer.sendLiveMessage(produceLiveStatusModel);
		}	
		reportingServiceProducer.sendMessage(produceManualModel);
//		producer logic ends here

		
	}
	
	public List<ManualModel> getManualModel() {
		
		return storage;
	}




}