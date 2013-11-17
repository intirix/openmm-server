package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.api.beans.SeasonDetails;
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

	private SeasonDetails[] seasons;
	

	public Show getShow()
	{
		return show;
	}

	public void setShow( Show show )
	{
		this.show = show;
	}

	public SeasonDetails[] getSeasons()
	{
		return seasons;
	}

	public void setSeasons( SeasonDetails[] seasons )
	{
		this.seasons = seasons;
	}

	
	
}
