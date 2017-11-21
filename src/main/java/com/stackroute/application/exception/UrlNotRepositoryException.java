package com.stackroute.application.exception;

public class UrlNotRepositoryException extends Exception {
	static final long serialVersionUID = 43L;
	
	private String errormessage;
	
	public UrlNotRepositoryException(String errormessage) {
		super(errormessage);
		this.errormessage = errormessage;
	}
	
	public String getErrormessage() {
		return errormessage;
	}
}
