package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.mt.OpenMMMidtierException;

/**
 * Action that updates all shows
 * @author jeff
 *
 */
public class TVDBUpdateAllAction extends PostAction
{

	private final Log log = LogFactory.getLog( TVDBUpdateAllAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		final PostActionResult res = new PostActionResult();
		getRuntime().executeTask( new Runnable() {
			
			public void run()
			{
				try
				{
					getRuntime().getApplicationLayer().getTvdbApp().updateShows();
				}
				catch ( OpenMMMidtierException e )
				{
					log.warn( "Failed to update shows", e );
				}
			}
		} );
		res.setActionMessage( "Started update" );

		return res;
	}

}
