package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;

public class TVDBUpdateAction extends PostAction
{

	private final Log log = LogFactory.getLog( TestTVDBUpdateAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		final PostActionResult res = new PostActionResult();
		final int showId = Integer.parseInt( req.getParameter( "showId" ) );
		try
		{
			getRuntime().getApplicationLayer().getTvdbApp().updateShow( showId );
			res.setActionMessage( "Successfully updated show" );
		}
		catch ( Exception e )
		{
			log.warn( "Failed to import show:", e );
			res.setActionMessage( "Failed to update show: " + e.getMessage() );
		}
		return res;
	}

}
