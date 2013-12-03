package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.ui.html.PageData;

@Default
public class MoviesBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean hasRTKey = false;

	public boolean isHasRTKey()
	{
		return hasRTKey;
	}

	public void setHasRTKey( boolean hasRTKey )
	{
		this.hasRTKey = hasRTKey;
	}


	
	
}
