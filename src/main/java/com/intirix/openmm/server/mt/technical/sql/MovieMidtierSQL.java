package com.intirix.openmm.server.mt.technical.sql;

import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import com.intirix.openmm.server.api.beans.MediaLink;
import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.MovieMidtier;
import com.intirix.openmm.server.mt.technical.beans.IntegerObjectFactory;
import com.intirix.openmm.server.mt.technical.beans.MediaLinkObjectFactory;
import com.intirix.openmm.server.mt.technical.beans.MovieObjectFactory;
import com.intirix.openmm.server.mt.technical.beans.MoviePrefixCounts;
import com.intirix.openmm.server.mt.technical.beans.MoviePrefixCountsObjectFactory;

public class MovieMidtierSQL implements MovieMidtier
{

	private final SQLHelper sqlHelper;

	public MovieMidtierSQL( DataSource ds )
	{
		sqlHelper = new SQLHelper( MovieMidtierSQL.class, ds );
	}

	public int addMovie( Movie movie ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "movie_add.sql", movie.getName(), movie.getDisplayName(), movie.getDescription(), movie.getGenre(),
					movie.getImdbId(), movie.getRtId(), movie.getMpaaRating(), movie.getRating(), movie.getRuntime(), movie.getReleaseDate(), 
					movie.getYear(), movie.getPosterUrl() );
			return sqlHelper.executeQuerySingleRow( new IntegerObjectFactory(), "movie_find.sql", movie.getName(), movie.getYear() );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public void deleteMovie( int movieId ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "movie_delete.sql", movieId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public void updateMovie( Movie oldBean, Movie movie ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "movie_update.sql", movie.getName(), movie.getDisplayName(), movie.getDescription(), movie.getGenre(),
					movie.getImdbId(), movie.getRtId(), movie.getMpaaRating(), movie.getRating(), movie.getRuntime(), movie.getReleaseDate(), 
					movie.getYear(), movie.getPosterUrl(), oldBean.getId() );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}



	public List< MoviePrefixCounts > listMoviePrefixes() throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuery( new MoviePrefixCountsObjectFactory(), "movie_list_prefixes.sql" );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public List< Movie > listMovies() throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuery( new MovieObjectFactory(), "movie_list.sql" );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public void watchMovie( int movieId ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "movie_watch.sql", new Timestamp( System.currentTimeMillis() ), movieId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public void assignFile( int movieId, String file, long size ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "movie_assign_file.sql", movieId, "vfs://" + file, size );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( "Failed to assign file", e );
		}
	}

	public void unassignFile( int linkId ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "unassign_file.sql", linkId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public List< MediaLink > getMovieLinks( int movieId ) throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuery( new MediaLinkObjectFactory(), "movie_list_links.sql", movieId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}



}
