package com.intirix.openmm.server.mt.app;

import java.util.ArrayList;
import java.util.List;

import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.EpisodeDetails;
import com.intirix.openmm.server.api.beans.MediaLink;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.SeasonDetails;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ShowMidtier;
import com.intirix.openmm.server.mt.technical.beans.EpisodeLinkCounts;
import com.intirix.openmm.server.mt.technical.beans.SeasonEpisodeCounts;

/**
 * This class is basically a wrapper around the technical layer.  This class has to be in the
 * application layer to allow it to call the WebCacheApp midtier.  This class also has some
 * handle helper methods
 * @author jeff
 *
 */
public class ShowAppImpl implements ShowApp
{

	private ShowMidtier showMidtier;

	private WebCacheApp webCacheApp;




	public ShowMidtier getShowMidtier()
	{
		return showMidtier;
	}

	public void setShowMidtier( ShowMidtier showMidtier )
	{
		this.showMidtier = showMidtier;
	}

	public WebCacheApp getWebCacheApp()
	{
		return webCacheApp;
	}

	public void setWebCacheApp( WebCacheApp webCacheApp )
	{
		this.webCacheApp = webCacheApp;
	}

	public int addShow( Show show ) throws OpenMMMidtierException
	{
		return showMidtier.addShow( show );
	}

	public List< Show > listShows() throws OpenMMMidtierException
	{
		final List< Show > shows = showMidtier.listShows();
		final List< Show > ret = new ArrayList< Show >( shows.size() );

		for ( final Show show: shows )
		{
			try
			{
				final Show bean = (Show)show.clone();
				bean.setBannerPath( webCacheApp.getWebCacheUrl( bean.getBannerPath() ) );
				ret.add( bean );
			}
			catch ( CloneNotSupportedException e )
			{
				// ignore
			}
		}

		return ret;
	}

