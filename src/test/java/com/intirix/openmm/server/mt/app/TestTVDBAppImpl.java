package com.intirix.openmm.server.mt.app;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ShowMidtier;
import com.intirix.openmm.server.mt.technical.sql.ShowMidtierSQL;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBMidtier;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBSeasonBean;
import com.omertron.thetvdbapi.model.Episode;
import com.omertron.thetvdbapi.model.Series;

public class TestTVDBAppImpl
{
	
	private OpenMMServerRuntime runtime;
	private ShowMidtier showMidtier;
	private TVDBApp app;
	
	@Before
	public void setUp() throws Exception
	{
		app = new TVDBAppImpl();
		runtime = MockSystemFactory.createMockRuntime();
		runtime.init();
		showMidtier = new ShowMidtierSQL( runtime.getDataSource() );
		app.setShowMidtier( showMidtier );
		app.setSearchApp( EasyMock.createMock( SearchApp.class ) );

	}


	@Test
	public void testImport() throws OpenMMMidtierException
	{
		final String mockShowId = "5";
		

		
		final Series series = new Series();
		final TVDBSeasonBean season1 = new TVDBSeasonBean();
		final TVDBSeasonBean season2 = new TVDBSeasonBean();
		season1.setSeasonNumber( 1 );
		season2.setSeasonNumber( 2 );
		final List< TVDBSeasonBean > seasons = new ArrayList< TVDBSeasonBean >( 2 );
		seasons.add( season1 );
		seasons.add( season2 );
		
		final Episode episode1 = new Episode();
		final Episode episode2 = new Episode();
		final Episode episode3 = new Episode();
		final Episode episode4 = new Episode();
		
		final List< Episode > s1epList = new ArrayList< Episode >( 2 );
		final List< Episode > s2epList = new ArrayList< Episode >( 2 );
		
		s1epList.add( episode1 );
		s1epList.add( episode2 );
		s2epList.add( episode3 );
		s2epList.add( episode4 );
		
		
		final TVDBMidtier tvdbMidtier = EasyMock.createMock( TVDBMidtier.class );
		EasyMock.expect( tvdbMidtier.getShowDetails( mockShowId ) ).andReturn( series );
		EasyMock.expect( tvdbMidtier.listShowSeasons( mockShowId ) ).andReturn( seasons );
		EasyMock.expect( tvdbMidtier.listSeasonEpisodes( mockShowId, season1.getSeasonNumber() ) ).andReturn( s1epList );
		EasyMock.expect( tvdbMidtier.listSeasonEpisodes( mockShowId, season2.getSeasonNumber() ) ).andReturn( s2epList );
		EasyMock.replay( tvdbMidtier );
		
		
		
		
		
		
		
		app.setTVDBMidtier( tvdbMidtier );
		
		Assert.assertEquals( 0, app.importShow( mockShowId ) );
	}

	
	@Test
	public void testlistShowsThatCanBeUpdated() throws OpenMMMidtierException
	{
		final Show show1 = new Show();
		show1.setActive( true );
		show1.setTvdbId( "" );
		
		final Show show2 = new Show();
		show2.setActive( false );
		show2.setTvdbId( "" );
		
		final Show show3 = new Show();
		show3.setActive( true );
		show3.setTvdbId( "123" );
		
		final Show show4 = new Show();
		show4.setActive( false );
		show4.setTvdbId( "456" );
		
		showMidtier.addShow( show1 );
		showMidtier.addShow( show2 );
		showMidtier.addShow( show3 );
		showMidtier.addShow( show4 );
		
		final List< Show > shows = app.listShowsThatCanBeUpdated();
		Assert.assertEquals( 1, shows.size() );
		Assert.assertEquals( show3, shows.get( 0 ) );
	}
	
	
}
