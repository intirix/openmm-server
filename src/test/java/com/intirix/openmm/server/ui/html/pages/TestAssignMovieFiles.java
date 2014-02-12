package com.intirix.openmm.server.ui.html.pages;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

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
import com.intirix.openmm.server.vfs.FileSystemBrowser;

public class TestAssignMovieFiles extends XMLTestCase
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
		req.setParameter( "path", "/" );
		
		final List< MoviePrefixCounts > plist = new ArrayList< MoviePrefixCounts >();
		final MoviePrefixCounts mpc1 = new MoviePrefixCounts();
		mpc1.setPrefix( "C" );
		plist.add( mpc1 );
		
		final List< MovieDetails > list = new ArrayList< MovieDetails >();
		final MovieDetails md1 = new MovieDetails();
		final Movie m1 = new Movie();
		md1.setMovie( m1 );
		m1.setDisplayName( "Casablanca" );
		m1.setName( "Casablanca" );
		md1.setAvailable( true );
		list.add( md1 );
		
		final MovieApp movieApp = EasyMock.createMock( MovieApp.class );
		EasyMock.expect( movieApp.listMoviePrefixes() ).andReturn( plist );
		EasyMock.expect( movieApp.listMovieDetails( "C" ) ).andReturn( list );
		EasyMock.replay( movieApp );
		runtime.getApplicationLayer().setMovieApp( movieApp );
		
 		FileSystemBrowser browser = runtime.getApplicationLayer().getVfsApp().getBrowser();
 		EasyMock.expect( browser.listFolders( "/" ) ).andReturn( new String[]{ "folder1" } );
 		EasyMock.expect( browser.listFiles( "/" ) ).andReturn( new String[]{ "file1" } );
 		EasyMock.replay( browser );
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream( 1024 );
		engine.renderPage( "assignMovieFiles.xml", engine.createPageBean( "assignMovieFiles.xml", req, null ), buffer );
		System.out.println( buffer.toString() );
		assertXpathEvaluatesTo( "Casablanca", "//movies/movieDetails/movie/name", buffer.toString() );
		assertXpathEvaluatesTo( "folder1", "//folders/string", buffer.toString() );
		assertXpathEvaluatesTo( "file1", "//files/string", buffer.toString() );
		
	}


	@Test
	public void testPhtmlNoMovieSelected() throws Exception
	{
		// pass in id=1
		final MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter( "prefix", "C" );
		req.setParameter( "path", "/" );
		
		final List< MoviePrefixCounts > plist = new ArrayList< MoviePrefixCounts >();
		final MoviePrefixCounts mpc1 = new MoviePrefixCounts();
		mpc1.setPrefix( "C" );
		plist.add( mpc1 );
		
		final List< MovieDetails > list = new ArrayList< MovieDetails >();
		final MovieDetails md1 = new MovieDetails();
		final Movie m1 = new Movie();
		md1.setMovie( m1 );
		m1.setDisplayName( "Casablanca" );
		m1.setName( "Casablanca" );
		md1.setAvailable( true );
		list.add( md1 );
		
		final MovieApp movieApp = EasyMock.createMock( MovieApp.class );
		EasyMock.expect( movieApp.listMoviePrefixes() ).andReturn( plist );
		EasyMock.expect( movieApp.listMovieDetails( "C" ) ).andReturn( list );
		EasyMock.replay( movieApp );
		runtime.getApplicationLayer().setMovieApp( movieApp );
		
 		FileSystemBrowser browser = runtime.getApplicationLayer().getVfsApp().getBrowser();
 		EasyMock.expect( browser.listFolders( "/" ) ).andReturn( new String[]{ "folder1" } );
 		EasyMock.expect( browser.listFiles( "/" ) ).andReturn( new String[]{ "file1" } );
 		EasyMock.replay( browser );
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream( 1024 );

		buffer = new ByteArrayOutputStream( 1024 );
		engine.renderPage( "assignMovieFiles.phtml", engine.createPageBean( "assignMovieFiles.phtml", req, null ), buffer );
		System.out.println( buffer.toString() );
		Assert.assertTrue( buffer.toString().contains( "<h2>C</h2>" ) );
		assertXpathEvaluatesTo( "C", "//div[@id='assignMovieFilesPrefixGroup']//ListViewItem", buffer.toString() );
		assertXpathEvaluatesTo( "Casablanca", "//div[@id='assignMovieFilesMovieGroup']//ListViewItem", buffer.toString() );
	}


	@Test
	public void testPhtmlMovieSelected() throws Exception
	{
		// pass in id=1
		final MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter( "prefix", "C" );
		req.setParameter( "path", "/" );
		req.setParameter( "movieId", "1" );
		
		final List< MoviePrefixCounts > plist = new ArrayList< MoviePrefixCounts >();
		final MoviePrefixCounts mpc1 = new MoviePrefixCounts();
		mpc1.setPrefix( "C" );
		plist.add( mpc1 );
		
		final List< MovieDetails > list = new ArrayList< MovieDetails >();
		final MovieDetails md1 = new MovieDetails();
		final Movie m1 = new Movie();
		md1.setMovie( m1 );
		m1.setId( 1 );
		m1.setDisplayName( "Casablanca" );
		m1.setName( "Casablanca" );
		md1.setAvailable( true );
		list.add( md1 );
		
		final MovieApp movieApp = EasyMock.createMock( MovieApp.class );
		EasyMock.expect( movieApp.listMoviePrefixes() ).andReturn( plist );
		EasyMock.expect( movieApp.listMovieDetails( "C" ) ).andReturn( list );
		EasyMock.expect( movieApp.getMovieById( m1.getId() ) ).andReturn( m1 );
		EasyMock.replay( movieApp );
		runtime.getApplicationLayer().setMovieApp( movieApp );
		
 		FileSystemBrowser browser = runtime.getApplicationLayer().getVfsApp().getBrowser();
 		EasyMock.expect( browser.listFolders( "/" ) ).andReturn( new String[]{ "folder1" } );
 		EasyMock.expect( browser.listFiles( "/" ) ).andReturn( new String[]{ "file1" } );
 		EasyMock.replay( browser );
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream( 1024 );

		buffer = new ByteArrayOutputStream( 1024 );
		engine.renderPage( "assignMovieFiles.phtml", engine.createPageBean( "assignMovieFiles.phtml", req, null ), buffer );
		System.out.println( buffer.toString() );
		Assert.assertTrue( buffer.toString().contains( "<h2>C</h2>" ) );
		assertXpathEvaluatesTo( "C", "//div[@id='assignMovieFilesPrefixGroup']//ListViewItem", buffer.toString() );
		assertXpathEvaluatesTo( "Casablanca", "//div[@id='assignMovieFilesMovieGroup']//ListViewItem", buffer.toString() );
		
		// this line should only appear when the movie is selected
		Assert.assertTrue( buffer.toString().contains( "<h3>Casablanca</h3>" ) );
	}

}
