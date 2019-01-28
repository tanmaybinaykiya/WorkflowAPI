package com.ziguar.wfengine.api.impl;

import javax.xml.bind.ValidationException;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.ziguar.wfengine.api.WorkflowAPI;
import com.ziguar.wfengine.api.view.CreateWorkflowView;
import com.ziguar.wfengine.dao.WorkflowDAO;
import com.ziguar.wfengine.model.component.ComponentReturnType;
import com.ziguar.wfengine.model.workflow.Workflow;

public class WorkflowAPIImpl implements WorkflowAPI {
	
	public String createWorkflow(CreateWorkflowView createWorkflowView){
		try {
			return WorkflowDAO.persist(Workflow.fromWorkFlowView(createWorkflowView));
		} catch (ValidationException e) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		} catch(Exception e){
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	public ComponentReturnType execute(String workflowGuid) { 
		try{
			List<ComponentReturnType> results = WorkflowDAO.get(workflowGuid).execute();
			return results.get(results.size()-1);
		} catch(Exception e){
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
