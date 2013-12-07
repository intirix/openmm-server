package com.intirix.openmm.server.ui.html.pages;

import java.io.ByteArrayOutputStream;

import org.custommonkey.xmlunit.XMLTestCase;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockHttpServletRequest;
import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.MediaLink;
import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.mt.app.MovieApp;
import com.intirix.openmm.server.ui.html.HtmlTemplateEngine;

public class TestMoviesDetails extends XMLTestCase
{
	private OpenMMServerRuntime runtime;
	
	private HtmlTemplateEngine engine;

	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		engine = new HtmlTemplateEngine( runtime, null );
	}

	@Test
	public void testXml() throws Exception
	{
		// pass in id=1
		final MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter( "movieId", "1" );

		final Movie movie = new Movie();
		movie.setName( "Simpsons" );
		movie.setDisplayName( "The Simpsons" );
		
		final MediaLink link1 = new MediaLink();
		link1.setUrl( "URL" );
		movie.setLinks( new MediaLink[]{ link1 } );
		
		final MovieApp movieApp = EasyMock.createMock( MovieApp.class );
		EasyMock.expect( movieApp.getMovieById( 1 ) ).andReturn( movie );
		EasyMock.expect( movieApp.getMovieById( 1 ) ).andReturn( movie );
		EasyMock.replay( movieApp );
		runtime.getApplicationLayer().setMovieApp( movieApp );
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream( 1024 );
		engine.renderPage( "moviesDetails.xml", engine.createPageBean( "moviesDetails.xml", req, null ), buffer );
		//System.out.println( buffer.toString() );
		assertXpathEvaluatesTo( "The Simpsons", "//movie/displayName", buffer.toString() );

		buffer = new ByteArrayOutputStream( 1024 );
		engine.renderPage( "moviesDetails.phtml", engine.createPageBean( "moviesDetails.phtml", req, null ), buffer );
		//System.out.println( buffer.toString() );
		//assertXpathEvaluatesTo( "The Simpsons", "//movie/displayName", buffer.toString() );
		Assert.assertTrue( buffer.toString().contains( movie.getDisplayName() ) );

	}
}
