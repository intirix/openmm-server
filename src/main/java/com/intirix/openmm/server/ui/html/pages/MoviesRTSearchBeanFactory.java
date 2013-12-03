package com.intirix.openmm.server.ui.html.pages;

import it.jtomato.gson.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.RTMovieSearchResultBean;

public class MoviesRTSearchBeanFactory implements PageBeanFactory
{

	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}


	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final MoviesRTSearchBean page = new MoviesRTSearchBean();
		final String query = request.getParameter( "query" );

		if ( query != null )
		{
			try
			{
				final List< Movie > results = runtime.getTechnicalLayer().getRtMidtier().searchMovies( query );
				final List< RTMovieSearchResultBean > list = new ArrayList< RTMovieSearchResultBean >( results.size() );
				for ( final Movie movie: results )
				{
					final RTMovieSearchResultBean result = new RTMovieSearchResultBean();
					
					result.setId( movie.id );
					result.setName( movie.title );
					result.setReleaseDate( movie.year );
					
					list.add( result );
				}
				page.setResults( list.toArray( new RTMovieSearchResultBean[]{} ) );
			}
			catch ( NullPointerException e )
			{
				page.setActionMessage( "No Results Found" );
			}
		}

		return page;
	}

}
