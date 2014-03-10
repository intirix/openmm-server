package com.intirix.openmm.server.mt.app;

import java.util.List;

import com.intirix.openmm.server.api.beans.SearchResult;
import com.intirix.openmm.server.mt.OpenMMMidtierException;

/**
 * Interface for search application
 * @author jeff
 *
 */
public interface SearchApp
{

	public void setMovieApp( MovieApp movieApp );


	public void setShowApp( ShowApp showApp );

	/**
	 * Recreate the index
	 * @throws OpenMMMidtierException
	 */
	public void reindex() throws OpenMMMidtierException;
	
	/**
	 * Perform a search
	 * @param query
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public List< SearchResult > search( String query ) throws OpenMMMidtierException;
}
