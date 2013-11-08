package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.mt.OpenMMMidtierException;

public class DeleteFolderAction extends PostAction
{

	/**
	 * Logger
	 */
	private final Logger log = Logger.getLogger( DeleteFolderAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		final PostActionResult result = new PostActionResult();
		try
		{
			getRuntime().getTechnicalLayer().getConfigMidtier().deleteRootFolder( Integer.parseInt( req.getParameter( "id" ) ) );
			result.setActionMessage( "Successfully deleted folder" );
			result.setRedirectUrl( "/html/adminFolders.html" );
		}
		catch ( OpenMMMidtierException e )
		{
			result.setActionMessage( e.toString() );
			log.error( "Failed to delete folder", e );
		}
		return result;

	}

}
