package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.mt.app.ShowApp;

public class WatchShowEpisodeAction extends PostAction
{

	private final Log log = LogFactory.getLog( WatchShowEpisodeAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest request ) throws ServletException
	{
		final PostActionResult res = new PostActionResult();

		final int ep = Integer.parseInt( request.getParameter( "ep" ) );


		try
		{
			final ShowApp showApp = getRuntime().getApplicationLayer().getShowApp();

			showApp.watchEpisode( ep );
			res.setActionMessage( "Successfully watched episode" );
		}
		catch ( Exception e )
		{
			res.setActionMessage( "Failed to watch episode: " + e.toString() );
			log.warn( "Failed to watch episode", e );
		}
		return res;
	}

}
