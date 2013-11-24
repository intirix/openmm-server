package com.intirix.openmm.server.mt.technical.sql;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.MediaLink;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.technical.ShowMidtier;
import com.intirix.openmm.server.mt.technical.beans.EpisodeLinkCounts;
import com.intirix.openmm.server.mt.technical.beans.SeasonEpisodeCounts;

public class TestShowMidtierSQL
{
	
	private OpenMMServerRuntime runtime;
	private ShowMidtier midtier;
	private Serializer serializer;
	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		runtime.init();
		midtier = new ShowMidtierSQL( runtime.getDataSource() );
		serializer = new Persister();
	}

	@Test
	public void testShow() throws Exception
	{
		final Show show1 = new Show();
		show1.setName( "Simpsons" );
		int id = midtier.addShow( show1 );
		Assert.assertTrue( id >= 0 );
		
		final List< Show > shows = midtier.listShows();
		Assert.assertEquals( 1, shows.size() );
		Assert.assertEquals( show1.getName(), shows.get( 0 ).getName() );
		
		final Show show1a = (Show)show1.clone();
		show1a.setDescription( "DESCRIPTION" );
		midtier.updateShow( show1, show1a );
		Assert.assertEquals( show1a.getDescription(), midtier.listShows().get( 0 ).getDescription() );
		
		serializer.write( shows.get( 0 ), System.out );

	}
	
	@Test
	public void testSeason() throws Exception
	{
		final Show show1 = new Show();
		show1.setName( "Simpsons" );
		int showId = midtier.addShow( show1 );
		
		final Season season1 = new Season();
		season1.setName( "Season 1" );
		season1.setShowId( showId );
		
		
		final int seasonId1 = midtier.addSeason( season1 );
		
		final List< Season > seasons = midtier.listSeasons( showId );
		Assert.assertEquals( 1, seasons.size() );
		final Season season1a = seasons.get( 0 );
		Assert.assertEquals( seasonId1, season1a.getId() );
		Assert.assertEquals( season1.getName(), season1a.getName() );
		
		final Season season1b = (Season)season1a.clone();
		season1b.setName( "TEST" );
		
		midtier.updateSeason( season1a, season1b );
		final Season season1c = midtier.listSeasons( showId ).get( 0 );
		Assert.assertEquals( season1b.getName(), season1c.getName() );
		
		final Season season1d = midtier.getSeason( seasonId1 );
		Assert.assertEquals( season1b.getName(), season1d.getName() );
		
		serializer.write( season1a, System.out );

	}
	
	@Test
	public void testEpisode() throws Exception
	{
		final Show show1 = new Show();
		show1.setName( "Simpsons" );
		int showId = midtier.addShow( show1 );
		
		final Season season1 = new Season();
		season1.setName( "Season 1" );
		season1.setShowId( showId );
		
		
		final int seasonId1 = midtier.addSeason( season1 );
		
		
		final Episode episode1 = new Episode();
		episode1.setSeasonId( seasonId1 );
		episode1.setName( "Simpsons Roasting on an Open Fire" );
		episode1.setEpNum( 1 );
		final int episodeId = midtier.addEpisode( episode1 );
		
		final Episode episode1a = midtier.listEpisodes( seasonId1 ).get( 0 );
		
		Assert.assertEquals( episode1.getName(), episode1a.getName() );
		
		
		midtier.watchEpisode( episodeId );
		final Episode episode1b = midtier.listEpisodes( seasonId1 ).get( 0 );
		
		Assert.assertEquals( episode1a.getWatchCount() + 1, episode1b.getWatchCount() );
		
		final Episode episode1c = (Episode)episode1a.clone();
		episode1c.setDescription( "DESCRIPTION" );
		midtier.updateEpisode( episode1a, episode1c );
		
		final Episode episode1d = midtier.listEpisodes( seasonId1 ).get( 0 );
		Assert.assertEquals( episode1c.getDescription(), episode1d.getDescription() );
		
		final Episode episode1e = midtier.getEpisode( episodeId );
		Assert.assertEquals( episode1c.getDescription(), episode1e.getDescription() );

		
		serializer.write( episode1a, System.out );
		serializer.write( episode1b, System.out );
	}
	

	@Test
	public void testAssign() throws Exception
	{
		final Show show1 = new Show();
		show1.setName( "Simpsons" );
		int showId = midtier.addShow( show1 );
		
		final Season season1 = new Season();
		season1.setName( "Season 1" );
		season1.setShowId( showId );
		
		
		final int seasonId1 = midtier.addSeason( season1 );
		
		
		final Episode episode1 = new Episode();
		episode1.setSeasonId( seasonId1 );
		episode1.setName( "Simpsons Roasting on an Open Fire" );
		episode1.setEpNum( 1 );
		final int episodeId = midtier.addEpisode( episode1 );


		midtier.assignFile( episodeId, "/test/blah.avi", 1024 );

	}
	
	@Test
	public void testSeasonEpisodeCounts() throws Exception
	{
		final Show show1 = new Show();
		show1.setName( "Simpsons" );
		int showId = midtier.addShow( show1 );
		
		final Season season1 = new Season();
		season1.setNumber( 1 );
		season1.setName( "Season 1" );
		season1.setShowId( showId );
		
		final Season season2 = new Season();
		season2.setNumber( 2 );
		season2.setName( "Season 2" );
		season2.setShowId( showId );
		
		
		final int seasonId1 = midtier.addSeason( season1 );
		final int seasonId2 = midtier.addSeason( season2 );
		
		
		final Episode episode1 = new Episode();
		episode1.setSeasonId( seasonId1 );
		episode1.setName( "Simpsons Roasting on an Open Fire" );
		episode1.setEpNum( 1 );
		final int episodeId = midtier.addEpisode( episode1 );
		
		final Episode episode2 = new Episode();
		episode2.setSeasonId( seasonId2 );
		episode2.setName( "Simpsons Roasting on an Open Fire" );
		episode2.setEpNum( 1 );
		midtier.addEpisode( episode2 );


		midtier.assignFile( episodeId, "/test/blah.avi", 1024 );
		
		final List< SeasonEpisodeCounts > counts = midtier.listSeasonEpisodeCounts( showId );
		Assert.assertEquals( 2, counts.size() );
		for ( final SeasonEpisodeCounts sec: counts )
		{
			if ( sec.getSeasonId() == seasonId1 )
			{
				Assert.assertEquals( 1, sec.getNumEpisodesAvailable() );
				Assert.assertEquals( 1, sec.getNumEpisodes() );
			}
			else if ( sec.getSeasonId() == seasonId2 )
			{
				Assert.assertEquals( 0, sec.getNumEpisodesAvailable() );
				Assert.assertEquals( 1, sec.getNumEpisodes() );
			}
			else
			{
				// we found a season that we didn't put in
				Assert.fail();
			}
		}
		
		
		
		final List< EpisodeLinkCounts > elcounts = midtier.listEpisodeLinkCounts( seasonId1 );
		Assert.assertEquals( 1, elcounts.size() );
		for ( final EpisodeLinkCounts elc: elcounts )
		{
			if ( elc.getEpisodeId() == episodeId )
			{
				Assert.assertEquals( 1, elc.getNumInternalLinks() );
				Assert.assertEquals( 0, elc.getNumExternalLinks() );
			}
			else
			{
				Assert.fail( "Unknown episode count" );
			}
		}
		
		
		final List< MediaLink > links = midtier.getEpisodeLinks( episodeId );
		Assert.assertEquals( 1, links.size() );
		final MediaLink link = links.get( 0 );
		Assert.assertEquals( "vfs:///test/blah.avi", link.getUrl() );
		
		midtier.unassignFile( link.getId() );
		
		Assert.assertEquals( 0, midtier.getEpisodeLinks( episodeId ).size() );

	}
}
