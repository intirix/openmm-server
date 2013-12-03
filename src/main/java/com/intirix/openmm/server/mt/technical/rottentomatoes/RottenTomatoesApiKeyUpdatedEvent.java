package com.intirix.openmm.server.mt.technical.rottentomatoes;

import com.intirix.openmm.server.events.BaseEvent;

/**
 * Event that gets fired off when the TVDB Api key gets updated
 * @author jeff
 *
 */
public class RottenTomatoesApiKeyUpdatedEvent extends BaseEvent
{

	private String newKey;
	
	public RottenTomatoesApiKeyUpdatedEvent()
	{
		
	}
	
	public RottenTomatoesApiKeyUpdatedEvent( String newKey )
	{
		this();
		this.newKey = newKey;
	}

	public String getNewKey()
	{
		return newKey;
	}

	public void setNewKey( String newKey )
	{
		this.newKey = newKey;
	}
	
	

}
