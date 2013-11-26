package com.intirix.openmm.server.mt.technical.impl.cache;

import java.util.List;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;
import com.intirix.openmm.server.mt.technical.impl.ConfigMidtierDecorator;

public class ConfigMidtierCache extends ConfigMidtierDecorator
{
	
	private List< RootFolder > cache = null;

	public ConfigMidtierCache( ConfigMidtier child )
	{
		super( child );
	}

	@Override
	public synchronized List< RootFolder > listRootFolders() throws OpenMMMidtierException
	{
		if ( cache == null )
		{
			cache = super.listRootFolders();
		}
		return cache;
	}

	@Override
	public synchronized void addRootFolder( RootFolder folder ) throws OpenMMMidtierException
	{
		cache = null;
		super.addRootFolder( folder );
	}

	@Override
	public synchronized void deleteRootFolder( int folderId ) throws OpenMMMidtierException
	{
		cache = null;
		super.deleteRootFolder( folderId );
	}

	@Override
	public synchronized void updateRootFolder( RootFolder oldBean, RootFolder newBean ) throws OpenMMMidtierException
	{
		cache = null;
		super.updateRootFolder( oldBean, newBean );
	}
	
	

}
