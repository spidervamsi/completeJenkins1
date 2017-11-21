package com.stackroute.application.model;

import static org.junit.Assert.*;

//import java.io.String;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class ManualModelTest {

	@Test
	public void testUrl() {
		ManualModel ManualModel = new ManualModel();
		
		
		ManualModel.setRepoUrl("www.github.com");
		String actual = ManualModel.getRepoUrl();
		
		assertNotNull(actual);
		assertEquals("www.github.com", actual);
	}
	
	@Test
	public void testPid() {
		ManualModel ManualModel = new ManualModel();
		
		ManualModel.setProjectId("2149");
		String actual = ManualModel.getProjectId();
		
		assertNotNull(actual);
		assertEquals("2149", actual);
	}
	
	@Test
	public void testPath() {
		ManualModel ManualModel = new ManualModel();
		
		ManualModel.setClonedPath("path");
		String actual = ManualModel.getClonedPath();
		
		assertNotNull(actual);
		assertEquals(new String("path"), actual);
	}
	
	@Test
	public void testSpan() {
		ManualModel ManualModel = new ManualModel();
		
		ManualModel.setTimeStamp(null);
		String actual = ManualModel.getTimeStamp();
		
		assertNull(actual);
		assertEquals(null, actual);
	}
	
	
	
	
}
