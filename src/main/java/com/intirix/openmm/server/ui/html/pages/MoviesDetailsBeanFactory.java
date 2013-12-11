package com.intirix.openmm.server.ui.html.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class MoviesDetailsBeanFactory implements PageBeanFactory
{
	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final MoviesDetailsBean page = new MoviesDetailsBean();

		try
		{
			final int movieId = Integer.parseInt( request.getParameter( "movieId") );
			page.setMovie( runtime.getApplicationLayer().getMovieApp().getMovieById( movieId ) );
			page.setPrefix( page.getMovie().getName().substring( 0, 1 ) );
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}


		return page;
	}

}
