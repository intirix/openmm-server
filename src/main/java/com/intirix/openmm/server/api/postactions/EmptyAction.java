package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;

public class EmptyAction extends PostAction
{


	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		return null;
	}

}
