package com.intirix.openmm.server.mt.technical.sql;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.MediaLink;
import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.api.beans.MoviePrefixCounts;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.MovieMidtier;

public class TestMovieMidtierSQL
{
	
	private OpenMMServerRuntime runtime;
	private MovieMidtier midtier;
	private Serializer serializer;
	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		runtime.init();
		midtier = new MovieMidtierSQL( runtime.getDataSource() );
		serializer = new Persister();
	}

	@Test
	public void testMovie() throws Exception
	{
		final Movie movie1 = new Movie();
		movie1.setName( "Simpsons" );
		movie1.setDisplayName( "The Simpsons" );
		int id = midtier.addMovie( movie1 );
		Assert.assertTrue( id >= 0 );
		
		final List< Movie > movies = midtier.listMovies();
		Assert.assertEquals( 1, movies.size() );
		Assert.assertEquals( movie1.getName(), movies.get( 0 ).getName() );
		
		final Movie movie1a = (Movie)movie1.clone();
		movie1a.setDescription( "DESCRIPTION" );
		midtier.updateMovie( movie1, movie1a );
		Assert.assertEquals( movie1a.getDescription(), midtier.listMovies().get( 0 ).getDescription() );
		
		// make sure the prefix uses the name, not the display name
		Assert.assertEquals( "S", midtier.listMoviePrefixes().get( 0 ).getPrefix() );
		
		serializer.write( movies.get( 0 ), System.out );
		
		midtier.watchMovie( id );
		midtier.deleteMovie( id );

	}
	
	@Test
	public void testAssign() throws OpenMMMidtierException
	{
		final Movie movie1 = new Movie();
		movie1.setName( "Simpsons" );
		movie1.setDisplayName( "The Simpsons" );
		int id = midtier.addMovie( movie1 );
		
		midtier.assignFile( id, "/tmp/simpsons.avi", 1024 );
		final List< MediaLink > links = midtier.getMovieLinks( id );
		Assert.assertEquals( 1, links.size() );
		
		midtier.unassignFile( links.get( 0 ).getId() );
	}
	
	@Test
	public void testPrefix() throws OpenMMMidtierException
	{
		final Movie movie1 = new Movie();
		movie1.setName( "Simpsons" );
		movie1.setDisplayName( "The Simpsons" );
		int id1 = midtier.addMovie( movie1 );
		
		final Movie movie2 = new Movie();
		movie2.setName( "Harry Potter 1" );
		movie2.setDisplayName( "Harry Potter 1" );
		int id2 = midtier.addMovie( movie2 );
		
		final Movie movie3 = new Movie();
		movie3.setName( "Harry Potter 2" );
		movie3.setDisplayName( "Harry Potter 2" );
		int id3 = midtier.addMovie( movie3 );
		
		midtier.assignFile( id2, "/tmp/hp1.avi", 1024 );
		
		// validate the link sizes
		Assert.assertEquals( 0, midtier.getMovieLinks( id1 ).size() );
		Assert.assertEquals( 1, midtier.getMovieLinks( id2 ).size() );
		Assert.assertEquals( 0, midtier.getMovieLinks( id3 ).size() );
		
		final List< MoviePrefixCounts > counts = midtier.listMoviePrefixes();
		
		// validate the prefix
		Assert.assertEquals( 2, counts.size() );
		Assert.assertEquals( "H", counts.get( 0 ).getPrefix() );
		Assert.assertEquals( "S", counts.get( 1 ).getPrefix() );

		// validate the number of movies
		Assert.assertEquals( 2, counts.get( 0 ).getNumMovies() );
		Assert.assertEquals( 1, counts.get( 1 ).getNumMovies() );

		// validate the number of available movies
		Assert.assertEquals( 1, counts.get( 0 ).getNumMoviesAvailable() );
		Assert.assertEquals( 0, counts.get( 1 ).getNumMoviesAvailable() );
	}
}
