package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.intirix.openmm.server.Configuration;
import com.intirix.openmm.server.api.PostAction;
import com.intirix.openmm.server.api.PostActionResult;
import com.intirix.openmm.server.events.MessageBus;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBApiKeyUpdatedEvent;

public class UpdateServerConfigAction extends PostAction
{

	/**
	 * Logger
	 */
	private final Logger log = Logger.getLogger( UpdateServerConfigAction.class );

	@Override
	public PostActionResult processAction( HttpServletRequest req ) throws ServletException
	{
		final PostActionResult result = new PostActionResult();
		try
		{

			final Configuration config = getRuntime().getConfig();
			
			if ( req.getParameter( "httpPort" ) != null )
			{
				config.setHttpPort( Integer.parseInt( req.getParameter( "httpPort" ) ) );
			}
			
			final String tvdbKey = req.getParameter( "tvdbKey" );
			if ( tvdbKey != null )
			{
				config.setTvdbKey( tvdbKey );
				final TVDBApiKeyUpdatedEvent event = new TVDBApiKeyUpdatedEvent( tvdbKey );
				MessageBus.getInstance().handleEvent( event );
			}
			config.saveToUserDir();

			result.setActionMessage( "Successfully updated server config" );
		}
		catch ( Exception e )
		{
			result.setActionMessage( e.toString() );
			log.error( "Failed to update server config", e );
		}
		return result;

	}

}
