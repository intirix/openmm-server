package com.intirix.openmm.server.ui.html.pages.beans;

import org.simpleframework.xml.Default;

@Default
public class TvdbEpisodeBean
{

	private String id = "";

	private String name = "";

	private String rating = "";
	
	private String firstAired;
	
	private int seasonNumber;

	private int episodeNumber;

	private int dvdEpisodeNumber;

	private String description = "";

	private String banner = "";

	public String getId()
	{
		return id;
	}

	public void setId( String id )
	{
		if ( id != null )
		{
			this.id = id;
		}
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		if ( name != null )
		{
			this.name = name;
		}
	}

	public String getRating()
	{
		return rating;
	}

	public void setRating( String rating )
	{
		if ( rating != null )
		{
			this.rating = rating;
		}
	}
	
	

	public String getFirstAired()
	{
		return firstAired;
	}

	public void setFirstAired( String firstAired )
	{
		this.firstAired = firstAired;
	}

	public int getSeasonNumber()
	{
		return seasonNumber;
	}

	public void setSeasonNumber( int seasonNumber )
	{
		this.seasonNumber = seasonNumber;
	}

	public int getEpisodeNumber()
	{
		return episodeNumber;
	}

	public void setEpisodeNumber( int episodeNumber )
	{
		this.episodeNumber = episodeNumber;
	}

	public int getDvdEpisodeNumber()
	{
		return dvdEpisodeNumber;
	}

	public void setDvdEpisodeNumber( int dvdEpisodeNumber )
	{
		this.dvdEpisodeNumber = dvdEpisodeNumber;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		if ( description != null )
		{
			this.description = description;
		}
	}

	public String getBanner()
	{
		return banner;
	}

	public void setBanner( String banner )
	{
		if ( banner != null )
		{
			this.banner = banner;
		}
	}



}
