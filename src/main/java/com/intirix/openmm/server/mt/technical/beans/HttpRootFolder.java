package com.intirix.openmm.server.mt.technical.beans;

import java.util.Properties;

public class HttpRootFolder extends RootFolder
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;
	
	public HttpRootFolder()
	{
		setType( "HTTP" );
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername( String username )
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	@Override
	public void loadConfigFromProperties( Properties props )
	{
		username = props.getProperty( "username" );
		password = props.getProperty( "password" );
	}

	@Override
	public Properties createPropertiesFromConfig()
	{
		final Properties props = new Properties();
		
		if ( username != null )
		{
			props.setProperty( "username", username );
		}
		if ( password != null )
		{
			props.setProperty( "password", password );
		}
		
		return props;
	}
	

	@Override
	public String getType()
	{
		return "HTTP";
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		final HttpRootFolder ret = new HttpRootFolder();
		applyProperties( ret );
		ret.loadConfigFromProperties( createPropertiesFromConfig() );
		
		return ret;
	}

	
}
