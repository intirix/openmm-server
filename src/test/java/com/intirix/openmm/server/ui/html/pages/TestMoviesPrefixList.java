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
import com.intirix.openmm.server.mt.app.MovieApp;
import com.intirix.openmm.server.mt.technical.beans.MoviePrefixCounts;
import com.intirix.openmm.server.ui.html.HtmlTemplateEngine;

public class TestMoviesPrefixList extends XMLTestCase
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
		
		final List< MoviePrefixCounts > list = new ArrayList< MoviePrefixCounts >();
		final MoviePrefixCounts mpc1 = new MoviePrefixCounts();
		mpc1.setPrefix( "C" );
		list.add( mpc1 );
		
		final MovieApp movieApp = EasyMock.createMock( MovieApp.class );
		EasyMock.expect( movieApp.listMoviePrefixes() ).andReturn( list );
		EasyMock.replay( movieApp );
		runtime.getApplicationLayer().setMovieApp( movieApp );
		
		final ByteArrayOutputStream buffer = new ByteArrayOutputStream( 1024 );
		engine.renderPage( "moviesPrefixList.xml", engine.createPageBean( "moviesPrefixList.xml", req, null ), buffer );
		//System.out.println( buffer.toString() );
		assertXpathEvaluatesTo( "C", "//prefix", buffer.toString() );

	}
}
