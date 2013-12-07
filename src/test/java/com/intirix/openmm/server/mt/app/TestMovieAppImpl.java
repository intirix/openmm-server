package com.intirix.openmm.server.mt.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.api.beans.MediaLink;
import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.api.beans.MovieDetails;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.MovieMidtier;

public class TestMovieAppImpl
{

	private MovieAppImpl impl;
	
	@Before
	public void setUp()
	{
		impl = new MovieAppImpl();
		impl.setMovieMidtier( EasyMock.createMock( MovieMidtier.class ) );
		impl.setWebCacheApp( EasyMock.createMock( WebCacheApp.class ) );
	}

	@Test
	public void testGetMovieById() throws OpenMMMidtierException
	{
		final Movie m1 = new Movie();
		m1.setId( 4 );
		m1.setName( "NAME" );
		final List< Movie > list = new ArrayList< Movie >( 1 );
		list.add( m1 );
		
		EasyMock.expect( impl.getMovieMidtier().listMovies() ).andReturn( list );
		EasyMock.expect( impl.getMovieMidtier().getMovieLinks( m1.getId() ) ).andReturn( Arrays.asList( new MediaLink[]{} ) );
		EasyMock.replay( impl.getMovieMidtier() );
		final Movie m2 = impl.getMovieById( m1.getId() );
		Assert.assertEquals( m1.getName(), m2.getName() );
	}

	@Test
	public void testGetMovieByIdNoResults() throws OpenMMMidtierException
	{
		EasyMock.expect( impl.getMovieMidtier().listMovies() ).andReturn( Arrays.asList( new Movie[]{} ) );
		EasyMock.replay( impl.getMovieMidtier() );
		
		try
		{
			impl.getMovieById( 1 );
			Assert.fail( "Code should have thrown an exception" );
		}
		catch ( OpenMMMidtierException e )
		{
			Assert.assertTrue( e.getMessage().contains( "Could not find" ) );
		}
	}
	
	@Test
	public void testListMovieDetails() throws OpenMMMidtierException
	{
		final List< Movie > list = new ArrayList< Movie >( 2 );
		
		final Movie m1 = new Movie();
		m1.setId( 4 );
		m1.setName( "ABC" );
		list.add( m1 );

		final Movie m2 = new Movie();
		m2.setId( 5 );
		m2.setName( "DEF" );
		list.add( m2 );
		
		final List< MediaLink > links = new ArrayList< MediaLink >();
		final MediaLink link1 = new MediaLink();
		link1.setAvailable( true );
		links.add( link1 );

		
		EasyMock.expect( impl.getMovieMidtier().listMovies() ).andReturn( list );
		EasyMock.expect( impl.getMovieMidtier().getMovieLinks( m1.getId() ) ).andReturn( links );
		EasyMock.replay( impl.getMovieMidtier() );
		final List< MovieDetails > details = impl.listMovieDetails( "A" );
		Assert.assertTrue( details.get( 0 ).isAvailable() );
	}

}
