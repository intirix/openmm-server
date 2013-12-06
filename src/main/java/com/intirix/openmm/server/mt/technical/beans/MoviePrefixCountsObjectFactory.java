package com.intirix.openmm.server.mt.technical.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.intirix.openmm.server.mt.technical.sql.SQLHelper.ObjectFactory;

public class MoviePrefixCountsObjectFactory implements ObjectFactory< MoviePrefixCounts >
{

	public MoviePrefixCounts createObject( ResultSet rs ) throws SQLException
	{
		final MoviePrefixCounts obj = new MoviePrefixCounts();
		
		obj.setPrefix( rs.getString( "PREFIX" ) );
		obj.setNumMovies( rs.getInt( "NUM_MOVIES" ) );
		obj.setNumMoviesAvailable( rs.getInt( "NUM_MOVIES_AVAIL" ) );
		
		return obj;
	}

}
