package com.intirix.openmm.server.mt.app;

import com.intirix.openmm.server.events.EventListener;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.events.VFSConfigUpdatedEvent;
import com.intirix.openmm.server.vfs.FileSystemBrowser;

/**
 * Cache for the FileSystemBrowser object
 * @author jeff
 *
 */
public class VFSAppCache extends VFSAppDecorator implements EventListener< VFSConfigUpdatedEvent >
{
	
	private FileSystemBrowser cache = null;

	public VFSAppCache( VFSApp child )
	{
		super( child );
	}

	/**
	 * Flush the cache
	 */
	public void handleEvent( VFSConfigUpdatedEvent event )
	{
		cache = null;
	}

	@Override
	public synchronized FileSystemBrowser getBrowser() throws OpenMMMidtierException
	{
		if ( cache == null )
		{
			cache = super.getBrowser();
		}
		return cache;
	}
	
	

}
