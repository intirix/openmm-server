package com.intirix.openmm.server.api.get;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.api.GetQuery;
import com.intirix.openmm.server.api.MovieListResponse;
import com.intirix.openmm.server.api.beans.MovieDetails;
import com.intirix.openmm.server.mt.OpenMMMidtierException;

public class MoviesListQuery extends GetQuery
{

	@Override
	public Object executeQuery( String param, HttpServletRequest req ) throws ServletException
	{
		final MovieListResponse resp = new MovieListResponse();
		
		try
		{
			resp.setMovies( getRuntime().getApplicationLayer().getMovieApp().listMovieDetails( param ).toArray( new MovieDetails[]{} ) );
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}
		
		return resp;
	}

}
