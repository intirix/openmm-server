package com.intirix.openmm.server.ui.html.pages;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class BrowseBeanFactory implements PageBeanFactory
{
	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final BrowseBean page = new BrowseBean();
		final String path = request.getParameter( "path" );
		if ( path.length() > 1 )
		{
			page.setParentPath( new File( path ).getParent() );
			if ( page.getParentPath() == null )
			{
				page.setParentPath( "" );
			}
		}
		page.setPath( path );
		
		try
		{
			page.setFolders( runtime.getVFSBrowser().listFolders( page.getPath() ) );
			page.setFiles( runtime.getVFSBrowser().listFiles( page.getPath() ) );
		}
		catch ( Exception e )
		{
			throw new ServletException( e );
		}

		return page;
	}

}
