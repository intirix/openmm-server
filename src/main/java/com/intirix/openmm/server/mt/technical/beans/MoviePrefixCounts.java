package com.intirix.openmm.server.mt.technical.beans;

import org.simpleframework.xml.Default;

@Default
public class MoviePrefixCounts
{

	private String prefix;
	
	private int numMovies;
	
	private int numMoviesAvailable;

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix( String prefix )
	{
		this.prefix = prefix;
	}

	public int getNumMovies()
	{
		return numMovies;
	}

	public void setNumMovies( int numMovies )
	{
		this.numMovies = numMovies;
	}

	public int getNumMoviesAvailable()
	{
		return numMoviesAvailable;
	}

	public void setNumMoviesAvailable( int numMoviesAvailable )
	{
		this.numMoviesAvailable = numMoviesAvailable;
	}

	@Override
	public String toString()
	{
		return getPrefix() + '(' + getNumMoviesAvailable() + '/' + getNumMovies() + ')';
	}
	
	
	
}
