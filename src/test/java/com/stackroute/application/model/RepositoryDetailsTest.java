//package com.stackroute.application.model;
//
//import static org.junit.Assert.*;
//
//import java.util.Set;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//
//import org.junit.Test;
//
//public class RepositoryDetailsTest {
//	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//	Validator validator = factory.getValidator();
//	
//	@Test
//	public void testUrl() {
//		RepositoryDetails repositorydetails = new RepositoryDetails();
//		
//		repositorydetails.setUrl("www.github.com");
//		String actual = repositorydetails.getUrl();
//		
//		assertNotNull(actual);
//		assertEquals("www.github.com", actual);
//	}
//	
//	
//	@Test
//	public void testFolderPath() {
//		RepositoryDetails repositorydetails = new RepositoryDetails();
//		
//		repositorydetails.setUrl("/home");
//		String actual = repositorydetails.getUrl();
//		
//		assertNotNull(actual);
//		assertEquals("/home", actual);
//	}
//	
//	
//	@Test
//	public void testUrlNull() {
//		String actual = null;
//		RepositoryDetails repositorydetails = new RepositoryDetails();
//		
//		repositorydetails.setUrl(null);
//		repositorydetails.setFolderpath("/home");
//		Set<ConstraintViolation<RepositoryDetails>> violations = validator.validate(repositorydetails);
//		for(ConstraintViolation<RepositoryDetails> violation : violations) {
//			actual = violation.getMessage();
//			System.err.println(actual);
//		}
//		
//		assertEquals("may not be null", actual);
//	}
//	
//	
//	@Test
//	public void testFolderPathNull() {
//		String actual = null;
//		RepositoryDetails repositorydetails = new RepositoryDetails();
//		
//		repositorydetails.setUrl("www.github.com");
//		repositorydetails.setFolderpath(null);
//		Set<ConstraintViolation<RepositoryDetails>> violations = validator.validate(repositorydetails);
//		for(ConstraintViolation<RepositoryDetails> violation : violations) {
//			actual = violation.getMessage();
//		}
//		
//		assertEquals("may not be null", actual);
//	}
//}
