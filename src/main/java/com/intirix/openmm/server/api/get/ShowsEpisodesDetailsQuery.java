package com.intirix.openmm.server.api.get;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.api.GetQuery;
import com.intirix.openmm.server.mt.OpenMMMidtierException;

public class ShowsEpisodesDetailsQuery extends GetQuery
{

	@Override
	public Object executeQuery( String param, HttpServletRequest req ) throws ServletException
	{
		final int epid = Integer.parseInt( param );
		try
		{
			return getRuntime().getApplicationLayer().getShowApp().getEpisodeDetails( epid );
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}
	}

}
