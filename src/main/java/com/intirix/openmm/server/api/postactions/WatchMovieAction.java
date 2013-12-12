package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.mt.app.MovieApp;

public class WatchMovieAction extends PostAction
{

	private final Log log = LogFactory.getLog( WatchMovieAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest request ) throws ServletException
	{
		final PostActionResult res = new PostActionResult();

		final int movieId = Integer.parseInt( request.getParameter( "movieId" ) );


		try
		{
			final MovieApp movieApp = getRuntime().getApplicationLayer().getMovieApp();
			movieApp.watchMovie( movieId );
			res.setActionMessage( "Successfully watched movie" );
		}
		catch ( Exception e )
		{
			res.setActionMessage( "Failed to watch episode: " + e.toString() );
			log.warn( "Failed to watch movie", e );
		}
		return res;
	}

}
