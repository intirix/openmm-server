package com.intirix.openmm.server.mt.app;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.MovieMidtier;
import com.intirix.openmm.server.mt.technical.rottentomatoes.RTMidtier;

/**
 * Rotten Tomatoes Application Layer
 * @author jeff
 *
 */
public interface RTApp
{

	/**
	 * Set the Movie Midtier;
	 * @param MovieMidtier
	 */
	public void setMovieMidtier( MovieMidtier MovieMidtier );
	
	/**
	 * Set the Rotten Tomatoes Midtier
	 * @param rtMidtier
	 */
	public void setRTMidtier( RTMidtier rtMidtier );
	
	/**
	 * Import a movie
	 * @param id
	 */
	public int importMovie( String id )  throws OpenMMMidtierException;
}
