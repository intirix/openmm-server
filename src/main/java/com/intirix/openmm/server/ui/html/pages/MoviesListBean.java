package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.api.beans.MovieDetails;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class MoviesListBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MovieDetails movies[] = new MovieDetails[]{};

	public MovieDetails[] getMovies()
	{
		return movies;
	}

	public void setMovies( MovieDetails[] movies )
	{
		this.movies = movies;
	}


	

	
}
