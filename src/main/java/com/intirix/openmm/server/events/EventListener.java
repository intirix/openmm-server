package com.intirix.openmm.server.events;

public interface EventListener< T extends BaseEvent >
{

	public void handleEvent( T event );
}
