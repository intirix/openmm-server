package com.intirix.openmm.server.mt.technical.tvdb;

import org.easymock.EasyMock;
import org.junit.Test;

import com.intirix.openmm.server.events.MessageBus;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBApiKeyUpdatedEvent;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBApiKeyUpdatedEventListener;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBMidtier;

public class TestTVDBApiKeyUpdatedEventListener
{

	@Test
	public void test1()
	{
		final String key = "NEW_KEY";
		
		final TVDBMidtier midtier = EasyMock.createMock( TVDBMidtier.class );
		midtier.setTvdbKey( key );
		EasyMock.expectLastCall();
		EasyMock.replay( midtier );
		
		final MessageBus bus = new MessageBus();
		bus.addListener( TVDBApiKeyUpdatedEvent.class, new TVDBApiKeyUpdatedEventListener( midtier ) );
		bus.handleEvent( new TVDBApiKeyUpdatedEvent( key ) );
	}

}
