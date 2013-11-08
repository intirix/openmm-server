package com.intirix.openmm.server.mt.technical;

import java.util.List;

import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;

/**
 * Low level interface for dealing with shows
 * @author jeff
 *
 */
public interface ShowMidtier
{

	/**
	 * Add a show
	 * @param show
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public int addShow( Show show ) throws OpenMMMidtierException;
	
	/**
	 * List shows
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public List< Show > listShows() throws OpenMMMidtierException;
	
	/**
	 * Get a show
	 * @param id
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public Show getShow( int id ) throws OpenMMMidtierException;
	
	/**
	 * Update a show
	 * @param oldBean
	 * @param newBean
	 * @throws OpenMMMidtierException
	 */
	public void updateShow( Show oldBean, Show newBean ) throws OpenMMMidtierException;
	
	/**
	 * Add a season
	 * @param season
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public int addSeason( Season season ) throws OpenMMMidtierException;
	
	/**
	 * List seasons
	 * @param showId
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public List< Season > listSeasons( int showId ) throws OpenMMMidtierException;
	
	/**
	 * Get a season by id
	 * @param seasonId
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public Season getSeason( int seasonId ) throws OpenMMMidtierException;
	
	/**
	 * Update a season
	 * @param oldBean
	 * @param newBean
	 * @throws OpenMMMidtierException
	 */
	public void updateSeason( Season oldBean, Season newBean ) throws OpenMMMidtierException;
	
	/**
	 * Add an episode
	 * @param episode
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public int addEpisode( Episode episode ) throws OpenMMMidtierException;
	
	/**
	 * List episodes
	 * @param seasonId
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public List< Episode > listEpisodes( int seasonId ) throws OpenMMMidtierException;
	
	/**
	 * Get an episode by id
	 * @param epid
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public Episode getEpisode( int epid ) throws OpenMMMidtierException;
	
	/**
	 * Update an episode
	 * @param oldBean
	 * @param newBean
	 * @throws OpenMMMidtierException
	 */
	public void updateEpisode( Episode oldBean, Episode newBean ) throws OpenMMMidtierException;
	
	/**
	 * Update the database with count and time info
	 * @param episodeId
	 * @throws OpenMMMidtierException
	 */
	public void watchEpisode( int episodeId ) throws OpenMMMidtierException;
	
	/**
	 * Assign a file to an episode
	 * @param episodeId
	 * @param file
	 * @param size size of the file
	 * @throws OpenMMMidtierException
	 */
	public void assignFile( int episodeId, String file, long size ) throws OpenMMMidtierException;
}
