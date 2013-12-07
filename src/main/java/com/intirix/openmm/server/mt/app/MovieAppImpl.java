package com.intirix.openmm.server.mt.app;

import java.util.ArrayList;
import java.util.List;

import com.intirix.openmm.server.api.beans.MediaLink;
import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.api.beans.MovieDetails;
import com.intirix.openmm.server.api.beans.MoviePrefixCounts;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.MovieMidtier;

public class MovieAppImpl implements MovieApp
{
	
	private MovieMidtier movieMidtier;
	
	private WebCacheApp webCacheApp;

	public void setMovieMidtier( MovieMidtier midtier )
	{
		this.movieMidtier = midtier;
	}
	

	public void setWebCacheApp( WebCacheApp webCacheApp )
	{
		this.webCacheApp = webCacheApp;
	}
	


	public MovieMidtier getMovieMidtier()
	{
		return movieMidtier;
	}


	public WebCacheApp getWebCacheApp()
	{
		return webCacheApp;
	}


	public int addMovie( Movie movie ) throws OpenMMMidtierException
	{
		return movieMidtier.addMovie( movie );
	}

	public void deleteMovie( int movieId ) throws OpenMMMidtierException
	{
		movieMidtier.deleteMovie( movieId );
	}

	public void updateMovie( Movie oldBean, Movie newBean ) throws OpenMMMidtierException
	{
		movieMidtier.updateMovie( oldBean, newBean );
	}

	public List< Movie > listMovies() throws OpenMMMidtierException
	{
		return movieMidtier.listMovies();
	}
	
	

	public List< MovieDetails > listMovieDetails( String prefix ) throws OpenMMMidtierException
	{
		final List< Movie > movies = listMovies();
		final List< MovieDetails > details = new ArrayList< MovieDetails >( movies.size() );
		
		// iterate over all movies searching for ones that start with the prefix
		for ( final Movie movie: movies )
		{
			// do the prefix check
			if ( movie.getName().toUpperCase().startsWith( prefix.toUpperCase() ) )
			{
				final MovieDetails detail = new MovieDetails();
				detail.setMovie( movie );
				
				int available = 0;
				
				// search for a link that is available
				for ( final MediaLink link: getMovieLinks( movie.getId() ) )
				{
					if ( link.isAvailable() )
					{
						available++;
					}
				}
				
				// if we found an available link
				if ( available > 0 )
				{
					detail.setAvailable( true );
				}
				
				details.add( detail );
			}
		}
		
		return details;
	}


	public Movie getMovieById( int movieId ) throws OpenMMMidtierException
	{
		Movie movie = null;
		
		// find the movie that matches the id
		for ( final Movie m: listMovies() )
		{
			if ( movieId == m.getId() )
			{
				try
				{
					movie = (Movie)m.clone();
				}
				catch ( CloneNotSupportedException e )
				{
					throw new OpenMMMidtierException( e );
				}
			}
		}
		
		if ( movie == null )
		{
			throw new OpenMMMidtierException( "Could not find movie" );
		}
		
		// if the movie has a poster, then cache it
		if ( movie.getPosterUrl().length() > 0 )
		{
			movie.setPosterUrl( webCacheApp.getWebCacheUrl( movie.getPosterUrl() ) );
		}
		
		// set the links for the movie
		movie.setLinks( getMovieLinks( movieId ).toArray( new MediaLink[]{} ) );
		
		return movie;
	}

	public List< MoviePrefixCounts > listMoviePrefixes() throws OpenMMMidtierException
	{
		return movieMidtier.listMoviePrefixes();
	}

	public void assignFile( int movieId, String file, long size ) throws OpenMMMidtierException
	{
		movieMidtier.assignFile( movieId, file, size );
	}

	public void unassignFile( int linkId ) throws OpenMMMidtierException
	{
		movieMidtier.unassignFile( linkId );
	}

	public void watchMovie( int movieId ) throws OpenMMMidtierException
	{
		movieMidtier.watchMovie( movieId );
	}

	public List< MediaLink > getMovieLinks( int movieId ) throws OpenMMMidtierException
	{
		return movieMidtier.getMovieLinks( movieId );
	}

}
