package com.intirix.openmm.server.ui.html.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.FileEntry;
import com.intirix.openmm.server.api.beans.FolderEntry;
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
			final List< FolderEntry > folders = new ArrayList< FolderEntry >();
			for ( final String folder: runtime.getVFSBrowser().listFolders( page.getPath() ) )
			{
				final FolderEntry entry = new FolderEntry();
				entry.setName( folder );
				folders.add( entry );
			}
			page.setFolders( folders.toArray( new FolderEntry[]{} ) );
			
			final List< FileEntry > files = new ArrayList< FileEntry >();
			for ( final String file: runtime.getVFSBrowser().listFiles( page.getPath() ) )
			{
				final FileEntry entry = new FileEntry();
				entry.setName( file );
				files.add( entry );
			}
			page.setFiles( files.toArray( new FileEntry[]{} ) );
		}
		catch ( Exception e )
		{
			throw new ServletException( e );
		}

		return page;
	}

}
