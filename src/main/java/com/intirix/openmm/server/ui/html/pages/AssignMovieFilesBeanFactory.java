package com.intirix.openmm.server.ui.html.pages;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.MovieDetails;
import com.intirix.openmm.server.api.beans.MoviePrefixCounts;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.MovieApp;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.vfs.FileSystemBrowser;

public class AssignMovieFilesBeanFactory implements PageBeanFactory
{
	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final AssignMovieFilesBean page = new AssignMovieFilesBean();
		
		try
		{
			page.setMovieId( Integer.parseInt( request.getParameter( "movieId" ) ) );
		}
		catch ( NumberFormatException e )
		{
			// ignore if invalid, it defaults to -1
		}
		
		try
		{
			final String prefix = request.getParameter( "prefix" );
			final MovieApp movieApp = runtime.getApplicationLayer().getMovieApp();
			page.setPrefixes( movieApp.listMoviePrefixes().toArray( new MoviePrefixCounts[]{} ) );
			
			if ( prefix != null && prefix.length() == 1 )
			{
				page.setPrefix( prefix );
				
				page.setMovies( movieApp.listMovieDetails( prefix ).toArray( new MovieDetails[]{} ) );
				
				if ( page.getMovieId() >= 0 )
				{
					page.setMovie( movieApp.getMovieById( page.getMovieId() ) );
					page.setSelected( true );
				}
				
			}
			
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}
		
		
		// the browse part
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
			final FileSystemBrowser browser = runtime.getApplicationLayer().getVfsApp().getBrowser();
			page.setFolders( browser.listFolders( page.getPath() ) );
			page.setFiles( browser.listFiles( page.getPath() ) );
		}
		catch ( Exception e )
		{
			throw new ServletException( e );
		}

		
		
		
		return page;
	}

}
