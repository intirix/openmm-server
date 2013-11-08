package com.intirix.openmm.server.mt.app;

import com.intirix.openmm.server.mt.technical.WebCacheMidtier;

/**
 * Web Cache application layer
 * @author jeff
 *
 */
public interface WebCacheApp
{

	/**
	 * Get the cache midtier
	 * @return
	 */
	public WebCacheMidtier getWebCacheMidtier();

	/**
	 * Set the cache midtier
	 * @param webCacheMidtier
	 */
	public void setWebCacheMidtier( WebCacheMidtier webCacheMidtier );
	
	/**
	 * Get the cached url
	 * @param url
	 * @return
	 */
	public String getWebCacheUrl( String url );
}
