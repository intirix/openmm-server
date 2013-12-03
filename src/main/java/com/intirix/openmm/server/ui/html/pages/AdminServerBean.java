package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.ui.html.PageData;

@Default
public class AdminServerBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private int httpPort;
	
	private String tvdbKey = "";
	
	private String rtKey = "";

	public int getHttpPort()
	{
		return httpPort;
	}

	public void setHttpPort( int httpPort )
	{
		this.httpPort = httpPort;
	}

	public String getTvdbKey()
	{
		return tvdbKey;
	}

	public void setTvdbKey( String tvdbKey )
	{
		this.tvdbKey = tvdbKey;
	}

	public String getRtKey()
	{
		return rtKey;
	}

	public void setRtKey( String rtKey )
	{
		this.rtKey = rtKey;
	}
	
	
}
