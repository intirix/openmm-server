package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.mt.technical.beans.LocalRootFolder;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class AdminFoldersBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RootFolder[] folders = new RootFolder[]{ new LocalRootFolder() };

	public RootFolder[] getFolders()
	{
		return folders;
	}

	public void setFolders( RootFolder[] folders )
	{
		this.folders = folders;
	}


	
	
	
}
