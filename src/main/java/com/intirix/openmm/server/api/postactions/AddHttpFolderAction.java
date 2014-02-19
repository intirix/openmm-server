package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.beans.HttpRootFolder;

public class AddHttpFolderAction extends PostAction
{

	/**
	 * Logger
	 */
	private final Logger log = Logger.getLogger( AddHttpFolderAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		final PostActionResult result = new PostActionResult();
		final HttpRootFolder folder = new HttpRootFolder();
		folder.setMountPoint( req.getParameter( "mountpoint" ) );
		folder.setUrl( req.getParameter( "url" ) );
		folder.setUsername( req.getParameter( "username" ) );
		folder.setPassword( req.getParameter( "password" ) );
		try
		{
			getRuntime().getTechnicalLayer().getConfigMidtier().addRootFolder( folder );
			result.setActionMessage( "${ui.actions.addHttpFolder.success}" );
		}
		catch ( OpenMMMidtierException e )
		{
			result.setActionMessage( e.toString() );
			log.error( "${ui.actions.addHttpFolder.failed}", e );
		}
		return result;
	}

}
