package com.intirix.openmm.server.ui.html.pages;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.custommonkey.xmlunit.XMLTestCase;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockHttpServletRequest;
import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.api.beans.MovieDetails;
import com.intirix.openmm.server.api.beans.MoviePrefixCounts;
import com.intirix.openmm.server.mt.app.MovieApp;
import com.intirix.openmm.server.ui.html.HtmlTemplateEngine;

public class TestMoviesList extends XMLTestCase
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
		req.setParameter( "prefix", "C" );
		
		final List< MovieDetails > list = new ArrayList< MovieDetails >();
		final MovieDetails md1 = new MovieDetails();
		final Movie m1 = new Movie();
		md1.setMovie( m1 );
		m1.setDisplayName( "Casablanca" );
		m1.setName( "Casablanca" );
		md1.setAvailable( true );
		list.add( md1 );
		
		final MovieApp movieApp = EasyMock.createMock( MovieApp.class );
		EasyMock.expect( movieApp.listMovieDetails( "C" ) ).andReturn( list );
		EasyMock.expect( movieApp.listMovieDetails( "C" ) ).andReturn( list );
		EasyMock.replay( movieApp );
		runtime.getApplicationLayer().setMovieApp( movieApp );
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream( 1024 );
		engine.renderPage( "moviesList.xml", engine.createPageBean( "moviesList.xml", req, null ), buffer );
		System.out.println( buffer.toString() );
		assertXpathEvaluatesTo( "Casablanca", "//movie/name", buffer.toString() );
		

		buffer = new ByteArrayOutputStream( 1024 );
		engine.renderPage( "moviesList.phtml", engine.createPageBean( "moviesList.phtml", req, null ), buffer );
		System.out.println( buffer.toString() );
		assertXpathEvaluatesTo( "Casablanca", "//ListViewItem", buffer.toString() );
	}
}
