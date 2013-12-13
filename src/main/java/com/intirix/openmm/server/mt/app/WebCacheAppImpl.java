package com.intirix.openmm.server.mt.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.WebCacheMidtier;

public class WebCacheAppImpl implements WebCacheApp
{
	/**
	 * Logger
	 */
	private final Log log = LogFactory.getLog( WebCacheAppImpl.class );

	private WebCacheMidtier webCacheMidtier;
	

	public WebCacheMidtier getWebCacheMidtier()
	{
		return webCacheMidtier;
	}

	public void setWebCacheMidtier( WebCacheMidtier webCacheMidtier )
	{
		this.webCacheMidtier = webCacheMidtier;
	}
	
	/**
	 * Get the cached url
	 * @param url
	 * @return
	 */
	public String getWebCacheUrl( String url )
	{
		// make the method null-safe
		if ( url == null || url.length() == 0 )
		{
			return "";
		}
		
		final String ext = url.replaceFirst( ".*\\.", "" );
		try
		{
			return "/openmm/cache/" + getWebCacheMidtier().registerCachableUrl( url ) + '.' + ext;
		}
		catch ( OpenMMMidtierException e )
		{
			log.warn( "Failed to get cache url", e );
			return url;
		}
	}
	
}
