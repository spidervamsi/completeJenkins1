package com.stackroute.application.messenger;


import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stackroute.application.exception.ModelNotFoundException;
import com.stackroute.application.exception.ModelVariableNotFoundException;
import com.stackroute.application.model.ManualModel;
import com.stackroute.application.model.ProduceLiveStatusModel;
import com.stackroute.application.model.ProduceManualModel;
import com.stackroute.application.service.ServiceManual;

import org.springframework.kafka.core.KafkaTemplate;


@Service
public class ReportingServiceProducer {
	
	
	@Autowired
	private KafkaTemplate<String, ProduceManualModel> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, ProduceLiveStatusModel> kafkaTemplate1;
	
	@Value("${kafka.topic.bootnew}")
	private String groupid;
	 	
	@Value("${kafka.topic.bootnew.live}")
	private String groupidLive;
//	Gives the status of the project to kafka.
//	That status includes build status till now
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void sendMessage(ProduceManualModel report){

//	    produces data object of produceManualModel(status of the project)
//	    into kafka through kafkaTemplate.send();
		
		
		System.out.println("\nthis is in producer "+report.getBuildStatus());
		System.out.println("\nthis is in producer "+report.getProjectId());
		System.out.println("\nthis is groupid "+groupid);
	    try {
	    	System.out.println("sending........");
	    kafkaTemplate.send(groupid,report);
	    
	    }
	    catch(Exception me) {        
			  logger.warn(me.getMessage()); 
		  }
	}
    
	public void sendLiveMessage(ProduceLiveStatusModel report){

//	    produces data object of produceManualModel(status of the project)
//	    into kafka through kafkaTemplate.send();
		
		
		
	    try {
	    	System.out.println("sending live........");
	    	System.out.println(report.getBuildPercentage());
	    kafkaTemplate1.send(groupidLive,report);
	    
	    }
	    catch(Exception me) {        
			  logger.warn(me.getMessage()); 
		  }
	}
	
	
}
