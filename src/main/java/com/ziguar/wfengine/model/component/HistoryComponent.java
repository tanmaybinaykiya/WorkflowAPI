package com.ziguar.wfengine.model.component;

import java.util.List;

import com.ziguar.wfengine.model.workflow.Workflow;

public class HistoryComponent extends Component {
	
	public HistoryComponent(){
		type = ComponentType.HISTORY;
	}
	
	public HistoryComponent withName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public ComponentReturnType execute(Workflow workflow, int index, List<ComponentReturnType> previousResults) {
		String value = workflow.getComponents().get(index-1).getName();
		System.out.println(String.format("Previous: %s", value));
		return new StringReturnType().withValue(value);
	}

}
