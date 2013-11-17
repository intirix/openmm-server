package com.intirix.openmm.server.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;

public abstract class GetQuery
{

	private OpenMMServerRuntime runtime;

	public OpenMMServerRuntime getRuntime()
	{
		return runtime;
	}

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}
	
	
	/**
	 * Execute the query
	 * @param param
	 * @param req
	 * @return
	 * @throws ServletException
	 */
	public abstract Object executeQuery( String param, HttpServletRequest req ) throws ServletException;
}
