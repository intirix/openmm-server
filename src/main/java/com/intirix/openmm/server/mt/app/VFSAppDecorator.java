package com.intirix.openmm.server.mt.app;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.vfs.FileSystemBrowser;

public class VFSAppDecorator implements VFSApp
{

	private final VFSApp child;
	
	public VFSAppDecorator( VFSApp child )
	{
		this.child = child;
	}

	public void setConfigMidtier( ConfigMidtier configMidtier )
	{
		child.setConfigMidtier( configMidtier );
	}

	public FileSystemBrowser getBrowser() throws OpenMMMidtierException
	{
		return child.getBrowser();
	}

}
