package com.ziguar.wfengine.dao;

import org.junit.Assert;

import org.junit.Test;

import com.ziguar.wfengine.model.component.Component;
import com.ziguar.wfengine.model.component.CountComponent;
import com.ziguar.wfengine.model.component.HistoryComponent;
import com.ziguar.wfengine.model.component.WelcomeComponent;

public class ComponentDAOTest {

	@Test
	public void persistTest_count() {
		Component component = new CountComponent().withName("Count Component");
		String guid = ComponentDAO.persist(component);
		Assert.assertNotNull(ComponentDAO.get(guid));
		Assert.assertEquals(component, ComponentDAO.get(guid));
	}

	@Test
	public void persistTest_history() {
		Component component = new HistoryComponent().withName("History Component");
		String guid = ComponentDAO.persist(component);
		Assert.assertNotNull(ComponentDAO.get(guid));
		Assert.assertEquals(component, ComponentDAO.get(guid));
	}

	@Test
	public void persistTest_welcome() {
		Component component = new WelcomeComponent().withName("Welcome Component");
		String guid = ComponentDAO.persist(component);
		Assert.assertNotNull(ComponentDAO.get(guid));
		Assert.assertEquals(component, ComponentDAO.get(guid));
	}
}
