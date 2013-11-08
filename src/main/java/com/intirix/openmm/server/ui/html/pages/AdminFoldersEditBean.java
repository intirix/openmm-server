package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.mt.technical.beans.RootFolder;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class AdminFoldersEditBean extends PageData
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RootFolder folder;

	public RootFolder getFolder()
	{
		return folder;
	}

	public void setFolder( RootFolder folder )
	{
		this.folder = folder;
	}
	
	

}
