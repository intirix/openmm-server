package com.intirix.openmm.server.mt.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ShowMidtier;

public class TestShowAppImpl
{

	private ShowAppImpl impl;
	
	@Before
	public void setUp()
	{
		impl = new ShowAppImpl();
		impl.setShowMidtier( EasyMock.createMock( ShowMidtier.class ) );
		impl.setWebCacheApp( EasyMock.createMock( WebCacheApp.class ) );
	}
	
	/**
	 * addShow() should just pass through to the technical layer
	 * @throws OpenMMMidtierException
	 */
	@Test
	public void testAddShow() throws OpenMMMidtierException
	{
		final Show show = new Show();
		EasyMock.expect( impl.getShowMidtier().addShow( show ) ).andReturn( 3 );
		EasyMock.replay( impl.getShowMidtier() );
		Assert.assertEquals( 3, impl.addShow( show ) );
	}
	
	/**
	 * listShows() should translate the bannerPath
	 * @throws OpenMMMidtierException
	 */
	@Test
	public void testListShows() throws OpenMMMidtierException
	{
		final Show show1 = new Show();
		show1.setName( "The SHOW" );
		show1.setDisplayName( "SHOW" );
		show1.setBannerPath( "A" );
		
		final List< Show > shows = new ArrayList< Show >( 1 );
		shows.add( show1 );
		
		EasyMock.expect( impl.getShowMidtier().listShows() ).andReturn( shows );
		EasyMock.expect( impl.getWebCacheApp().getWebCacheUrl( "A" ) ).andReturn( "B" );
		EasyMock.replay( impl.getShowMidtier(), impl.getWebCacheApp() );
		
		final List< Show > showList = impl.listShows();
		Assert.assertEquals( 1, showList.size() );
		final Show show2 = showList.get( 0 );
		Assert.assertEquals( show1.getName(), show2.getName() );
		Assert.assertEquals( "B", show2.getBannerPath() );
	}
	
	/**
	 * getShow() should translate the bannerPath
	 * @throws OpenMMMidtierException
	 */
	@Test
	public void testGetShow() throws OpenMMMidtierException
	{
		final Show show1 = new Show();
		show1.setName( "The SHOW" );
		show1.setDisplayName( "SHOW" );
		show1.setBannerPath( "A" );

		EasyMock.expect( impl.getShowMidtier().getShow( 4 ) ).andReturn( show1 );
		EasyMock.expect( impl.getWebCacheApp().getWebCacheUrl( "A" ) ).andReturn( "B" );
		EasyMock.replay( impl.getShowMidtier(), impl.getWebCacheApp() );

		final Show show2 = impl.getShow( 4 );
		Assert.assertEquals( show1.getDisplayName(), show2.getDisplayName() );
		Assert.assertEquals( "B", show2.getBannerPath() );
	}
	
	/**
	 * updateShow() should just pass through
	 * @throws OpenMMMidtierException
	 */
	@Test
	public void testUpdateShow() throws OpenMMMidtierException
	{
		final Show show1 = new Show();
		final Show show2 = new Show();
		
		impl.getShowMidtier().updateShow( show1, show2 );
		EasyMock.expectLastCall();
		EasyMock.replay( impl.getShowMidtier() );
		
		impl.updateShow( show1, show2 );
	}
	
	@Test
	public void testAddSeason() throws OpenMMMidtierException
	{
		final Season season1 = new Season();
		
		EasyMock.expect( impl.getShowMidtier().addSeason( season1 ) ).andReturn( 2 );
		EasyMock.replay( impl.getShowMidtier() );
		
		Assert.assertEquals( 2, impl.addSeason( season1 ) );
	}
	
	@Test
	public void testListSeasons() throws OpenMMMidtierException
	{
		EasyMock.expect( impl.listSeasons( 1 ) ).andReturn( Arrays.asList( new Season[]{} ) );
		EasyMock.replay( impl.getShowMidtier() );
		
		Assert.assertNotNull( impl.listSeasons( 1 ) );
	}
	
	/**
	 * getSeason() should list the seasons, then return the instance with
	 * the current season number
	 * @throws OpenMMMidtierException
	 */
	@Test
	public void testGetSeason1() throws OpenMMMidtierException
	{
		final Season season1 = new Season();
		season1.setNumber( 1 );
		final Season season2 = new Season();
		season2.setNumber( 2 );
		
		final List< Season > seasons = new ArrayList< Season >( 2 );
		seasons.add( season1 );
		seasons.add( season2 );
		
		EasyMock.expect( impl.getShowMidtier().listSeasons( 5 ) ).andReturn( seasons );
		EasyMock.replay( impl.getShowMidtier() );
		
		Assert.assertEquals( season2, impl.getSeason( 5, 2 ) );
	}
	
