package com.intirix.openmm.server.mt.app;

import it.jtomato.gson.Movie;
import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.MovieMidtier;
import com.intirix.openmm.server.mt.technical.rottentomatoes.RTMidtier;
import com.intirix.openmm.server.mt.technical.sql.MovieMidtierSQL;

public class TestRTAppImpl
{

	
	private OpenMMServerRuntime runtime;
	private MovieMidtier movieMidtier;
	private RTApp app;
	
	@Before
	public void setUp() throws Exception
	{
		app = new RTAppImpl();
		runtime = MockSystemFactory.createMockRuntime();
		runtime.init();
		movieMidtier = new MovieMidtierSQL( runtime.getDataSource() );
		app.setMovieMidtier( movieMidtier );
		app.setSearchApp( EasyMock.createMock( SearchApp.class ) );
	}

	@Test
	public void testImport() throws OpenMMMidtierException
	{
		final Movie movie = new Movie();
		movie.id = "1";
		movie.title = "The Simpsons";
		
		final RTMidtier rtmidtier = EasyMock.createMock( RTMidtier.class );
		EasyMock.expect( rtmidtier.getMovieById( "1" ) ).andReturn( movie );
		EasyMock.replay( rtmidtier );
		app.setRTMidtier( rtmidtier );
		
		
		Assert.assertEquals( 0, app.importMovie( "1" ) );
	}
}
