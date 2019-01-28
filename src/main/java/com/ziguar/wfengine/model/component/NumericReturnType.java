package com.ziguar.wfengine.model.component;

public class NumericReturnType extends ComponentReturnType {

	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setCount(Integer value) {
		this.value = value;
	}
	
	public NumericReturnType withCount(Integer value) {
		this.value = value;
		return this;
	}
	
}
