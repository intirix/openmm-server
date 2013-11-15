package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.mt.app.ShowApp;

public class AssignShowFileAction extends PostAction
{

	private final Log log = LogFactory.getLog( AssignShowFileAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest request ) throws ServletException
	{
		final PostActionResult res = new PostActionResult();

		final int showId = Integer.parseInt( request.getParameter( "show" ) );
		final int season = Integer.parseInt( request.getParameter( "season" ) );
		final int ep = Integer.parseInt( request.getParameter( "ep" ) );


		try
		{
			final ShowApp showApp = getRuntime().getApplicationLayer().getShowApp();
			final Episode episode = showApp.getEpisode( showId, season, ep );

			final String file = request.getParameter( "path" ) + '/' + request.getParameter( "filename" );
			final long size = getRuntime().getVFSBrowser().getFileLength( file );


			showApp.assignFile( episode.getId(), file, size );
			res.setActionMessage( "Successfully assigned file" );
		}
		catch ( Exception e )
		{
			res.setActionMessage( "Failed to assign file: " + e.toString() );
			log.warn( "Failed to assign file", e );
		}
		return res;
	}

}
