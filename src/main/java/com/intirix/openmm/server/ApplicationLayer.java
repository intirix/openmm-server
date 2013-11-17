package com.intirix.openmm.server;

import com.intirix.openmm.server.mt.app.ShowApp;
import com.intirix.openmm.server.mt.app.TVDBApp;
import com.intirix.openmm.server.mt.app.WebCacheApp;

/**
 * Application layer container
 * @author jeff
 *
 */
public class ApplicationLayer
{

	private WebCacheApp webCacheApp;
	
	private TVDBApp tvdbApp;
	
	private ShowApp showApp;
	
	public WebCacheApp getWebCacheApp()
	{
		return webCacheApp;
	}

	public void setWebCacheApp( WebCacheApp webCacheApp )
	{
		this.webCacheApp = webCacheApp;
	}

	public TVDBApp getTvdbApp()
	{
		return tvdbApp;
	}

	public void setTvdbApp( TVDBApp tvdbApp )
	{
		this.tvdbApp = tvdbApp;
	}

	public ShowApp getShowApp()
	{
		return showApp;
	}

	public void setShowApp( ShowApp showApp )
	{
		this.showApp = showApp;
	}

	
	
}