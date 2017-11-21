package com.stackroute.application.model;

import org.springframework.stereotype.Component;

@Component
public class ProduceLiveStatusModel {
	
	private String projectId; //project-id
	private String buildStatus; //status of the build
	private String buildPercentage;
	private String consoleOutput;
	
	
public ProduceLiveStatusModel(String projectId, String buildStatus, String buildPercentage,String consoleOutput) {
		
		this.projectId = projectId;
		this.buildStatus = buildStatus;
		this.buildPercentage = buildPercentage;
		this.consoleOutput=consoleOutput;
	}

public ProduceLiveStatusModel() {
	
}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getBuildStatus() {
		return buildStatus;
	}
	public void setBuildStatus(String buildStatus) {
		this.buildStatus = buildStatus;
	}
	public String getBuildPercentage() {
		return buildPercentage;
	}
	public void setBuildPercentage(String buildPercentage) {
		this.buildPercentage = buildPercentage;
	}
	public String getConsoleOutput() {
		return consoleOutput;
	}
	public void setConsoleOutput(String consoleOutput) {
		this.consoleOutput = consoleOutput;
	}
	

}
