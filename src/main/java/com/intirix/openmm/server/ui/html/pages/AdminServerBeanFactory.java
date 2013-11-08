package com.intirix.openmm.server.ui.html.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class AdminServerBeanFactory implements PageBeanFactory
{
	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final AdminServerBean page = new AdminServerBean();
		
		page.setHttpPort( runtime.getConfig().getHttpPort() );
		page.setTvdbKey( runtime.getConfig().getTvdbKey() );

		return page;
	}

}
