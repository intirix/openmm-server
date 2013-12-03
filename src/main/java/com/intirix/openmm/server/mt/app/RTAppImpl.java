package com.intirix.openmm.server.mt.app;

import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.MovieMidtier;
import com.intirix.openmm.server.mt.technical.rottentomatoes.RTMidtier;

public class RTAppImpl implements RTApp
{

	private MovieMidtier movieMidtier;

	private RTMidtier rtMidtier;

	public void setMovieMidtier( MovieMidtier MovieMidtier )
	{
		this.movieMidtier = MovieMidtier;
	}

	public void setRTMidtier( RTMidtier rtMidtier )
	{
		this.rtMidtier = rtMidtier;
	}

	public int importMovie( String id ) throws OpenMMMidtierException
	{
		for ( final Movie movie: movieMidtier.listMovies() )
		{
			if ( id.equals( movie.getRtId() ) )
			{
				return movie.getId();
			}
		}

		final it.jtomato.gson.Movie rtmovie = rtMidtier.getMovieById( id );
		final Movie movie = new Movie();
		movie.setDescription( rtmovie.synopsis );
		movie.setDisplayName( rtmovie.title );
		final StringBuilder genres = new StringBuilder( 128 );
		if ( rtmovie.genres != null )
		{
			for ( final String genre: rtmovie.genres )
			{
				if ( genres.length() > 0 )
				{
					genres.append( ',' );
				}
				genres.append( genre );
			}
		}
		movie.setGenre( genres.toString() );
		movie.setMpaaRating( rtmovie.mpaaRating );
		movie.setName( movie.getDisplayName().replace( "The ", "" ) );
		if ( rtmovie.posters != null )
		{
			movie.setPosterUrl( rtmovie.posters.original );
		}
		if ( rtmovie.rating != null )
		{
			movie.setRating( "" + ( rtmovie.rating.audienceScore / 10.0f ) );
		}
		if ( rtmovie.releaseDate != null )
		{
			movie.setReleaseDate( rtmovie.releaseDate.theater );
		}
		movie.setRtId( id );
		movie.setYear( rtmovie.year );
		return movieMidtier.addMovie( movie );
	}

}
