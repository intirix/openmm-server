package com.intirix.openmm.server.ui.html.pages.beans;

import java.io.Serializable;

import org.simpleframework.xml.Default;

@Default
public class TvdbSeasonInfoBean implements Serializable, Comparable< TvdbSeasonInfoBean >
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int seasonNumber;
	
	private String name;

	public int getSeasonNumber()
	{
		return seasonNumber;
	}

	public void setSeasonNumber( int seasonNumber )
	{
		this.seasonNumber = seasonNumber;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public int compareTo( TvdbSeasonInfoBean o )
	{
		if ( getSeasonNumber() < o.getSeasonNumber() )
		{
			return -1;
		}
		else if ( getSeasonNumber() > o.getSeasonNumber() )
		{
			return 1;
		}
		return 0;
	}
	
	

	@Override
	public String toString()
	{
		return getName();
	}

	@Override
	public boolean equals( Object obj )
	{
		return toString().equals( obj.toString() );
	}
	
}
