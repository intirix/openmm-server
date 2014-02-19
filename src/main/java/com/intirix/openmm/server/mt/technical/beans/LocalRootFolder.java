package com.intirix.openmm.server.mt.technical.beans;

import java.util.Properties;

public class LocalRootFolder extends RootFolder
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LocalRootFolder()
	{
		setType( "LOCAL" );
	}

	@Override
	public void loadConfigFromProperties( Properties props )
	{
	}

	@Override
	public Properties createPropertiesFromConfig()
	{
		return new Properties();
	}
	
	

	@Override
	public String getType()
	{
		return "LOCAL";
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		final LocalRootFolder ret = new LocalRootFolder();
		
		applyProperties( ret );
		ret.loadConfigFromProperties( createPropertiesFromConfig() );
		
		return ret;
	}

	
}
