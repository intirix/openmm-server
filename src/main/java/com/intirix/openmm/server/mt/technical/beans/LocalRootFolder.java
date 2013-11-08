package com.intirix.openmm.server.mt.technical.beans;

import java.util.Properties;

public class LocalRootFolder extends RootFolder
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void loadConfigFromProperties( Properties props )
	{
	}

	@Override
	protected Properties createPropertiesFromConfig()
	{
		return new Properties();
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		final LocalRootFolder ret = new LocalRootFolder();
		
		applyProperties( ret );
		
		return ret;
	}

	
}
