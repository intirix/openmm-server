package com.intirix.openmm.server.ui.html;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;

public interface PageBeanFactory
{
	
	/**
	 * Set the runtime for the factory
	 * @param runtime
	 */
	public void setRuntime( OpenMMServerRuntime runtime );

	/**
	 * Create a page bean
	 * @param request
	 * @param optional result of any POST actions that were performed
	 * @return
	 * @throws ServletException
	 */
	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException;
}
