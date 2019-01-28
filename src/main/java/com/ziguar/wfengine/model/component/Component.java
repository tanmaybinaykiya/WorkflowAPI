package com.ziguar.wfengine.model.component;

import java.util.List;

import com.ziguar.wfengine.api.view.CreateComponentView;
import com.ziguar.wfengine.model.workflow.Workflow;

public abstract class Component {
	protected String name;
	protected ComponentType type;	
	
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

	public abstract Component withName(String name);
	public abstract ComponentReturnType execute(Workflow workflow, int index, List<ComponentReturnType> previousResults);

	public static Component fromComponentView(CreateComponentView createComponentView) {
		
		switch (createComponentView.getType()) {
			case COUNT:
				return new CountComponent()
					.withName(createComponentView.getName());					
			case HISTORY:
				return new HistoryComponent()
					.withName(createComponentView.getName());
			case WELCOME:
				return new WelcomeComponent()
					.withName(createComponentView.getName())
					.withParamValue((String)createComponentView.getParams().get("param"));
			default:
				throw new RuntimeException("Invalid component type: "+createComponentView.getType());
		}
	}
}
