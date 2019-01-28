package com.ziguar.wfengine.api.impl;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.ziguar.wfengine.api.ComponentAPI;
import com.ziguar.wfengine.api.view.CreateComponentView;
import com.ziguar.wfengine.dao.ComponentDAO;
import com.ziguar.wfengine.model.component.Component;

public class ComponentAPIImpl implements ComponentAPI {

	public String createComponent(CreateComponentView createComponentView) {
		try{
			return ComponentDAO.persist(Component.fromComponentView(createComponentView));
		} catch(Exception e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

}
