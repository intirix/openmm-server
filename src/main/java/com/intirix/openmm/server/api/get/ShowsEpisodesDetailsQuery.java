package com.intirix.openmm.server.api.get;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.api.GetQuery;
import com.intirix.openmm.server.api.ShowEpisodeDetailsResponse;
import com.intirix.openmm.server.api.beans.EpisodeDetails;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.ShowApp;

public class ShowsEpisodesDetailsQuery extends GetQuery
{

	@Override
	public Object executeQuery( String param, HttpServletRequest req ) throws ServletException
	{
		final int epid = Integer.parseInt( param );
		try
		{
			final ShowApp showApp = getRuntime().getApplicationLayer().getShowApp();
			final EpisodeDetails details = showApp.getEpisodeDetails( epid );
			final ShowEpisodeDetailsResponse response = new ShowEpisodeDetailsResponse();
			
			response.setEpisode( details );
			response.setSeason( showApp.getSeason( details.getEpisode().getSeasonId() ) );
			response.setShow( showApp.getShow( response.getSeason().getShowId() ) );
			
			return response;
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}
	}

}
