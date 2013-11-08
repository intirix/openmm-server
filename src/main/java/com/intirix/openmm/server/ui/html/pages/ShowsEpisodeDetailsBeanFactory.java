package com.intirix.openmm.server.ui.html.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.ShowApp;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class ShowsEpisodeDetailsBeanFactory implements PageBeanFactory
{

	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}


	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		try
		{
			final ShowsEpisodeDetailsBean page = new ShowsEpisodeDetailsBean();


			final int id = Integer.parseInt( request.getParameter( "epid" ) );
			final ShowApp showApp = runtime.getApplicationLayer().getShowApp();
			final Episode episode = showApp.getEpisode( id );
			page.setSeasonNumber( showApp.getSeason( episode.getSeasonId() ).getNumber() );

			page.setEpisode( episode );

			return page;
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}
	}

}
