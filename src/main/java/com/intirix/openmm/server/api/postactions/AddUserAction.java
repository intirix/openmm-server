package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.beans.UserBean;

/**
 * Action that gets called when an admin adds a user
 * @author jeff
 *
 */
public class AddUserAction extends PostAction
{

	/**
	 * Logger
	 */
	private final Logger log = Logger.getLogger( AddUserAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		final PostActionResult result = new PostActionResult();
		
		final UserBean user = new UserBean();
		user.setAdmin( "Y".equalsIgnoreCase( req.getParameter( "admin" ) ) );
		user.setDisplayName( req.getParameter( "displayName" ) );
		user.setUsername( req.getParameter( "username" ) );
		final String password = req.getParameter( "password" );
		
		try
		{
			getRuntime().getApplicationLayer().getUserApp().addUser( user );
			getRuntime().getApplicationLayer().getUserApp().resetPassword( user.getUsername(), password );
			result.setActionMessage( "${ui.actions.addUser.success}" );
		}
		catch ( OpenMMMidtierException e )
		{
			result.setActionMessage( e.toString() );
			log.error( "${ui.actions.addUser.failed}", e );
		}
		return result;
	}

}
