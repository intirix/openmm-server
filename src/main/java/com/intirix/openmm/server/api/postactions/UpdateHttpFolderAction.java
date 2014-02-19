package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.beans.HttpRootFolder;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;

public class UpdateHttpFolderAction extends PostAction
{

	/**
	 * Logger
	 */
	private final Logger log = Logger.getLogger( UpdateHttpFolderAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		final PostActionResult result = new PostActionResult();
		try
		{
			final ConfigMidtier configMidtier = getRuntime().getTechnicalLayer().getConfigMidtier();
			final RootFolder oldBean = configMidtier.getRootFolder( Integer.parseInt( req.getParameter( "id" ) ) );
			final HttpRootFolder newBean = (HttpRootFolder)oldBean.clone();
			
			newBean.setUrl( req.getParameter( "url" ) );
			newBean.setMountPoint( req.getParameter( "mountpoint" ) );
			newBean.setUsername( req.getParameter( "username" ) );
			newBean.setPassword( req.getParameter( "password" ) );
			
			configMidtier.updateRootFolder( oldBean, newBean );
			

			result.setActionMessage( "${ui.actions.updateHttpFolder.success}" );
		}
		catch ( Exception e )
		{
			result.setActionMessage( e.toString() );
			log.error( "${ui.actions.updateHttpFolder.failed}", e );
		}
		return result;

	}

}
