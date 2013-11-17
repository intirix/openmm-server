package com.intirix.openmm.server.api.get;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.api.GetQuery;
import com.intirix.openmm.server.api.ShowSeasonListResponse;
import com.intirix.openmm.server.api.beans.SeasonDetails;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;

public class ShowsSeasonsListQuery extends GetQuery
{

	@Override
	public Object executeQuery( String param, HttpServletRequest req ) throws ServletException
	{
		final ShowSeasonListResponse resp = new ShowSeasonListResponse();
		final int showId = Integer.parseInt( param );
		try
		{
			final Show show = getRuntime().getApplicationLayer().getShowApp().getShow( showId );
			resp.setShow( show );
			
			final List< SeasonDetails > seasons = getRuntime().getApplicationLayer().getShowApp().listSeasonDetails( showId );
			resp.setSeasons( seasons.toArray( new SeasonDetails[]{} ) );
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}
		return resp;
	}

}
