//package com.stackroute.application.service;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.stackroute.application.exception.InternalRepositoryException;
//import com.stackroute.application.exception.UrlNotRepositoryException;
//import com.stackroute.application.model.RepositoryDetails;
//import com.stackroute.application.service.SvnRepositoryServiceImpl;
//
//@SpringBootTest
//public class SvnRepositoryServiceImplTest {
//	/*
//	test that checkUrlValidity function returns true when repository url is correct
//	*/
//	@Test
//	public void testCheckUrlValidityTrue() throws UrlNotRepositoryException {
//		SvnRepositoryServiceImpl _svnservice = new SvnRepositoryServiceImpl();
//		
//		boolean actual = _svnservice.checkUrlValidity("https://github.com/bigfoot31/SmoldynHowto");
//		
//		assertTrue(actual);
//	}
//	
//	
//	
//	/*
//	test that checkUrlValidity function throws an error message when repository url is incorrect 
//	*/
//	@Test
//	public void testCheckUrlValidityFalse() {
//		SvnRepositoryServiceImpl _svnservice = new SvnRepositoryServiceImpl();
//		
//		String actual;
//		try {
//			_svnservice.checkUrlValidity("https://github.com/bigfoot31");
//		} catch (UrlNotRepositoryException e) {
//			actual = e.getMessage();
//			assertEquals("This url does not point to a repository.", actual);
//		}
//	}
//	
//	
//	
//	/*
//	test that checkPathValidity function returns true when folderpath is correct
//	*/
//	@Test
//	public void testCheckPathValidityTrue() {
//		SvnRepositoryServiceImpl _svnservice = new SvnRepositoryServiceImpl();
//		
//		boolean actual = _svnservice.checkPathValidity("/home");
//		
//		assertTrue(actual);
//	}
//
//	
//	
//	/*
//	test that checkPathValidity function returns false when folderpath is incorrect
//	*/
//	@Test
//	public void testCheckPathValidityFalse() {
//		SvnRepositoryServiceImpl _svnservice = new SvnRepositoryServiceImpl();
//		
//		boolean actual = _svnservice.checkPathValidity("/stackroute");
//		
//		assertFalse(actual);
//	}
//	
//	
//	
//	
//	/*
//	test that cloneRepository function returns true when repository url and folderpath is correct
//	*/
//	@Test
//	public void testClonerepositoryTrue() {	
//		SvnRepositoryServiceImpl _svnservice = new SvnRepositoryServiceImpl();
//		RepositoryDetails repositorydetails = new RepositoryDetails();
//		
//		repositorydetails.setUrl("https://github.com/bigfoot31/SmoldynHowto");
//		repositorydetails.setFolderpath("~/Desktop");
//		
//		try {
//			boolean actual = _svnservice.cloneRepository(repositorydetails);
//			assertTrue(actual);
//		} catch(InternalRepositoryException e) {
//		}
//	}
//}
