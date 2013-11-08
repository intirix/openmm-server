package com.intirix.openmm.server.mt.technical.tvdb;

import com.intirix.openmm.server.events.EventListener;

/**
 * Update the TVDBMidtier when a key gets changed
 * @author jeff
 *
 */
public class TVDBApiKeyUpdatedEventListener implements EventListener< TVDBApiKeyUpdatedEvent >
{
	
	private final TVDBMidtier midtier;

	public TVDBApiKeyUpdatedEventListener( TVDBMidtier midtier )
	{
		this.midtier = midtier;
	}

	public void handleEvent( TVDBApiKeyUpdatedEvent event )
	{
		midtier.setTvdbKey( event.getNewKey() );
	}

}
