package com.intirix.openmm.server.api.get;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.api.BrowseResponse;
import com.intirix.openmm.server.api.GetQuery;
import com.intirix.openmm.server.api.beans.FileEntry;
import com.intirix.openmm.server.api.beans.FolderEntry;
import com.intirix.openmm.server.vfs.FileSystemBrowser;

public class BrowseQuery extends GetQuery
{

	@Override
	public Object executeQuery( String param, HttpServletRequest req ) throws ServletException
	{
		String path = req.getParameter( "path" );
		if ( path == null || path.length() == 0 )
		{
			path = "/";
		}
		
		final BrowseResponse resp = new BrowseResponse();
		
		resp.setPath( path );
		

		try
		{
			final FileSystemBrowser browser = getRuntime().getApplicationLayer().getVfsApp().getBrowser();
			
			final List< FolderEntry > folders = new ArrayList< FolderEntry >();
			for ( final String folder: browser.listFolders( path ) )
			{
				final FolderEntry entry = new FolderEntry();
				entry.setName( folder );
				folders.add( entry );
			}
			resp.setFolders( folders.toArray( new FolderEntry[]{} ) );
			
			final List< FileEntry > files = new ArrayList< FileEntry >();
			for ( final String file: browser.listFiles( path ) )
			{
				final FileEntry entry = new FileEntry();
				entry.setName( file );
				files.add( entry );
			}
			resp.setFiles( files.toArray( new FileEntry[]{} ) );
		}
		catch ( Exception e )
		{
			throw new ServletException( e );
		}

		
		return resp;
	}

}