	public Show getShow( int id ) throws OpenMMMidtierException
	{
		try
		{
			final Show show = (Show)showMidtier.getShow( id ).clone();
			show.setBannerPath( webCacheApp.getWebCacheUrl( show.getBannerPath() ) );
			return show;
		}
		catch ( CloneNotSupportedException e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public void updateShow( Show oldBean, Show newBean ) throws OpenMMMidtierException
	{
		showMidtier.updateShow( oldBean, newBean );
	}

	public int addSeason( Season season ) throws OpenMMMidtierException
	{
		return showMidtier.addSeason( season );
	}

	public List< Season > listSeasons( int showId ) throws OpenMMMidtierException
	{
		return showMidtier.listSeasons( showId );
	}
	
	public List< SeasonDetails > listSeasonDetails( int showId ) throws OpenMMMidtierException
	{
		final List< Season > seasons = listSeasons( showId );
		final List< SeasonEpisodeCounts > counts = showMidtier.listSeasonEpisodeCounts( showId );
		final List< SeasonDetails > details = new ArrayList< SeasonDetails >( seasons.size() );
		
		for ( final Season season: seasons )
		{
			final SeasonDetails detail = new SeasonDetails();
			
			detail.setSeason( season );
			
			// search for the counts object that matches the season
			// this is n-squared, but the cardinality is pretty low
			// so, o well
			for ( final SeasonEpisodeCounts sec: counts )
			{
				if ( sec.getSeasonId() == season.getId() )
				{
					detail.setNumEpisodes( sec.getNumEpisodes() );
					detail.setNumEpisodesAvailable( sec.getNumEpisodesAvailable() );
				}
			}
			
			details.add( detail );
		}
		
		return details;
	}

	public Season getSeason( int showId, int seasonNumber ) throws OpenMMMidtierException
	{
		for ( final Season season: listSeasons( showId ) )
		{
			if ( season.getNumber() == seasonNumber )
			{
				return season;
			}
		}
		return null;
	}
	
	public Season getSeason( int seasonId ) throws OpenMMMidtierException
	{
		return showMidtier.getSeason( seasonId );
	}

	public void updateSeason( Season oldBean, Season newBean ) throws OpenMMMidtierException
	{
		showMidtier.updateSeason( oldBean, newBean );
	}

	public int addEpisode( Episode episode ) throws OpenMMMidtierException
	{
		return showMidtier.addEpisode( episode );
	}

	public List< Episode > listEpisodes( int seasonId ) throws OpenMMMidtierException
	{
		final List< Episode > episodes = showMidtier.listEpisodes( seasonId );
		final List< Episode > ret = new ArrayList< Episode >( episodes.size() );
		for ( final Episode episode: episodes )
		{
			try
			{
				final Episode bean = (Episode)episode.clone();
				bean.setScreenshotPath( webCacheApp.getWebCacheUrl( bean.getScreenshotPath() ) );
				ret.add( bean );
			}
			catch ( CloneNotSupportedException e )
			{
				// ignore
			}
		}
		return ret;
	}
	
	
	
	public List< EpisodeDetails > listEpisodeDetails( int seasonId ) throws OpenMMMidtierException
	{
		final List< Episode > episodes = showMidtier.listEpisodes( seasonId );
		final List< EpisodeLinkCounts > counts = showMidtier.listEpisodeLinkCounts( seasonId );
		final List< EpisodeDetails> details = new ArrayList< EpisodeDetails >( episodes.size() );
		
		for ( final Episode episode: episodes )
		{
			final EpisodeDetails detail = new EpisodeDetails();
			detail.setEpisode( episode );
			
			for ( final EpisodeLinkCounts elc: counts )
			{
				if ( elc.getEpisodeId() == episode.getId() )
				{
					detail.setNumExternalLinks( elc.getNumExternalLinks() );
					detail.setNumInternalLinks( elc.getNumInternalLinks() );
				}
			}
			
			details.add( detail );
		}
		
		return details;
	}

	public Episode getEpisode( int showId, int seasonNumber, int epNum ) throws OpenMMMidtierException
	{
		final Season season = getSeason( showId, seasonNumber );
		final List< Episode > episodes = listEpisodes( season.getId() );
		for ( final Episode episode: episodes )
		{
			if ( episode.getEpNum() == epNum )
			{
				return episode;
			}
		}
		return null;
	}
	
	public Episode getEpisode( int epid ) throws OpenMMMidtierException
	{
		Episode bean;
		try
		{
			bean = (Episode)showMidtier.getEpisode( epid ).clone();
		}
		catch ( CloneNotSupportedException e )
		{
			throw new OpenMMMidtierException( e );
		}
		bean.setScreenshotPath( webCacheApp.getWebCacheUrl( bean.getScreenshotPath() ) );
		return bean;
	}
	
	

	public EpisodeDetails getEpisodeDetails( int showId, int seasonNumber, int epNum ) throws OpenMMMidtierException
	{
		final EpisodeDetails details = new EpisodeDetails();
		details.setEpisode( getEpisode( showId, seasonNumber, epNum ) );
		details.setLinks( showMidtier.getEpisodeLinks( details.getEpisode().getId() ).toArray( new MediaLink[]{} ) );
		
		countLinks( details );
		
		return details;
	}

	public EpisodeDetails getEpisodeDetails( int epid ) throws OpenMMMidtierException
	{
		final EpisodeDetails details = new EpisodeDetails();
		details.setEpisode( getEpisode( epid ) );
		details.setLinks( showMidtier.getEpisodeLinks( epid ).toArray( new MediaLink[]{} ) );
		
		countLinks( details );
		
		return details;
	}

	private void countLinks( final EpisodeDetails details )
	{
		for ( final MediaLink link: details.getLinks() )
		{
			if ( link.isAvailable() )
			{
				if ( link.isInternal() )
				{
					details.setNumInternalLinks( details.getNumInternalLinks() + 1 );
				}
				else
				{
					details.setNumExternalLinks( details.getNumExternalLinks() + 1 );
				}
			}
		}
	}

	public void updateEpisode( Episode oldBean, Episode newBean ) throws OpenMMMidtierException
	{
		showMidtier.updateEpisode( oldBean, newBean );
	}

	public void watchEpisode( int episodeId ) throws OpenMMMidtierException
	{
		showMidtier.watchEpisode( episodeId );
	}

	public void assignFile( int episodeId, String file, long size ) throws OpenMMMidtierException
	{
		showMidtier.assignFile( episodeId, file, size );
	}

}
