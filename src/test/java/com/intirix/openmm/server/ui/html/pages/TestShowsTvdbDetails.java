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
import com.intirix.openmm.server.mt.technical.tvdb.TVDBMidtier;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBSeasonBean;
import com.intirix.openmm.server.ui.html.HtmlTemplateEngine;
import com.omertron.thetvdbapi.model.Series;

public class TestShowsTvdbDetails extends XMLTestCase
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
		req.setParameter( "id", "1" );
		
		final Series series = new Series();
		series.setSeriesName( "The Simpsons" );
		series.setOverview( "OVERVIEW" );
		series.setRating( "1.0" );
		
		final List< TVDBSeasonBean > seasons = new ArrayList< TVDBSeasonBean >( 1 );
		
		final TVDBMidtier tvdbMidtier = EasyMock.createMock( TVDBMidtier.class );
		EasyMock.expect( tvdbMidtier.getShowDetails( "1" ) ).andReturn( series );
		EasyMock.expect( tvdbMidtier.listShowSeasons( "1" ) ).andReturn( seasons );
		EasyMock.replay( tvdbMidtier );
		runtime.getTechnicalLayer().setTvdbMidtier( tvdbMidtier );
		
		final ByteArrayOutputStream buffer = new ByteArrayOutputStream( 1024 );
		engine.renderPage( "showsTvdbDetails.xml", engine.createPageBean( "showsTvdbDetails.xml", req, null ), buffer );
		assertXpathEvaluatesTo( "The Simpsons", "/showsTvdbDetailsBean/name", buffer.toString() );

	}
}