	/**
	 * Test the passthrough variant of getSeason()
	 * @throws OpenMMMidtierException
	 */
	@Test
	public void testGetSeason2() throws OpenMMMidtierException
	{
		final Season season = new Season();
		
		EasyMock.expect( impl.getShowMidtier().getSeason( season.getId() ) ).andReturn( season );
		EasyMock.replay( impl.getShowMidtier() );
		
		Assert.assertEquals( season.getId(), impl.getSeason( season.getId() ).getId() );
	}
	
	@Test
	public void testUpdateSeason() throws OpenMMMidtierException
	{
		final Season season1 = new Season();
		final Season season2 = new Season();

		impl.getShowMidtier().updateSeason( season1, season2 );
		EasyMock.expectLastCall();
		EasyMock.replay( impl.getShowMidtier() );
		
		impl.updateSeason( season1, season2 );
	}
	
	@Test
	public void testAddEpisode() throws OpenMMMidtierException
	{
		final Episode episode = new Episode();
		
		EasyMock.expect( impl.getShowMidtier().addEpisode( episode ) ).andReturn( 6 );
		EasyMock.replay( impl.getShowMidtier() );
		
		Assert.assertEquals( 6, impl.addEpisode( episode ) );
	}
	
	/**
	 * listEpisodes() should translate the screenshot path
	 * @throws OpenMMMidtierException
	 */
	@Test
	public void testListEpisodes() throws OpenMMMidtierException
	{
		final Episode episode = new Episode();
		episode.setScreenshotPath( "A" );
		
		final List< Episode > episodes = new ArrayList< Episode >( 1 );
		episodes.add( episode );
		
		EasyMock.expect( impl.getShowMidtier().listEpisodes( 4 ) ).andReturn( episodes );
		EasyMock.expect( impl.getWebCacheApp().getWebCacheUrl( "A" ) ).andReturn( "B" );
		EasyMock.replay( impl.getShowMidtier(), impl.getWebCacheApp() );
		
		final Episode episode2 = impl.listEpisodes( 4 ).get( 0 );
		Assert.assertEquals( "B", episode2.getScreenshotPath() );
	}
	
	/**
	 * getEpisode() should call a few other methods
	 * it indirectly translates the screenshots and banner paths
	 * @throws OpenMMMidtierException
	 */
	@Test
	public void testGetEpisode1() throws OpenMMMidtierException
	{
		final int showId = 2;
		final Season season1 = new Season();
		season1.setId( 10 ); // random number
		season1.setNumber( 1 );
		
		final List< Season > seasons = new ArrayList< Season >( 1 );
		seasons.add( season1 );
		
		final Episode episode1 = new Episode();
		episode1.setEpNum( 1 );
		final List< Episode > episodes = new ArrayList< Episode >( 1 );
		episodes.add( episode1 );
		
		EasyMock.expect( impl.getShowMidtier().listSeasons( showId ) ).andReturn( seasons );
		EasyMock.expect( impl.getShowMidtier().listEpisodes( season1.getId() ) ).andReturn( episodes );
		EasyMock.expect( impl.getWebCacheApp().getWebCacheUrl( "" ) ).andReturn( "" );
		EasyMock.replay( impl.getShowMidtier(), impl.getWebCacheApp() );
		
		Assert.assertEquals( episode1.getEpNum(), impl.getEpisode( showId, season1.getNumber(), episode1.getEpNum() ).getEpNum() );
	}
	
	/**
	 * Test the passthrough variant of getEpisode()
	 * @throws OpenMMMidtierException
	 */
	@Test
	public void testGetEpisode2() throws OpenMMMidtierException
	{
		final Episode episode1 = new Episode();
		
		EasyMock.expect( impl.getShowMidtier().getEpisode( episode1.getId() ) ).andReturn( episode1 );
		EasyMock.replay( impl.getShowMidtier() );
		
		Assert.assertEquals( episode1.getId(), impl.getEpisode( episode1.getId() ).getId() );
	}
	
	@Test
	public void testUpdateEpisode() throws OpenMMMidtierException
	{
		final Episode episode1 = new Episode();
		final Episode episode2 = new Episode();
		
		impl.getShowMidtier().updateEpisode( episode1, episode2 );
		EasyMock.expectLastCall();
		EasyMock.replay( impl.getShowMidtier() );
		
		impl.updateEpisode( episode1, episode2 );		
	}

	
	@Test
	public void testWatchEpisode() throws OpenMMMidtierException
	{
		impl.getShowMidtier().watchEpisode( 1 );
		EasyMock.expectLastCall();
		EasyMock.replay( impl.getShowMidtier() );
		
		impl.watchEpisode( 1 );	
	}

	@Test
	public void testAssignFile() throws OpenMMMidtierException
	{
		impl.getShowMidtier().assignFile( 1, "file", 2 );
		EasyMock.expectLastCall();
		EasyMock.replay( impl.getShowMidtier() );
		
		impl.assignFile( 1, "file", 2 );
	}
	
}
