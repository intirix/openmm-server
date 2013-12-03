package com.intirix.openmm.server.ui.html.pages.beans;

import org.simpleframework.xml.Default;

@Default
public class RTMovieSearchResultBean
{

	private String id;
	
	private String name;
	
	private String releaseDate;

	public String getId()
	{
		return id;
	}

	public void setId( String id )
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getReleaseDate()
	{
		return releaseDate;
	}

	public void setReleaseDate( String releaseDate )
	{
		this.releaseDate = releaseDate;
	}
	
	public String getDisplayName()
	{
		return name + " - " + releaseDate;
	}
	
	
}
