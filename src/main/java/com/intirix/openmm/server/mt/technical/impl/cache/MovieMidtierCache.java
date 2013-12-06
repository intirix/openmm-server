package com.intirix.openmm.server.mt.technical.impl.cache;

import java.util.ArrayList;
import java.util.List;

import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.MovieMidtier;
import com.intirix.openmm.server.mt.technical.impl.MovieMidtierDecorator;

public class MovieMidtierCache extends MovieMidtierDecorator
{

	private final List< Movie > cache = new ArrayList< Movie >( 10 );

	public MovieMidtierCache( MovieMidtier child )
	{
		super( child );
	}

	@Override
	public int addMovie( Movie movie ) throws OpenMMMidtierException
	{
		try
		{
			return super.addMovie( movie );
		}
		finally
		{
			synchronized ( cache )
			{
				cache.clear();
			}
		}
	}

	@Override
	public void deleteMovie( int movieId ) throws OpenMMMidtierException
	{
		super.deleteMovie( movieId );
		synchronized ( cache )
		{
			cache.clear();
		}
	}

	@Override
	public void updateMovie( Movie oldBean, Movie newBean ) throws OpenMMMidtierException
	{
		super.updateMovie( oldBean, newBean );
		synchronized ( cache )
		{
			cache.clear();
		}
	}

	@Override
	public List< Movie > listMovies() throws OpenMMMidtierException
	{
		synchronized ( cache )
		{
			if ( cache.size() == 0 )
			{
				cache.addAll( super.listMovies() );
			}
			return cache;
		}
	}

	@Override
	public void watchMovie( int movieId ) throws OpenMMMidtierException
	{
		super.watchMovie( movieId );
		synchronized ( cache )
		{
			cache.clear();
		}
	}



}
