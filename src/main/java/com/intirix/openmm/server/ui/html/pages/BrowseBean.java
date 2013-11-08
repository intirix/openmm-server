package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

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
	
	private String[] folders = new String[]{};
	
	private String[] files = new String[]{};
	
	

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

	public String[] getFolders()
	{
		return folders;
	}

	public void setFolders( String[] folders )
	{
		this.folders = folders;
	}

	public String[] getFiles()
	{
		return files;
	}

	public void setFiles( String[] files )
	{
		this.files = files;
	}
	
	
	
}
