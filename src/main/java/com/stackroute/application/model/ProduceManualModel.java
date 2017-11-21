package com.stackroute.application.model;

import org.springframework.stereotype.Component;

@Component
public class ProduceManualModel {
	private String projectId; //project-id
	private String buildStatus; //status of the build
	private String consoleOutput; //status of the build
	public ProduceManualModel() {
		
	}

	public ProduceManualModel ( String projectId,String buildStatus,String consoleOutput) {
		
		this.projectId = projectId;
		this.buildStatus = buildStatus;
		this.consoleOutput=consoleOutput;
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
	public String getConsoleOutput() {
		return consoleOutput;
	}

	public void setConsoleOutput(String consoleOutput) {
		this.consoleOutput = consoleOutput;
	}
	

}
