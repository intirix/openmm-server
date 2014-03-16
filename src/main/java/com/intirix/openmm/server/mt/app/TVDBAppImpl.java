package com.intirix.openmm.server.mt.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ShowMidtier;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBMidtier;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBSeasonBean;
import com.omertron.thetvdbapi.model.Series;

public class TVDBAppImpl implements TVDBApp
{
	private SearchApp searchApp;
	
	private ShowMidtier showMidtier;

	private TVDBMidtier tvdbMidtier;

	/**
	 * Logger
	 */
	private final Log log = LogFactory.getLog( TVDBAppImpl.class );


	public void setShowMidtier( ShowMidtier showMidtier )
	{
		this.showMidtier = showMidtier;
	}

	public void setTVDBMidtier( TVDBMidtier tvdbMidtier )
	{
		this.tvdbMidtier = tvdbMidtier;
	}
	
	

	public void setSearchApp( SearchApp searchApp )
	{
		this.searchApp = searchApp;
	}

	public int importShow( String id ) throws OpenMMMidtierException
	{

		// first check if we already imported the show
		// if we did, then update instead of import
		for ( final Show show: showMidtier.listShows() )
		{
			if ( id.equals( show.getTvdbId() ) )
			{
				updateShow( show.getId() );
				return show.getId();
			}
		}

		final Series series = tvdbMidtier.getShowDetails( id );
		final Show show = new Show();
		show.setActive( !"Ended".equalsIgnoreCase( series.getStatus() ) );
		show.setBannerPath( series.getBanner() );
		show.setContentRating( series.getContentRating() );
		show.setDescription( series.getOverview() );
		show.setDisplayName( series.getSeriesName() );
		show.setImdbId( series.getImdbId() );
		show.setName( show.getDisplayName().replaceFirst( "^The ", "" ) );
		show.setTvdbId( series.getId() );
		show.setTvdbLang( series.getLanguage() );
		show.setZap2itId( series.getZap2ItId() );

		log.debug( "Adding show " + show.getName() );

		int showId = showMidtier.addShow( show );

		for ( final TVDBSeasonBean seasonBean: tvdbMidtier.listShowSeasons( id ) )
		{
			addSeason( showId, id, seasonBean );
		}
		searchApp.reindex();

		return showId;
	}

	public void updateShow( int showId ) throws OpenMMMidtierException
	{
		try
		{
			final Show show1 = showMidtier.getShow( showId );


			String tvdbId = show1.getTvdbId();
			final Series series = tvdbMidtier.getShowDetails( tvdbId );
			final Show show  = (Show)show1.clone();
			show.setActive( !"Ended".equalsIgnoreCase( series.getStatus() ) );
			show.setBannerPath( series.getBanner() );

			log.debug( "Updating show " + show.getName() );
			showMidtier.updateShow( show1, show );

			final List< Season > seasons = showMidtier.listSeasons( showId );

			// convert the list into a map
			final Map< Integer, Season > seasonMap = new HashMap< Integer, Season >( 16 );
			for ( final Season season: seasons )
			{
				seasonMap.put( season.getNumber(), season );
			}

			// iterate over the seasons
			for ( final TVDBSeasonBean seasonBean: tvdbMidtier.listShowSeasons( tvdbId ) )
			{
				// check if the season already exists
				if ( seasonMap.containsKey( seasonBean.getSeasonNumber() ) )
				{
					final Season season = seasonMap.get( seasonBean.getSeasonNumber() );

					// get all the episodes from the database
					final List< Episode > episodes = showMidtier.listEpisodes( season.getNumber() );

					// convert the list into a map
					final Map< Integer, Episode > episodeMap = new HashMap< Integer, Episode >( 32 );
					for ( final Episode episode: episodes )
					{
						episodeMap.put( episode.getEpNum(), episode );
					}

					// iterate over the episodes from TVDB
					for ( final com.omertron.thetvdbapi.model.Episode episodeBean: tvdbMidtier.listSeasonEpisodes( tvdbId, season.getNumber() ) )
					{
						if ( episodeMap.containsKey( episodeBean.getEpisodeNumber() ) )
						{
							// update some of the attributes if the episode already exists
							final Episode oldBean = episodeMap.get( episodeBean.getEpisodeNumber() );
							final Episode newBean = (Episode)oldBean.clone();

							newBean.setDescription( episodeBean.getOverview() );
							newBean.setGuests( getGuestList( episodeBean ) );
							newBean.setRating( episodeBean.getRating() );
							newBean.setScreenshotPath( episodeBean.getFilename() );

							log.debug( "Updating episode " + season.getNumber() + 'x' + oldBean.getEpNum() );
							showMidtier.updateEpisode( oldBean, newBean );
						}
						else
						{
							// add the season if it doesn't already exist
							addEpisode( season.getId(), season.getNumber(), episodeBean );
						}
					}
				}
				else
				{
					// add the season if it didn't exist
					addSeason( showId, tvdbId, seasonBean );
				}
			}
			searchApp.reindex();
		}
		catch ( CloneNotSupportedException e )
		{
			throw new OpenMMMidtierException( e );
		}

	}

