package com.intirix.openmm.server.api.get;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.api.GetQuery;
import com.intirix.openmm.server.api.MoviePrefixListResponse;
import com.intirix.openmm.server.api.beans.MoviePrefixCounts;
import com.intirix.openmm.server.mt.OpenMMMidtierException;

public class MoviesPrefixListQuery extends GetQuery
{

	@Override
	public Object executeQuery( String param, HttpServletRequest req ) throws ServletException
	{
		final MoviePrefixListResponse resp = new MoviePrefixListResponse();
		
		try
		{
			resp.setPrefixes( getRuntime().getApplicationLayer().getMovieApp().listMoviePrefixes().toArray( new MoviePrefixCounts[]{} ) );
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}
		
		return resp;
	}

}
