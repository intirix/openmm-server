package com.intirix.openmm.server.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection of Event Listeners
 * @author jeff
 *
 */
public class EventListenerCollection< T extends BaseEvent > implements EventListener< T >
{
	
	/**
	 * Collection of event listeners
	 */
	private final List< EventListener< T > > listeners = new ArrayList< EventListener<T > >( 10 );

	/**
	 * Pass the event to the collection
	 */
	public void handleEvent( T event )
	{
		for ( final EventListener< T > listener: listeners )
		{
			listener.handleEvent( event );
		}
	}
	
	/**
	 * Add an event listener
	 * @param listener
	 */
	public void addEventListener( EventListener< T > listener )
	{
		listeners.add( listener );
	}

}
