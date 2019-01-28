package com.ziguar.wfengine.model.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ziguar.wfengine.api.view.CreateWorkflowView;
import com.ziguar.wfengine.dao.ComponentDAO;
import com.ziguar.wfengine.model.component.Component;
import com.ziguar.wfengine.model.component.ComponentReturnType;

public class Workflow {
	private String name;
	private WorkFlowType type;
	private List<Component> components;
	private static Logger logger = LogManager.getLogger(Workflow.class);
	
	public String getName() {
		return name;
	}

	public Workflow withName(String name) {
		this.name = name;
		return this;
	}

	public WorkFlowType getType() {
		return type;
	}

	public Workflow withType(WorkFlowType type) {
		this.type = type;
		return this;
	}

	public List<Component> getComponents() {
		return components;
	}

	public Workflow withComponents(List<Component> components) {
		this.components = components;
		return this;
	}

	public static Workflow fromWorkFlowView(CreateWorkflowView view) throws ValidationException {
		List<Map.Entry<String, String>> connections = view.getConnections();			
		List<Component> components = Workflow.getTopologicallySortedComponents(connections);
		return new Workflow()
				.withName(view.getName())
				.withType(view.getType())
				.withComponents(components);
	}
	
//	private static List<Component> getOrderedComponents(List<Entry<String, String>> connections) throws ValidationException {		
//		List<String> componentNames = connections.stream()
//				.map(Map.Entry::getKey)
//				.collect(Collectors.toList());
//		componentNames.add(connections.get(connections.size()-1).getValue());
//		
//		ComponentDAO.exists(componentNames);
//		return componentNames.stream()
//			.map(ComponentDAO::get)
//			.collect(Collectors.toList());
//	}

	public List<ComponentReturnType> execute() {
		List<ComponentReturnType> componentResults = new ArrayList<ComponentReturnType>();
		ListIterator<Component> it = components.listIterator();
		while (it.hasNext()) {
			int index = it.nextIndex();
			Component component = it.next();
			ComponentReturnType result = component.execute(this, index, componentResults);
			componentResults.add(result);
		}
		return componentResults;
	}
	
	private static List<Component> getTopologicallySortedComponents(List<Entry<String, String>> connections) throws ValidationException {
		Set<String> vertices = new HashSet<String>();
		Map<String, List<String>> adjacencyList = new HashMap<String, List<String>>();
		for (Entry<String, String> connection: connections){
			vertices.add(connection.getKey());
			vertices.add(connection.getValue());
			
			List<String> neighbours = adjacencyList.getOrDefault(connection.getKey(), new ArrayList<String>());
			neighbours.add(connection.getValue());
			adjacencyList.put(connection.getKey(), neighbours);
			
			adjacencyList.putIfAbsent(connection.getValue(), new ArrayList<String>());
			
		}
		List<String> componentNames = Workflow.topologicalSort(vertices, adjacencyList);
		ComponentDAO.exists(componentNames);
		return componentNames.stream()
			.map(ComponentDAO::get)
			.collect(Collectors.toList());
	}
	
	private static void topologicalSortRec(String vertex, Set<String> visited, Stack<String> stack, final Map<String, List<String>> adjacencyList) { 
		visited.add(vertex); 
		Iterator<String> it = adjacencyList.get(vertex).iterator(); 
		while (it.hasNext()) { 
			String neighbor = it.next(); 
			if (!visited.contains(neighbor)){ 
				Workflow.topologicalSortRec(neighbor, visited, stack, adjacencyList);
			}
		}
		stack.push(vertex); 
	} 

	private static List<String> topologicalSort(Set<String> vertices, Map<String, List<String>> adjacencyList) {
		logger.info("Adj List: " + adjacencyList);
		List<String> components = new ArrayList<String>();
		Stack<String> stack = new Stack<String>(); 
		Set<String> visited = new HashSet<String>();
		
		for (String vertex: vertices) {
			if (!visited.contains(vertex)){
				Workflow.topologicalSortRec(vertex, visited, stack, adjacencyList);
			}
		}
		while (!stack.empty()) { 
			components.add(stack.pop());
		}
		logger.info("Topologically Sorted Components: " + components);
		return components;
	} 	

}
