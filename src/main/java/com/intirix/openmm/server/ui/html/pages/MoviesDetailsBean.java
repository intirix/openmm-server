package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class MoviesDetailsBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Movie movie;
	
	/**
	 * Used for the link to the assignMovieFiles.html page
	 */
	private String prefix = "";

	public Movie getMovie()
	{
		return movie;
	}

	public void setMovie( Movie movie )
	{
		this.movie = movie;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix( String prefix )
	{
		this.prefix = prefix;
	}
	
	
	
	
}
