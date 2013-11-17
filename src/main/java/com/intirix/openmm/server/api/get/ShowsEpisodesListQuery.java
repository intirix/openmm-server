package com.intirix.openmm.server.api.get;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.api.GetQuery;
import com.intirix.openmm.server.api.ShowEpisodesListResponse;
import com.intirix.openmm.server.api.beans.EpisodeDetails;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.ShowApp;

public class ShowsEpisodesListQuery extends GetQuery
{

	@Override
	public Object executeQuery( String param, HttpServletRequest req ) throws ServletException
	{
		final ShowEpisodesListResponse resp = new ShowEpisodesListResponse();
		final int seasonId = Integer.parseInt( param );
		final ShowApp showApp = getRuntime().getApplicationLayer().getShowApp();
		try
		{
			final Season season = showApp.getSeason( seasonId );
			final Show show = showApp.getShow( season.getShowId() );
			final List< EpisodeDetails > episodes = showApp.listEpisodeDetails( seasonId );

			resp.setShow( show );
			resp.setSeason( season );
			resp.setEpisodes( episodes.toArray( new EpisodeDetails[]{} ) );
			return resp;
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}
	}

}
