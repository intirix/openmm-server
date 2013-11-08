package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class ShowsDetailsBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Show show;

	private Season[] seasons;
	

	public Show getShow()
	{
		return show;
	}

	public void setShow( Show show )
	{
		this.show = show;
	}

	public Season[] getSeasons()
	{
		return seasons;
	}

	public void setSeasons( Season[] seasons )
	{
		this.seasons = seasons;
	}

	
	
}
