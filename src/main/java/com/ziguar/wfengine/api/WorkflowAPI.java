package com.ziguar.wfengine.api;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.ziguar.wfengine.api.view.CreateWorkflowView;
import com.ziguar.wfengine.model.component.ComponentReturnType;

@Path("/api/v1/workflow")
public interface WorkflowAPI {
	
	@PUT
	public String createWorkflow(CreateWorkflowView createWorkflowView);
	
	@POST
	@Path("/{workflowGuid}/execute")
	public ComponentReturnType execute(@PathParam("workflowGuid") String workflowGuid);
	
}
