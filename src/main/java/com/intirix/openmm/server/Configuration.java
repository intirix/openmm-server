package com.intirix.openmm.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Global configuration API
 * @author jeff
 *
 */
public class Configuration
{
	
	private int httpPort;
	
	private String databasePath;
	
	private String tvdbKey = "";
	
	private String rottenTomatoesKey = "";
	
	private String webCache;
	
	private String baseUrl = "";

	/**
	 * Load properties from the user's home directory
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Configuration loadFromUserDir() throws IOException
	{
		final Configuration config = new Configuration();
		
		final File f = new File( getConfigFileName() );
		if ( f.exists() )
		{
			final Properties props = new Properties();
			props.load( new BufferedInputStream( new FileInputStream( f ) ) );
			config.loadFromProperties( props );
		}
		else
		{
			config.loadFromProperties( new Properties() );
		}
		
		return config;
	}

	private static String getConfigFileName()
	{
		return System.getProperty( "user.home" ) + "/.openmm-server/config.properties";
	}
	
	/**
	 * Load the settings from a properties file
	 * @param props
	 */
	public void loadFromProperties( Properties props )
	{
		rottenTomatoesKey = props.getProperty( "rottenTomatoesKey", "" );
		tvdbKey = props.getProperty( "tvdbKey", "" );
		baseUrl = props.getProperty( "baseUrl", "" );
		webCache = props.getProperty( "webCache", System.getProperty( "user.home" ) + "/.openmm-server/webCache" );
		databasePath = props.getProperty( "dbPath", System.getProperty( "user.home" ) + "/.openmm-server/db/openmm" );
		httpPort = Integer.parseInt( props.getProperty( "http.port", "3760" ) );
	}

	
	/**
	 * Write the configuration to a properties file
	 * @param props
	 */
	public void writeToProperties( Properties props )
	{
		props.setProperty( "tvdbKey", tvdbKey );
		props.setProperty( "rottenTomatoesKey", rottenTomatoesKey );
		props.setProperty( "webCache", webCache );
		props.setProperty( "baseUrl", baseUrl );
		props.setProperty( "dbPath", databasePath );
		props.setProperty( "http.port", "" + httpPort );
	}
	
	/**
	 * Save to a user's home dir
	 * @throws IOException
	 */
	public void saveToUserDir() throws IOException
	{
		final File f = new File( getConfigFileName() );
		final File dir = f.getParentFile();
		
		if ( !dir.exists() )
		{
			dir.mkdirs();
		}
		
		final Properties props = new Properties();
		writeToProperties( props );
		props.store( new BufferedOutputStream( new FileOutputStream( f ) ), "" );

	}


	/////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Setters/Getters
	//
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Get the path to the database
	 * @return
	 */
	public String getDatabasePath()
	{
		return databasePath;
	}

	public void setDatabasePath( String databasePath )
	{
		this.databasePath = databasePath;
	}

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

	public String getWebCache()
	{
		return webCache;
	}

	public void setWebCache( String webCache )
	{
		this.webCache = webCache;
	}

	public String getBaseUrl()
	{
		return baseUrl;
	}

	public void setBaseUrl( String baseUrl )
	{
		this.baseUrl = baseUrl;
	}

	public String getRottenTomatoesKey()
	{
		return rottenTomatoesKey;
	}

	public void setRottenTomatoesKey( String rottenTomatoesKey )
	{
		this.rottenTomatoesKey = rottenTomatoesKey;
	}


	
	
	
	
	
	
	

}
