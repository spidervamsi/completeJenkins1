package com.stackroute.application.messenger;


import org.springframework.stereotype.Service;

import com.stackroute.application.exception.ModelNotFoundException;
import com.stackroute.application.exception.ModelVariableNotFoundException;
import com.stackroute.application.model.ManualModel;
import com.stackroute.application.service.ServiceManual;

import java.io.IOException;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;


//This consumer consumes data from kafaka.
//That data  includes the project-id(pid),git or svn url(url),timestamp(timestamp)

@Service
public class ReportingServiceConsumer {
	
	@Autowired
	ServiceManual serviceManual; //service
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@KafkaListener(topics="${spring.kafka.consumer.group-id}")
	public void reportlistener(ManualModel data) throws ModelNotFoundException, ModelVariableNotFoundException, URISyntaxException, IOException, InterruptedException {
		

		
	System.out.println(data.getProjectId());
	System.out.println(data.getRepoUrl());
	System.out.println(data.getClonedPath());
	System.out.println(data.getTimeStamp());
//		System.out.println(data + ": " + data.getProjectId() + " :" + data.getClonedPath());
//		puts in the data takens from kafka into the service (serviceManual)
		 try {
			 serviceManual.put(data);
			  }
			  catch(ModelNotFoundException me) {				
				  logger.warn(me.getMessage());
			  }
			  catch(ModelVariableNotFoundException me) {				 
				  logger.warn(me.getMessage());
			  }
		

	}
}