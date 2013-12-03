package com.intirix.openmm.server.mt.technical.impl;

import java.util.List;

import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.MovieMidtier;

public class MovieMidtierDecorator implements MovieMidtier
{
	
	private final MovieMidtier child;
	
	public MovieMidtierDecorator( MovieMidtier child )
	{
		this.child = child;
	}

	public int addMovie( Movie movie ) throws OpenMMMidtierException
	{
		return child.addMovie( movie );
	}

	public void deleteMovie( int movieId ) throws OpenMMMidtierException
	{
		child.deleteMovie( movieId );
	}

	public void updateMovie( Movie oldBean, Movie newBean ) throws OpenMMMidtierException
	{
		child.updateMovie( oldBean, newBean );
	}

	public List< Movie > listMovies() throws OpenMMMidtierException
	{
		return child.listMovies();
	}

	public void watchMovie( int movieId ) throws OpenMMMidtierException
	{
		child.watchMovie( movieId );
	}

}
