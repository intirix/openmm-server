package com.intirix.openmm.server.mt.technical.impl;

import java.util.List;

import com.intirix.openmm.server.api.beans.MediaLink;
import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.api.beans.MoviePrefixCounts;
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
	
	

	public List< MoviePrefixCounts > listMoviePrefixes() throws OpenMMMidtierException
	{
		return child.listMoviePrefixes();
	}

	public List< Movie > listMovies() throws OpenMMMidtierException
	{
		return child.listMovies();
	}

	public void watchMovie( int movieId ) throws OpenMMMidtierException
	{
		child.watchMovie( movieId );
	}

	public void assignFile( int movieId, String file, long size ) throws OpenMMMidtierException
	{
		child.assignFile( movieId, file, size );
	}

	public void unassignFile( int linkId ) throws OpenMMMidtierException
	{
		child.unassignFile( linkId );
	}

	public List< MediaLink > getMovieLinks( int movieId ) throws OpenMMMidtierException
	{
		return child.getMovieLinks( movieId );
	}

	
}
