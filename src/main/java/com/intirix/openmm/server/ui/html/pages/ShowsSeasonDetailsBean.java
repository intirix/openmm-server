package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class ShowsSeasonDetailsBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bannerPath = "";
	
	private Season season;
	
	private Episode[] episodes;
	
	

	public String getBannerPath()
	{
		return bannerPath;
	}

	public void setBannerPath( String bannerPath )
	{
		this.bannerPath = bannerPath;
	}

	public Season getSeason()
	{
		return season;
	}

	public void setSeason( Season season )
	{
		this.season = season;
	}

	public Episode[] getEpisodes()
	{
		return episodes;
	}

	public void setEpisodes( Episode[] episodes )
	{
		this.episodes = episodes;
	}
	
	
	
}
