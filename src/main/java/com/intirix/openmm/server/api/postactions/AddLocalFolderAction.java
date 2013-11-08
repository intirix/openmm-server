package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.beans.LocalRootFolder;

public class AddLocalFolderAction extends PostAction
{

	/**
	 * Logger
	 */
	private final Logger log = Logger.getLogger( AddLocalFolderAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		final PostActionResult result = new PostActionResult();
		final LocalRootFolder folder = new LocalRootFolder();
		folder.setMountPoint( req.getParameter( "mountpoint" ) );
		folder.setUrl( "file://" + req.getParameter( "path" ) );
		try
		{
			getRuntime().getTechnicalLayer().getConfigMidtier().addRootFolder( folder );
			result.setActionMessage( "Successfully added local folder" );
		}
		catch ( OpenMMMidtierException e )
		{
			result.setActionMessage( e.toString() );
			log.error( "Failed to add local folder", e );
		}
		return result;
	}

}