	/**
	 * Add a season
	 * @param showId
	 * @param tvdbId
	 * @param seasonBean
	 * @throws OpenMMMidtierException
	 */
	private void addSeason( int showId, String tvdbId, final TVDBSeasonBean seasonBean ) throws OpenMMMidtierException
	{
		final Season season = new Season();
		season.setShowId( showId );
		season.setNumber( seasonBean.getSeasonNumber() );
		if ( seasonBean.getSeasonNumber() < 1 )
		{
			season.setName( "Specials" );
		}
		else
		{
			season.setName( "Season " + seasonBean.getSeasonNumber() );
		}
		log.debug( "Adding season " + season.getName() );
		final int seasonId = showMidtier.addSeason( season );
		final int seasonNumber = season.getNumber();

		for ( final com.omertron.thetvdbapi.model.Episode episodeBean: tvdbMidtier.listSeasonEpisodes( tvdbId, seasonBean.getSeasonNumber() ) )
		{
			addEpisode( seasonId, seasonNumber, episodeBean );
		}
	}

	/**
	 * Add an episode
	 * @param seasonId
	 * @param seasonNumber
	 * @param episodeBean
	 * @throws OpenMMMidtierException
	 */
	private void addEpisode( final int seasonId, final int seasonNumber, final com.omertron.thetvdbapi.model.Episode episodeBean ) throws OpenMMMidtierException
	{
		final Episode episode = new Episode();
		episode.setAirDate( episodeBean.getFirstAired() );
		episode.setDescription( episodeBean.getOverview() );
		episode.setGuests( getGuestList( episodeBean ) );
		if ( episodeBean.getDvdEpisodeNumber() == null )
		{
			episode.setDvdNum( episodeBean.getEpisodeNumber() );
		}
		else
		{
			try
			{
				episode.setDvdNum( Integer.parseInt( episodeBean.getDvdEpisodeNumber().replaceFirst( "\\..*", "" ) ) );
			}
			catch ( NumberFormatException e )
			{
				episode.setDvdNum( episodeBean.getEpisodeNumber() );
			}
		}
		episode.setEpNum( episodeBean.getEpisodeNumber() );
		episode.setName( episodeBean.getEpisodeName() );
		episode.setRating( episodeBean.getRating() );
		episode.setScreenshotPath( episodeBean.getFilename() );
		episode.setSeasonId( seasonId );
		episode.setTvdbId( episodeBean.getId() );

		log.debug( "Adding episode " + seasonNumber + 'x' + episode.getEpNum() + " - " + episode.getName() );

		showMidtier.addEpisode( episode );
	}

	public List< Show > listShowsThatCanBeUpdated() throws OpenMMMidtierException
	{
		final List< Show > ret = new ArrayList< Show >( 10 );
		final List< Show > shows = showMidtier.listShows();
		for ( final Show show: shows )
		{
			if ( show.getActive() && show.getTvdbId().length() > 0 )
			{
				ret.add( show );
			}
		}
		return ret;
	}

	public void updateShows() throws OpenMMMidtierException
	{
		for ( final Show show: listShowsThatCanBeUpdated() )
		{
			updateShow( show.getId() );
		}

	}

	private String getGuestList( com.omertron.thetvdbapi.model.Episode episodeBean )
	{
		final StringBuilder guestBuffer = new StringBuilder( 1024 );
		if ( episodeBean.getGuestStars() != null )
		{
			for ( final String guest: episodeBean.getGuestStars() )
			{
				if ( guestBuffer.length() > 0 )
				{
					guestBuffer.append( ", " );
				}
				guestBuffer.append( guest );
			}
		}
		final String guests = guestBuffer.toString();
		return guests;
	}

}
