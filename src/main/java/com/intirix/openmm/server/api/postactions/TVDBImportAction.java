package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;

public class TVDBImportAction extends PostAction
{

	private final Log log = LogFactory.getLog( TVDBImportAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		final PostActionResult res = new PostActionResult();
		final String id = req.getParameter( "tvdbId" );
		try
		{
			getRuntime().getApplicationLayer().getTvdbApp().importShow( id );
			res.setActionMessage( "Successfully imported show" );
		}
		catch ( Exception e )
		{
			log.warn( "Failed to import show:", e );
			res.setActionMessage( "Failed to import show: " + e.getMessage() );
		}
		return res;
	}

}
