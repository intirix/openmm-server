package com.intirix.openmm.server.mt.technical.tvdb;

import com.intirix.openmm.server.events.BaseEvent;

/**
 * Event that gets fired off when the TVDB Api key gets updated
 * @author jeff
 *
 */
public class TVDBApiKeyUpdatedEvent extends BaseEvent
{

	private String newKey;
	
	public TVDBApiKeyUpdatedEvent()
	{
		
	}
	
	public TVDBApiKeyUpdatedEvent( String newKey )
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
