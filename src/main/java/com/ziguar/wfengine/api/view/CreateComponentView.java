package com.ziguar.wfengine.api.view;

import java.util.Map;

import com.ziguar.wfengine.model.component.ComponentType;

public class CreateComponentView {
	
	private String name;
	private ComponentType type;
	private Map<String, String> params;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ComponentType getType() {
		return type;
	}
	
	public void setType(ComponentType type) {
		this.type = type;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
}
