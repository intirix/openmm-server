package com.intirix.openmm.server;

import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.ShowMidtier;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.WebCacheMidtier;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBMidtier;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBMidtierImpl;

/**
 * Technical layer container
 * @author jeff
 *
 */
public class TechnicalLayer
{

	private ConfigMidtier configMidtier;
	
	private WebCacheMidtier webCacheMidtier;
	
	private ShowMidtier showMidtier;
	
	private TVDBMidtier tvdbMidtier = new TVDBMidtierImpl();
	
	private UserMidtier userMidtier;


	public ConfigMidtier getConfigMidtier()
	{
		return configMidtier;
	}

	public void setConfigMidtier( ConfigMidtier configMidtier )
	{
		this.configMidtier = configMidtier;
	}
	
	

	public WebCacheMidtier getWebCacheMidtier()
	{
		return webCacheMidtier;
	}

	public void setWebCacheMidtier( WebCacheMidtier webCacheMidtier )
	{
		this.webCacheMidtier = webCacheMidtier;
	}

	
	
	public ShowMidtier getShowMidtier()
	{
		return showMidtier;
	}

	public void setShowMidtier( ShowMidtier showMidtier )
	{
		this.showMidtier = showMidtier;
	}

	public TVDBMidtier getTvdbMidtier()
	{
		return tvdbMidtier;
	}

	public void setTvdbMidtier( TVDBMidtier tvdbMidtier )
	{
		this.tvdbMidtier = tvdbMidtier;
	}

	public UserMidtier getUserMidtier()
	{
		return userMidtier;
	}

	public void setUserMidtier( UserMidtier userMidtier )
	{
		this.userMidtier = userMidtier;
	}

	
}
