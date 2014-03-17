package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.mt.app.ShowApp;

public class AssignShowDirectoryAction extends PostAction
{

	private final Log log = LogFactory.getLog( AssignShowDirectoryAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest request ) throws ServletException
	{
		final PostActionResult res = new PostActionResult();

		final int showId = Integer.parseInt( request.getParameter( "show" ) );
		try
		{
			final ShowApp showApp = getRuntime().getApplicationLayer().getShowApp();
			
			final int count = showApp.assignFilesInDirectory( showId, request.getParameter( "path" ) );
			
			res.setActionMessage( "${ui.actions.assignShowDirectory.success}: " + count );
		}
		catch ( Exception e )
		{
			res.setActionMessage( "${ui.actions.assignShowDirectory.failed}: " + e.toString() );
			log.warn( "Failed to assign directory", e );
		}
		return res;
	}

}
