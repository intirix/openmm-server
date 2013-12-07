package com.intirix.openmm.server.ui.html.pages;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.MovieDetails;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class MoviesListBeanFactory implements PageBeanFactory
{
	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final MoviesListBean page = new MoviesListBean();

		try
		{
			final String prefix = request.getParameter( "prefix" );
			final List< MovieDetails > movies = runtime.getApplicationLayer().getMovieApp().listMovieDetails( prefix );
			page.setMovies( movies.toArray( new MovieDetails[]{} ) );
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}


		return page;
	}

}
