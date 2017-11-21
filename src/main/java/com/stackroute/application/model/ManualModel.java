/*
this String should contain your microservice data model
*/

package com.stackroute.application.model;


//import java.io.String;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/*
javax.validation.constraints must be present in your data model
check link: https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html 
TODO: needed
ile*/
@JsonDeserialize
public class ManualModel {
	private String projectId;
	private String clonedPath;
	private String repoUrl; // git or svn url
	 private String timeStamp; // time stamp when to build the project
	
	 public ManualModel(String projectId, String clonedPath, String repoUrl, String timeStamp) {
			
			this.projectId = projectId;
			this.clonedPath = clonedPath;
			this.repoUrl = repoUrl;
			this.timeStamp = timeStamp;
		}
	 public ManualModel() {
		 
	 }
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getClonedPath() {
		return clonedPath;
	}
	public void setClonedPath(String clonedPath) {
		this.clonedPath = clonedPath;
	}
	public String getRepoUrl() {
		return repoUrl;
	}
	public void setRepoUrl(String repoUrl) {
		this.repoUrl = repoUrl;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
  
	  
}
