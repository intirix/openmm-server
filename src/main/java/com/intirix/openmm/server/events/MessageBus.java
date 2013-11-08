package com.intirix.openmm.server.events;

/**
 * Message bus for posting/handling messages
 * @author jeff
 *
 */
public class MessageBus extends EventListenerCollection< BaseEvent >
{
	
	private static final MessageBus instance = new MessageBus();
	
	
	/**
	 * Get an instance of the bus
	 * @return
	 */
	public static MessageBus getInstance()
	{
		return instance;
	}



	/**
	 * Add a listener
	 * @param eventType
	 * @param listener
	 */
	public < T extends BaseEvent > void addListener( final Class< T > eventType, final EventListener< T > listener )
	{
		addEventListener( new EventListener< BaseEvent >() {
			
			@SuppressWarnings( "unchecked" )
			public void handleEvent( BaseEvent event )
			{
				if ( event.getClass().isAssignableFrom( eventType ) )
				{
					listener.handleEvent( (T)event );
				}
			}
		} );
	}

}
