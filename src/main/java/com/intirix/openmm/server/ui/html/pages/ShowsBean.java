package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.ui.html.PageData;

@Default
public class ShowsBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean hasTVDBKey = false;

	public boolean getHasTVDBKey()
	{
		return hasTVDBKey;
	}

	public void setHasTVDBKey( boolean hasTVDBKey )
	{
		this.hasTVDBKey = hasTVDBKey;
	}
	
	
}
