package com.intirix.openmm.server.ui.html.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class AdminFoldersBeanFactory implements PageBeanFactory
{
	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final AdminFoldersBean page = new AdminFoldersBean();
		
		try
		{
			final ConfigMidtier configMidtier = runtime.getTechnicalLayer().getConfigMidtier();
			page.setFolders( configMidtier.listRootFolders().toArray( new RootFolder[]{}) );
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}

		return page;
	}

}
