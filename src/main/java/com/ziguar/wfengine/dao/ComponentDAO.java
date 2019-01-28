package com.ziguar.wfengine.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.ValidationException;

import com.ziguar.wfengine.model.component.Component;

public class ComponentDAO {
	
	private static Map<String, Component> components = new HashMap<String, Component>();

	public static String persist(Component component){
		String key = UUID.randomUUID().toString();
		components.put(key, component);
		return key;
	}
	
	public static String persist(String key, Component component){
		components.put(key, component);
		return key;
	}
	
	public static Component get(String componentGuid){
		return components.get(componentGuid);
	}
	
	public static void exists(List<String> componentGuids) throws ValidationException {
		for (String componentGuid: componentGuids) {
			ComponentDAO.exists(componentGuid);
		}
	}

	public static void exists(String componentGuid) throws ValidationException {
		assert components.containsKey(componentGuid);
	}

}
