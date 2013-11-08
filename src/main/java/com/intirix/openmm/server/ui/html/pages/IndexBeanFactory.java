package com.intirix.openmm.server.ui.html.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class IndexBeanFactory implements PageBeanFactory
{
	

	public void setRuntime( OpenMMServerRuntime runtime )
	{
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		return new IndexBean();
	}

}
