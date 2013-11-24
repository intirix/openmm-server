package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.api.beans.FileEntry;
import com.intirix.openmm.server.api.beans.FolderEntry;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class BrowseBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String parentPath = "";

	private String path;
	
	private FolderEntry[] folders = new FolderEntry[]{};
	
	private FileEntry[] files = new FileEntry[]{};
	
	

	public String getParentPath()
	{
		return parentPath;
	}

	public void setParentPath( String parentPath )
	{
		this.parentPath = parentPath;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath( String path )
	{
		this.path = path;
	}

	public FolderEntry[] getFolders()
	{
		return folders;
	}

	public void setFolders( FolderEntry[] folders )
	{
		this.folders = folders;
	}

	public FileEntry[] getFiles()
	{
		return files;
	}

	public void setFiles( FileEntry[] files )
	{
		this.files = files;
	}


	
	
}
