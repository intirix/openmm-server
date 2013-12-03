package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;

public class RTImportAction extends PostAction
{

	private final Log log = LogFactory.getLog( RTImportAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		final PostActionResult res = new PostActionResult();
		final String id = req.getParameter( "rtId" );
		try
		{
			getRuntime().getApplicationLayer().getRtApp().importMovie( id );
			res.setActionMessage( "${ui.actions.rtImport.success}" );
		}
		catch ( Exception e )
		{
			log.warn( "Failed to import show:", e );
			res.setActionMessage( "${ui.actions.rtImport.failed}: " + e.getMessage() );
		}
		return res;
	}

}
