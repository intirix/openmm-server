package com.intirix.openmm.server.api.get;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.api.GetQuery;
import com.intirix.openmm.server.api.MovieDetailsResponse;

public class MoviesGetQuery extends GetQuery
{

	@Override
	public Object executeQuery( String param, HttpServletRequest req ) throws ServletException
	{
		final MovieDetailsResponse resp = new MovieDetailsResponse();
		
		try
		{
			final int movieId = Integer.parseInt( param );
			resp.setMovie( getRuntime().getApplicationLayer().getMovieApp().getMovieById( movieId ) );
		}
		catch ( Exception e )
		{
			throw new ServletException( e );
		}
		
		return resp;
	}

}
