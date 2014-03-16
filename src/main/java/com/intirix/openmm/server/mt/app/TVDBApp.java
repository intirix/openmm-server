package com.intirix.openmm.server.mt.app;

import java.util.List;

import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ShowMidtier;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBMidtier;

public interface TVDBApp
{
	
	/**
	 * Set the search app
	 * @param searchApp
	 */
	public void setSearchApp( SearchApp searchApp );
	
	/**
	 * Set the show midtier
	 * @param showMidtier
	 */
	public void setShowMidtier( ShowMidtier showMidtier );
	
	/**
	 * Set the tvdb midtier
	 * @param tvdbMidtier
	 */
	public void setTVDBMidtier( TVDBMidtier tvdbMidtier );

	/**
	 * Import a show
	 * @param id
	 * @throws OpenMMMidtierException
	 */
	public int importShow( String id ) throws OpenMMMidtierException;
	
	/**
	 * Update a show with the latest information
	 * @param showId internal show id
	 * @throws OpenMMMidtierException
	 */
	public void updateShow( int showId ) throws OpenMMMidtierException;
	
	/**
	 * List all the shows that can be updated
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public List< Show > listShowsThatCanBeUpdated() throws OpenMMMidtierException;
	
	/**
	 * Update any shows that can be updated
	 * @throws OpenMMMidtierException
	 */
	public void updateShows() throws OpenMMMidtierException;
}
