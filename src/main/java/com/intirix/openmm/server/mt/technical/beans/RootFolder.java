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
	
	private String type = "";

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
	 * Get the type of the folder
	 * @return
	 */
	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	/**
	 * Load properties from the database into the object
	 * @param props
	 */
	public abstract void loadConfigFromProperties( Properties props );
	
	/**
	 * Get properties so they can be stored into the database
	 * @return
	 */
	public abstract Properties createPropertiesFromConfig();
	
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

	@Override
	public boolean equals( Object obj )
	{
		if ( !( obj instanceof RootFolder ) )
		{
			return false;
		}
		
		final RootFolder o = (RootFolder)obj;
		
		if ( !o.getUrl().equals( getUrl() ) || !o.getMountPoint().equals( getMountPoint() ) )
		{
			return false;
		}
		
		final Properties p1 = createPropertiesFromConfig();
		final Properties p2 = o.createPropertiesFromConfig();
		
		for ( final Object k: p1.keySet() )
		{
			if ( !p2.containsKey( k ) )
			{
				return false;
			}
			
			if ( !p1.get( k ).equals( p2.get( k ) ) )
			{
				return false;
			}
		}
		
		for ( final Object k: p2.keySet() )
		{
			if ( !p1.containsKey( k ) )
			{
				return false;
			}
			
			if ( !p1.get( k ).equals( p2.get( k ) ) )
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	
}
