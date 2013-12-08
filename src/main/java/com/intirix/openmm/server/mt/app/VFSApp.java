package com.intirix.openmm.server.mt.app;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.vfs.FileSystemBrowser;

/**
 * Application layer for the VFS library
 * @author jeff
 *
 */
public interface VFSApp
{
	
	/**
	 * Set the config midtier
	 * @param configMidtier
	 */
	public void setConfigMidtier( ConfigMidtier configMidtier );

	/**
	 * Get the file system browser
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public FileSystemBrowser getBrowser() throws OpenMMMidtierException;
}
