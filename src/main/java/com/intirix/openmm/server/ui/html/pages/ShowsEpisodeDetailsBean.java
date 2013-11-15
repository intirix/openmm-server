package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class ShowsEpisodeDetailsBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int showId;
	
	private int seasonNumber;
	
	private Episode episode;
	
	

	public int getShowId()
	{
		return showId;
	}

	public void setShowId( int showId )
	{
		this.showId = showId;
	}

	public int getSeasonNumber()
	{
		return seasonNumber;
	}

	public void setSeasonNumber( int seasonNumber )
	{
		this.seasonNumber = seasonNumber;
	}

	public Episode getEpisode()
	{
		return episode;
	}

	public void setEpisode( Episode episode )
	{
		this.episode = episode;
	}
	
	


}
