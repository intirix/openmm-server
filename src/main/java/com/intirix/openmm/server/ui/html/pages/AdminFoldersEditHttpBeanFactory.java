package com.intirix.openmm.server.ui.html.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class AdminFoldersEditHttpBeanFactory implements PageBeanFactory
{
	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final AdminFoldersEditHttpBean page = new AdminFoldersEditHttpBean();
		
		try
		{
			final ConfigMidtier configMidtier = runtime.getTechnicalLayer().getConfigMidtier();
			page.setFolder( configMidtier.getRootFolder( Integer.parseInt( request.getParameter( "id" ) ) ) );
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}

		return page;
	}

}
