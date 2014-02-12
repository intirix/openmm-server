package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.UserApp;
import com.intirix.openmm.server.mt.technical.beans.UserBean;

/**
 * Action that gets called when an admin resets a user's password
 * @author jeff
 *
 */
public class ResetPasswordAction extends PostAction
{

	/**
	 * Logger
	 */
	private final Logger log = Logger.getLogger( ResetPasswordAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		final PostActionResult result = new PostActionResult();
		final int userId = Integer.parseInt( req.getParameter( "userId" ) );
		final String password = req.getParameter( "password" );
		
		try
		{
			final UserApp userApp = getRuntime().getApplicationLayer().getUserApp();
			final UserBean user = userApp.getUserById( userId );
			userApp.resetPassword( user.getUsername(), password );

			
			result.setActionMessage( "${ui.actions.resetPassword.success}" );
		}
		catch ( OpenMMMidtierException e )
		{
			result.setActionMessage( "${ui.actions.resetPassword.failed}: " + e.toString() );
			log.error( "${ui.actions.resetPassword.failed}", e );
		}
		return result;
	}

}
