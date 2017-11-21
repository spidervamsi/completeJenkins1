package com.stackroute.application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.stackroute.application.exception.ModelNotFoundException;
import com.stackroute.application.exception.ModelVariableNotFoundException;
import com.stackroute.application.model.ManualModel;
import com.stackroute.application.service.ServiceManual;



@SpringBootApplication
//@EnableDiscoveryClient
public class Application {
	
	public static void main(String[] args) throws ModelNotFoundException, ModelVariableNotFoundException, URISyntaxException, IOException, InterruptedException {
		System.out.print("************manual triger running ....");
		SpringApplication.run(Application.class, args);

        
	}
	

	
	
	
	
	
	
	
	
	
}
