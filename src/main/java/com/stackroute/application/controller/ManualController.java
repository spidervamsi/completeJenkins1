//package com.stackroute.application.controller;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.stackroute.application.messenger.ReportingServiceProducer;
//
//import com.stackroute.application.model.ManualModel;
//import com.stackroute.application.service.ServiceManual;
//
//
//@RestController
//@RequestMapping(value="/jsa/kafka")
//public class ManualController {
//	
//	@Autowired
//	ReportingServiceProducer producer;
//	
//	@Autowired
//	ServiceManual serviceManual;
//	
//	
//	@PostMapping(value="/data")
//	  public @ResponseBody ResponseEntity<ManualModel> postPerson(RequestEntity<ManualModel> request) throws URISyntaxException, IOException  {		
//		ManualModel ManualModel = request.getBody();
//		
//		System.out.println(ManualModel.getPath()+" vamsi bro "+ManualModel.getPid()+" vamsi bro");
//		producer.send(ManualModel);
////		service.setManualModel(ManualModel);
//	    return new ResponseEntity<ManualModel>(ManualModel, HttpStatus.OK);
//	  }
//
//	
//	
//	
//	@GetMapping(value="/data")
//	public @ResponseBody List<ManualModel> getAllRecievedMessage(){
////		String messages = serviceManual.toString();
////		serviceManual.clear();
//		List<ManualModel> ManualModelList = new ArrayList<ManualModel>();
//		ManualModelList=serviceManual.get();
//		
//		return ManualModelList;
//	}
//	
//}