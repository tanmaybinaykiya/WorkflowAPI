package com.ziguar.wfengine.api.view;

import java.util.List;
import java.util.Map;

import com.ziguar.wfengine.model.workflow.WorkFlowType;

public class CreateWorkflowView {

	private String name;
	private WorkFlowType type;
	private List<Map.Entry<String, String>> connections;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public WorkFlowType getType() {
		return type;
	}
	
	public void setType(WorkFlowType type) {
		this.type = type;
	}
	
	public List<Map.Entry<String, String>> getConnections() {
		return connections;
	}
	
	public void setConnections(List<Map.Entry<String, String>> connections) {
		this.connections = connections;
	}

	public CreateWorkflowView withName(String name) {
		this.name = name;
		return this;
	}
	
	public CreateWorkflowView withType(WorkFlowType type) {
		this.type = type;
		return this;
	}
	
	public CreateWorkflowView withConnections(List<Map.Entry<String, String>> connections) {
		this.connections = connections;
		return this;
	}

}
