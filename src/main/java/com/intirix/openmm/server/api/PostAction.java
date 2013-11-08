package com.intirix.openmm.server.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;

public abstract class PostAction
{
	
	protected OpenMMServerRuntime runtime;
	
	

	public OpenMMServerRuntime getRuntime()
	{
		return runtime;
	}



	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}



	/**
	 * Process an action
	 * @param req
	 * @return arbitrary response object
	 * @throws ServletException
	 */
	public abstract PostActionResult processAction( HttpServletRequest req ) throws ServletException;
}
