package com.intirix.openmm.server.mt.technical.beans;

import java.io.Serializable;
import java.util.Properties;

import org.simpleframework.xml.Default;

@Default
public abstract class RootFolder implements Serializable, Cloneable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String mountPoint = "";
	
	private String url = "";

	public int getId()
	{
		return id;
	}

	public void setId( int id )
	{
		this.id = id;
	}
	
	
	
	public String getMountPoint()
	{
		return mountPoint;
	}

	public void setMountPoint( String mountPoint )
	{
		this.mountPoint = mountPoint;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl( String url )
	{
		this.url = url;
	}

	/**
	 * Load properties from the database into the object
	 * @param props
	 */
	protected abstract void loadConfigFromProperties( Properties props );
	
	/**
	 * Get properties so they can be stored into the database
	 * @return
	 */
	protected abstract Properties createPropertiesFromConfig();
	
	protected void applyProperties( RootFolder other )
	{
		other.id = id;
		other.url = url;
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
	
}
