package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.mt.app.MovieApp;
import com.intirix.openmm.server.vfs.FileSystemBrowser;

public class AssignMovieFileAction extends PostAction
{

	private final Log log = LogFactory.getLog( AssignMovieFileAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest request ) throws ServletException
	{
		final PostActionResult res = new PostActionResult();

		final int movieId = Integer.parseInt( request.getParameter( "movieId" ) );


		try
		{
			final FileSystemBrowser browser = getRuntime().getApplicationLayer().getVfsApp().getBrowser();
			
			final MovieApp movieApp = getRuntime().getApplicationLayer().getMovieApp();

			final String file = request.getParameter( "path" ) + '/' + request.getParameter( "filename" );
			final long size = browser.getFileLength( file );


			movieApp.assignFile( movieId, file, size );
			res.setActionMessage( "${ui.actions.assignMovieFile.success}" );
		}
		catch ( Exception e )
		{
			res.setActionMessage( "${ui.actions.assignMovieFile.failed}: " + e.toString() );
			log.warn( "Failed to assign file", e );
		}
		return res;
	}

}
