package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.TvdbEpisodeBean;

@Default
public class ShowsTvdbSeasonDetailsBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private int seasonNumber;

	private String banner = "";
	
	private String name = "";
	
	private TvdbEpisodeBean[] episodes = new TvdbEpisodeBean[]{};

	public String getId()
	{
		return id;
	}

	public void setId( String id )
	{
		this.id = id;
	}

	public int getSeasonNumber()
	{
		return seasonNumber;
	}

	public void setSeasonNumber( int seasonNumber )
	{
		this.seasonNumber = seasonNumber;
	}

	public String getBanner()
	{
		return banner;
	}

	public void setBanner( String banner )
	{
		this.banner = banner;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public TvdbEpisodeBean[] getEpisodes()
	{
		return episodes;
	}

	public void setEpisodes( TvdbEpisodeBean[] episodes )
	{
		this.episodes = episodes;
	}

	


}
