package com.stackroute.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.offbytwo.jenkins.JenkinsServer;
import com.stackroute.application.exception.ModelNotFoundException;
import com.stackroute.application.exception.ModelVariableNotFoundException;
import com.stackroute.application.model.ManualModel;

public class ServiceTest {
	
	@Autowired
	ServiceManual service; //service
//	@Test
//	public void testManualModelService() throws ModelNotFoundException, ModelVariableNotFoundException, URISyntaxException, IOException {
////		 ServiceManual service=new ServiceManual();
//		 ManualModel manual=new ManualModel();
//		 manual.setCloned_path("path");
//		 manual.setProjectID("pid");
//		 manual.setRepo_url("url");
//		 manual.setTimeStamp(null);
//		service.put(manual);
//		assertNotNull(manual);
//		assertEquals(manual, service.getManualModel().get(0));		
//
//	}
	
	@Test
	public void testJenkinsServer() throws URISyntaxException {
		JenkinsServer jenkins = new JenkinsServer(new URI("http://localhost:8080"), "vamsi", "vamsi123");

		assertNotNull(jenkins);
	}
	

}
