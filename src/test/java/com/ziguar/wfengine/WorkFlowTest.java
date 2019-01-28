package com.ziguar.wfengine;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ValidationException;

import org.junit.Assert;
import org.junit.Test;

import com.ziguar.wfengine.api.view.CreateWorkflowView;
import com.ziguar.wfengine.dao.ComponentDAO;
import com.ziguar.wfengine.model.component.Component;
import com.ziguar.wfengine.model.component.ComponentReturnType;
import com.ziguar.wfengine.model.component.CountComponent;
import com.ziguar.wfengine.model.component.HistoryComponent;
import com.ziguar.wfengine.model.component.NumericReturnType;
import com.ziguar.wfengine.model.component.StringReturnType;
import com.ziguar.wfengine.model.component.WelcomeComponent;
import com.ziguar.wfengine.model.workflow.WorkFlowType;
import com.ziguar.wfengine.model.workflow.Workflow;

public class WorkFlowTest {

	@Test
	public void execute_test() {
		List<Component> components = new ArrayList<Component>();
		components.add(new WelcomeComponent().withName("Welcome Component").withParamValue("Lorem Ipsum"));
		components.add(new HistoryComponent().withName("History Component"));
		components.add(new CountComponent().withName("Count Component"));
		Workflow workflow = new Workflow().withName("Workflow")
				.withType(WorkFlowType.LOOK_UP)
				.withComponents(components);
		List<ComponentReturnType> result = workflow.execute();
		Assert.assertEquals(result.size(), 3);
		Assert.assertEquals(((StringReturnType) result.get(0)).getValue(), "Hello Lorem Ipsum");
		Assert.assertEquals(((StringReturnType) result.get(1)).getValue(), "Welcome Component");
		Assert.assertEquals(((NumericReturnType) result.get(2)).getValue().intValue(), 2);
	}

	@Test
	public void topSort_test() throws ValidationException {
		
		ComponentDAO.persist("1", new CountComponent().withName("Count Component 1"));
		ComponentDAO.persist("2", new CountComponent().withName("Count Component 2"));
		ComponentDAO.persist("3", new CountComponent().withName("Count Component 3"));
		ComponentDAO.persist("4", new CountComponent().withName("Count Component 4"));
		ComponentDAO.persist("5", new CountComponent().withName("Count Component 5"));
		ComponentDAO.persist("6", new CountComponent().withName("Count Component 6"));
		
		List<Map.Entry<String, String>> connections = new ArrayList<>();
		connections.add(new AbstractMap.SimpleEntry<String, String>("1", "2"));
		connections.add(new AbstractMap.SimpleEntry<String, String>("1", "3"));
		connections.add(new AbstractMap.SimpleEntry<String, String>("1", "4"));
		connections.add(new AbstractMap.SimpleEntry<String, String>("4", "2"));
		connections.add(new AbstractMap.SimpleEntry<String, String>("3", "5"));
		connections.add(new AbstractMap.SimpleEntry<String, String>("4", "6"));
		
		CreateWorkflowView view = new CreateWorkflowView()
				.withName("Workflow")
				.withType(WorkFlowType.LOOK_UP)
				.withConnections(connections);
		Workflow workflow = Workflow.fromWorkFlowView(view);
		Assert.assertEquals(workflow.getComponents().size(), 6);
		Assert.assertEquals(workflow.getComponents().get(0).getName(), "Count Component 1");
		Assert.assertEquals(workflow.getComponents().get(1).getName(), "Count Component 4");
		Assert.assertEquals(workflow.getComponents().get(2).getName(), "Count Component 6");
		Assert.assertEquals(workflow.getComponents().get(3).getName(), "Count Component 3");
		Assert.assertEquals(workflow.getComponents().get(4).getName(), "Count Component 5");
		Assert.assertEquals(workflow.getComponents().get(5).getName(), "Count Component 2");
		
	}
	
}
