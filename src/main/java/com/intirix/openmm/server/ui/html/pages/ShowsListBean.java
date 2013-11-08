package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class ShowsListBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Show[] shows;

	public Show[] getShows()
	{
		return shows;
	}

	public void setShows( Show[] shows )
	{
		this.shows = shows;
	}
	
	
	
}
