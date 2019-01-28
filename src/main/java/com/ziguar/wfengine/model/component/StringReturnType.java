package com.ziguar.wfengine.model.component;

public class StringReturnType extends ComponentReturnType {
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public StringReturnType withValue(String value) {
		this.value = value;
		return this;
	}
	
}
