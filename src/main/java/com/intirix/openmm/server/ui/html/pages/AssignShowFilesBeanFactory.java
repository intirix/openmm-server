package com.intirix.openmm.server.ui.html.pages;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.ShowApp;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class AssignShowFilesBeanFactory implements PageBeanFactory
{
	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final AssignShowFilesBean page = new AssignShowFilesBean();
		
		try
		{
			page.setShowId( Integer.parseInt( request.getParameter( "show" ) ) );
			page.setSeasonNumber( Integer.parseInt( request.getParameter( "season" ) ) );
			page.setEpNum( Integer.parseInt( request.getParameter( "ep" ) ) );
		}
		catch ( NumberFormatException e )
		{
			// start ignoring after the first invalid number
		}
		
		try
		{
			// populate the lists if we have enough data
			final ShowApp showApp = runtime.getApplicationLayer().getShowApp();
			page.setShows( showApp.listShows().toArray( new Show[]{} ) );
			if ( page.getShowId() >= 0 )
			{
				page.setSeasons( showApp.listSeasons( page.getShowId() ).toArray( new Season[]{} ) );
				
				if ( page.getSeasonNumber() >= 0 )
				{
					final Season season = showApp.getSeason( page.getShowId(), page.getSeasonNumber() );
					page.setEpisodes( showApp.listEpisodes( season.getId() ).toArray( new Episode[]{} ) );
					
					if ( page.getEpNum() >= 0 )
					{
						page.setSelected( true );
					}
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
