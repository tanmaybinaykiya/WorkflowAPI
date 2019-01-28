package com.ziguar.wfengine.model.component;

import java.util.List;

import com.ziguar.wfengine.model.workflow.Workflow;

public class WelcomeComponent extends Component {

	private String paramValue;

	public WelcomeComponent(){
		type = ComponentType.WELCOME;
	}
	
	public WelcomeComponent withName(String name) {
		this.name = name;
		return this;
	}
	
	public WelcomeComponent withParamValue(String value) {
		this.paramValue = value;
		return this;
	}
	
	@Override
	public ComponentReturnType execute(final Workflow workflow, final int index, final List<ComponentReturnType> previousResults) {
		String value = String.format("Hello %s", this.paramValue);
		System.out.println(value);
		return new StringReturnType().withValue(value);
	}

}
