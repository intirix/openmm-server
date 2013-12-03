package com.intirix.openmm.server.mt.technical.rottentomatoes;

import com.intirix.openmm.server.events.EventListener;

/**
 * Update the TVDBMidtier when a key gets changed
 * @author jeff
 *
 */
public class RottenTomatoesApiKeyUpdatedEventListener implements EventListener< RottenTomatoesApiKeyUpdatedEvent >
{
	
	private final RTMidtier midtier;

	public RottenTomatoesApiKeyUpdatedEventListener( RTMidtier midtier )
	{
		this.midtier = midtier;
	}

	public void handleEvent( RottenTomatoesApiKeyUpdatedEvent event )
	{
		midtier.setKey( event.getNewKey() );
	}

}
