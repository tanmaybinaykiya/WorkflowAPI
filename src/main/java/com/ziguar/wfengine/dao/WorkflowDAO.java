package com.ziguar.wfengine.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ziguar.wfengine.model.workflow.Workflow;

public class WorkflowDAO {

	private static Map<String, Workflow> workflows = new HashMap<String, Workflow>();
	
	public static String persist(Workflow workflow){
		String key = UUID.randomUUID().toString();
		workflows.put(key, workflow);
		return key;
	}

	public static Workflow get(String guid) {
		return workflows.get(guid);
	}
}
