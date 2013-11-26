package com.intirix.openmm.server.mt.technical.impl;

import java.util.List;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;

public class ConfigMidtierDecorator implements ConfigMidtier
{
	
	private final ConfigMidtier child;
	
	public ConfigMidtierDecorator( ConfigMidtier child )
	{
		this.child = child;
	}

	public List< RootFolder > listRootFolders() throws OpenMMMidtierException
	{
		return child.listRootFolders();
	}

	public void addRootFolder( RootFolder folder ) throws OpenMMMidtierException
	{
		child.addRootFolder( folder );
	}

	public RootFolder getRootFolder( int folderId ) throws OpenMMMidtierException
	{
		return child.getRootFolder( folderId );
	}

	public void deleteRootFolder( int folderId ) throws OpenMMMidtierException
	{
		child.deleteRootFolder( folderId );
	}

	public void updateRootFolder( RootFolder oldBean, RootFolder newBean ) throws OpenMMMidtierException
	{
		child.updateRootFolder( oldBean, newBean );
	}

}
