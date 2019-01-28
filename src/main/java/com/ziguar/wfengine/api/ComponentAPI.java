package com.ziguar.wfengine.api;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.ziguar.wfengine.api.view.CreateComponentView;

@Path("/api/v1/component")
public interface ComponentAPI {
	
	@PUT
	public String createComponent(CreateComponentView createComponent);
	
}
