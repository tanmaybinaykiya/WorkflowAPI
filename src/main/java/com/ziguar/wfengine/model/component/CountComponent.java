package com.ziguar.wfengine.model.component;

import java.util.List;

import com.ziguar.wfengine.model.workflow.Workflow;

public class CountComponent extends Component {

	public CountComponent(){
		type = ComponentType.COUNT;
	}
	
	public CountComponent withName(String name) {
		this.name = name;
		return this;
	}
	
	@Override
	public ComponentReturnType execute(Workflow workflow, int index, List<ComponentReturnType> previousResults) {		
		int value = previousResults.size();
		System.out.println(String.format("Count %d", value));
		return new NumericReturnType().withCount(value);
	}

}
