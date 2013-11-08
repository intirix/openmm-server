package com.intirix.openmm.server.mt.technical.tvdb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.omertron.thetvdbapi.TheTVDBApi;
import com.omertron.thetvdbapi.model.Banner;
import com.omertron.thetvdbapi.model.Episode;
import com.omertron.thetvdbapi.model.Series;

public class TVDBMidtierImpl implements TVDBMidtier
{

	private String language = "en";

	private String tvdbKey;

	private TheTVDBApi api = null;

	private final Log log = LogFactory.getLog( TVDBMidtierImpl.class );

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage( String language )
	{
		this.language = language;
	}

	/**
	 * Does the app have a key to use
	 * @return
	 */
	public boolean hasKey()
	{
		return tvdbKey != null && tvdbKey.length() > 0;
	}

	public String getTvdbKey()
	{
		return tvdbKey;
	}

	public void setTvdbKey( String tvdbKey )
	{
		this.tvdbKey = tvdbKey;

		if ( tvdbKey.length() > 0 )
		{
			api = new TheTVDBApi( tvdbKey );
		}
	}

	public List< Series > searchShows( String search )
	{
		return api.searchSeries( search, language );
	}

	public Series getShowDetails( String id )
	{
		try
		{
			log.debug( "getShowDetails(" + id +')' );
			return api.getSeries( id, language );
		}
		finally
		{
			log.debug( "getShowDetails(" + id + ") - DONE" );
		}
	}

	/**
	 * List seasons of a show
	 * @param id
	 * @return
	 */
	public List< TVDBSeasonBean > listShowSeasons( String id )
	{
		final Set< Integer > set = new HashSet< Integer >( 16 );
		final List< TVDBSeasonBean > seasons = new ArrayList< TVDBSeasonBean >( 10 );
		final List< Episode > episodes = api.getAllEpisodes( id, language );
		for ( final Episode episode: episodes )
		{
			if ( !set.contains( episode.getSeasonNumber() ) )
			{
				final TVDBSeasonBean bean = new TVDBSeasonBean();
				bean.setSeasonNumber( episode.getSeasonNumber() );
				if ( episode.getSeasonNumber() == 0 )
				{
					bean.setName( "Specials" );
				}
				else
				{
					bean.setName( "Season " + episode.getSeasonNumber() );
				}
				seasons.add( bean );
				set.add( episode.getSeasonNumber() );
			}
		}
		return seasons;
	}

	/**
	 * Get the banner for a season
	 * @param id
	 * @param season
	 * @return
	 */
	public Banner getSeasonBanner( String id, int season )
	{
		final List< Banner > list = api.getBanners( id ).getSeriesList();
		final List< Banner > list2 = new ArrayList< Banner >( list.size() );

		// filter out the banners in other languages
		for ( final Banner b: list )
		{
			if ( getLanguage().equalsIgnoreCase( b.getLanguage() ) )
			{
				list2.add( b );
			}
		}

		if ( list2.size() > 0 )
		{
			// pick a random banner
			int index = (int)( Math.random() * list2.size() );
			if ( index >= list2.size() )
			{
				index = 0;
			}
			return list2.get( index );
		}
		return null;
	}

	/**
	 * List all the episodes in a season
	 * @param id
	 * @param season
	 * @return
	 */
	public List< Episode > listSeasonEpisodes( String id, int season )
	{
		return api.getSeasonEpisodes( id, season, language );
	}

	/**
	 * Get details about an episode
	 * @param id
	 * @return
	 */
	public Episode getEpisode( String id )
	{
		return api.getEpisodeById( id, language );
	}

	/**
	 * Get details about an episode
	 * @param id
	 * @return
	 */
	public Episode getEpisode( String seriesId, int seasonNbr, int episodeNbr )
	{
		return api.getEpisode( seriesId, seasonNbr, episodeNbr, language );
	}
}
