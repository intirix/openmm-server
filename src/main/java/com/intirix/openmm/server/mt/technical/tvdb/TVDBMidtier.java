package com.intirix.openmm.server.mt.technical.tvdb;

import java.util.List;

import com.omertron.thetvdbapi.model.Banner;
import com.omertron.thetvdbapi.model.Episode;
import com.omertron.thetvdbapi.model.Series;

public interface TVDBMidtier
{
	/**
	 * Set the default language
	 * @param lang
	 */
	public void setLanguage( String lang );
	
	/**
	 * Get the default language
	 * @return
	 */
	public String getLanguage();
	
	/**
	 * Does the app have a key to use
	 * @return
	 */
	public boolean hasKey();

	/**
	 * Get the api key
	 * @return
	 */
	public String getTvdbKey();

	/**
	 * Set the api key
	 * @param tvdbKey
	 */
	public void setTvdbKey( String tvdbKey );
	
	/**
	 * Search for a show
	 * @param search
	 * @return
	 */
	public List< Series > searchShows( String search );
	
	/**
	 * Get the details about a show
	 * @param id
	 * @return
	 */
	public Series getShowDetails( String id );
	
	/**
	 * List seasons of a show
	 * @param id
	 * @return
	 */
	public List< TVDBSeasonBean > listShowSeasons( String id );
	
	/**
	 * Get the banner for a season
	 * @param id
	 * @param season
	 * @return
	 */
	public Banner getSeasonBanner( String id, int season );
	
	/**
	 * List all the episodes in a season
	 * @param id
	 * @param season
	 * @return
	 */
	public List< Episode > listSeasonEpisodes( String id, int season );

	/**
	 * Get details about an episode
	 * @param id
	 * @return
	 */
	public Episode getEpisode( String id );

	/**
	 * Get details about an episode
	 * @param id
	 * @return
	 */
	public Episode getEpisode( String seriesId, int seasonNbr, int episodeNbr );
}
