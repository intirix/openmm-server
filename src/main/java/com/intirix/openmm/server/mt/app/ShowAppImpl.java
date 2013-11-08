package com.intirix.openmm.server.mt.app;

import java.util.ArrayList;
import java.util.List;

import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ShowMidtier;

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
