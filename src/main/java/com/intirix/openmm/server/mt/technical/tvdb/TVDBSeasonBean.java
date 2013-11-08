package com.intirix.openmm.server.mt.technical.tvdb;

/**
 * Simple midtier bean for representing a season
 * @author jeff
 *
 */
public class TVDBSeasonBean implements Comparable< TVDBSeasonBean >
{

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

	public int compareTo( TVDBSeasonBean o )
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
