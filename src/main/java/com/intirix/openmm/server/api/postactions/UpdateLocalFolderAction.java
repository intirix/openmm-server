package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;

public class UpdateLocalFolderAction extends PostAction
{

	/**
	 * Logger
	 */
	private final Logger log = Logger.getLogger( UpdateLocalFolderAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		final PostActionResult result = new PostActionResult();
		try
		{
			final ConfigMidtier configMidtier = getRuntime().getTechnicalLayer().getConfigMidtier();
			final RootFolder oldBean = configMidtier.getRootFolder( Integer.parseInt( req.getParameter( "id" ) ) );
			final RootFolder newBean = (RootFolder)oldBean.clone();
			
			newBean.setUrl( req.getParameter( "path" ) );
			newBean.setMountPoint( req.getParameter( "mountpoint" ) );
			
			configMidtier.updateRootFolder( oldBean, newBean );
			

			result.setActionMessage( "${ui.actions.updateLocalFolder.success}" );
		}
		catch ( Exception e )
		{
			result.setActionMessage( e.toString() );
			log.error( "${ui.actions.updateLocalFolder.failed}", e );
		}
		return result;

	}

}
