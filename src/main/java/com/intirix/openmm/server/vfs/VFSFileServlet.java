package com.intirix.openmm.server.vfs;

import com.intirix.openmm.server.mt.OpenMMMidtierException;

public class VFSFileServlet extends FileServlet
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected FileSystemBrowser getFileSystemBrowser() throws OpenMMMidtierException
	{
		final FileSystemBrowser browser = getRuntime().getApplicationLayer().getVfsApp().getBrowser();
		return browser;
	}
}
