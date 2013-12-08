package com.intirix.openmm.server.mt.app;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;
import com.intirix.openmm.server.vfs.FileSystemBrowser;
import com.intirix.openmm.server.vfs.FileSystemFactory;

/**
 * Default VFS impl
 * @author jeff
 *
 */
public class VFSAppImpl implements VFSApp
{

	private ConfigMidtier configMidtier;

	public void setConfigMidtier( ConfigMidtier configMidtier )
	{
		this.configMidtier = configMidtier;
	}

	public FileSystemBrowser getBrowser() throws OpenMMMidtierException
	{
		final FileSystemBrowser browser = new FileSystemBrowser();
		for ( final RootFolder folder: configMidtier.listRootFolders() )
		{
			browser.mount( folder.getMountPoint(), new FileSystemFactory().createFileSystem( folder ) );
		}

		return browser;
	}

}
