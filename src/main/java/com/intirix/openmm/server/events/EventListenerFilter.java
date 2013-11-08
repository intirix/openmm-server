package com.intirix.openmm.server.events;

/**
 * Filters out events that are not passing in an instance of the filter class
 * @author jeff
 *
 */
public class EventListenerFilter< T extends BaseEvent > implements EventListener< BaseEvent >
{
	private EventListener< T > child;
	
	private Class< T > filterClass;
	
	public EventListenerFilter( EventListener< T > child, Class< T > filterClass )
	{
		this.child = child;
		this.filterClass = filterClass;
	}

	@SuppressWarnings( "unchecked" )
	public void handleEvent( BaseEvent event )
	{
		if ( event.getClass().isAssignableFrom( filterClass ) )
		{
			child.handleEvent( (T)event );
		}
	}

}
