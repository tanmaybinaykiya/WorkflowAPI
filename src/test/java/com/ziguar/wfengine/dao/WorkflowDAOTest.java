package com.ziguar.wfengine.dao;

import org.junit.Assert;
import org.junit.Test;

import com.ziguar.wfengine.model.workflow.Workflow;

public class WorkflowDAOTest {

	@Test
	public void persistTest() {
		Workflow workflow = new Workflow().withName("Count Component");
		String guid = WorkflowDAO.persist(workflow);
		Assert.assertNotNull(WorkflowDAO.get(guid));
		Assert.assertEquals(workflow, WorkflowDAO.get(guid));
	}

}
