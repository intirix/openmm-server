package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.TvdbSeasonInfoBean;

@Default
public class ShowsTvdbDetailsBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String banner = "";
	
	private String name = "";
	
	private String description = "";
	
	private String rating = "";
	
	private TvdbSeasonInfoBean[] seasons = new TvdbSeasonInfoBean[]{};
	
	

	public String getId()
	{
		return id;
	}

	public void setId( String id )
	{
		this.id = id;
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

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public String getRating()
	{
		return rating;
	}

	public void setRating( String rating )
	{
		this.rating = rating;
	}

	public TvdbSeasonInfoBean[] getSeasons()
	{
		return seasons;
	}

	public void setSeasons( TvdbSeasonInfoBean[] seasons )
	{
		this.seasons = seasons;
	}




}
