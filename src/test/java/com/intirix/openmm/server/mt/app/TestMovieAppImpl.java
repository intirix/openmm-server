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

}
