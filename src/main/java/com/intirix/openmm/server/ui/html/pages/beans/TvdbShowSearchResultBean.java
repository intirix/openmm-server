package com.intirix.openmm.server.ui.html.pages.beans;

import org.simpleframework.xml.Default;

@Default
public class TvdbShowSearchResultBean
{

	private String id;
	
	private String displayName;
	
	private String lang;
	
	private String firstAirDate;

	public String getId()
	{
		return id;
	}

	public void setId( String id )
	{
		this.id = id;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName( String displayName )
	{
		this.displayName = displayName;
	}

	public String getLang()
	{
		return lang;
	}

	public void setLang( String lang )
	{
		this.lang = lang;
	}

	public String getFirstAirDate()
	{
		return firstAirDate;
	}

	public void setFirstAirDate( String firstAirDate )
	{
		this.firstAirDate = firstAirDate;
	}
	
	

}
