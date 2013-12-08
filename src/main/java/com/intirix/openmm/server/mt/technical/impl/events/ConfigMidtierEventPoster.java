package com.intirix.openmm.server.mt.technical.impl.events;

import com.intirix.openmm.server.events.MessageBus;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;
import com.intirix.openmm.server.mt.technical.events.VFSConfigUpdatedEvent;
import com.intirix.openmm.server.mt.technical.impl.ConfigMidtierDecorator;

/**
 * Notifies other objects of config changes
 * @author jeff
 *
 */
public class ConfigMidtierEventPoster extends ConfigMidtierDecorator
{
	
	private MessageBus bus;

	public ConfigMidtierEventPoster( ConfigMidtier child, MessageBus bus )
	{
		super( child );
		this.bus = bus;
	}

	@Override
	public void addRootFolder( RootFolder folder ) throws OpenMMMidtierException
	{
		super.addRootFolder( folder );
		bus.handleEvent( new VFSConfigUpdatedEvent() );
	}

	@Override
	public void deleteRootFolder( int folderId ) throws OpenMMMidtierException
	{
		bus.handleEvent( new VFSConfigUpdatedEvent() );
		super.deleteRootFolder( folderId );
	}

	@Override
	public void updateRootFolder( RootFolder oldBean, RootFolder newBean ) throws OpenMMMidtierException
	{
		super.updateRootFolder( oldBean, newBean );
		bus.handleEvent( new VFSConfigUpdatedEvent() );
	}
	
	

}
