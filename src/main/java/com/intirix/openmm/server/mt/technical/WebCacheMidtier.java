package com.intirix.openmm.server.mt.technical;

import com.intirix.openmm.server.mt.OpenMMMidtierException;

public interface WebCacheMidtier
{

	/**
	 * Register a url with the cache system
	 * @param url
	 * @return cache hash code
	 * @throws OpenMMMidtierException
	 */
	public String registerCachableUrl( String url ) throws OpenMMMidtierException;
	
	/**
	 * Get the url for the hash code
	 * @param hash
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public String getUrlForHash( String hash ) throws OpenMMMidtierException;
}
