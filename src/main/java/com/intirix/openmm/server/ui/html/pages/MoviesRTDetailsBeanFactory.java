package com.intirix.openmm.server.ui.html.pages;

import it.jtomato.gson.Movie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class MoviesRTDetailsBeanFactory implements PageBeanFactory
{

	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}


	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final MoviesRTDetailsBean page = new MoviesRTDetailsBean();


		final String id = request.getParameter( "id" );
		page.setId( id );
		final Movie movie = runtime.getTechnicalLayer().getRtMidtier().getMovieById( id );
		
		if ( movie.posters.original != null )
		{
			page.setBanner( runtime.getApplicationLayer().getWebCacheApp().getWebCacheUrl( movie.posters.original ) );
		}

		//page.setBanner( series.getBanner() );
		page.setDescription( movie.synopsis );
		page.setName( movie.title );
		page.setRating( "" + movie.rating.audienceScore );
		page.setMpaaRating( movie.mpaaRating );

		return page;
	}

}
