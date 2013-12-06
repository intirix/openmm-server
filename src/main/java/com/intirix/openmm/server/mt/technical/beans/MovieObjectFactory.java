package com.intirix.openmm.server.mt.technical.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.mt.technical.sql.SQLHelper.ObjectFactory;

public class MovieObjectFactory implements ObjectFactory< Movie >
{

	public Movie createObject( ResultSet rs ) throws SQLException
	{
		final Movie movie = new Movie();
		
		movie.setDescription( rs.getString( "DESCRIPTION" ) );
		movie.setDisplayName( rs.getString( "DISPLAY_NAME" ) );
		movie.setGenre( rs.getString( "GENRE" ) );
		movie.setId( rs.getInt( "MOVIE_ID" ) );
		movie.setImdbId( rs.getString( "IMDB_NUMBER" ) );
		movie.setMpaaRating( rs.getString( "MPAA_RATING" ) );
		movie.setName( rs.getString( "NAME" ).replace( "The ", "" ) );
		movie.setPosterUrl( rs.getString( "POSTER_URL" ) );
		movie.setRating( rs.getString( "RATING" ) );
		movie.setReleaseDate( rs.getString( "RELEASE_DATE" ) );
		movie.setRtId( rs.getString( "RTID_NUMBER" ) );
		movie.setRuntime( rs.getString( "RUNTIME" ) );
		movie.setYear( rs.getString( "YEAR" ) );
		
		return movie;
	}

}
