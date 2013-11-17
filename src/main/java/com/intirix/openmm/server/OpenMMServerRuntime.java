package com.intirix.openmm.server;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.googlecode.flyway.core.Flyway;
import com.intirix.openmm.server.api.PostActionEngine;
import com.intirix.openmm.server.events.MessageBus;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.ShowApp;
import com.intirix.openmm.server.mt.app.ShowAppImpl;
import com.intirix.openmm.server.mt.app.TVDBApp;
import com.intirix.openmm.server.mt.app.TVDBAppImpl;
import com.intirix.openmm.server.mt.app.WebCacheAppImpl;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;
import com.intirix.openmm.server.mt.technical.sql.ConfigMidtierSQL;
import com.intirix.openmm.server.mt.technical.sql.ShowMidtierSQL;
import com.intirix.openmm.server.mt.technical.sql.WebCacheMidtierSQL;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBApiKeyUpdatedEvent;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBApiKeyUpdatedEventListener;
import com.intirix.openmm.server.vfs.FileSystemBrowser;
import com.intirix.openmm.server.vfs.FileSystemFactory;
import com.intirix.openmm.server.vfs.LocalFileSystem;

/**
 * Runtime that gets shared with all other objects
 * @author jeff
 *
 */
public class OpenMMServerRuntime
{
	/**
	 * Logger
	 */
	private final static Logger log = Logger.getLogger( OpenMMServerRuntime.class );

	static
	{
		try
		{
			Class.forName("org.hsqldb.jdbc.JDBCDriver" );
		}
		catch ( ClassNotFoundException e )
		{
			log.fatal( "Failed to initialize the database driver", e );
		}
	}

	private Configuration config;

	private DataSource dataSource;

	private final ApplicationLayer applicationLayer = new ApplicationLayer();

	private final TechnicalLayer technicalLayer = new TechnicalLayer();

	private PostActionEngine actionEngine = new PostActionEngine();
	
	private final BlockingQueue< Runnable > workQueue = new LinkedBlockingQueue< Runnable >( 100 );
	
	private ResourceBundle bundle = ResourceBundle.getBundle( "OpenmmServerUIBundle" );
	
	private ThreadPoolExecutor pool;

	public void init() throws IOException
	{

		// configure the datasource if none was passed in
		if ( dataSource == null )
		{
			dataSource = new OpenMMDataSource( config );
		}

		log.info( "Initializing the database" );
		// Create the Flyway instance
		Flyway flyway = new Flyway();

		flyway.setDataSource( getDataSource() );

		// Start the migration
		flyway.migrate();

		log.info( "Finished initializing the database" );
		
		pool = new ThreadPoolExecutor( 2, 4, 1, TimeUnit.SECONDS, workQueue );
		pool.prestartCoreThread();

		actionEngine.setRuntime( this );

		getTechnicalLayer().setConfigMidtier( new ConfigMidtierSQL( getDataSource() ) );
		getTechnicalLayer().setWebCacheMidtier( new WebCacheMidtierSQL( getDataSource() ) );
		getTechnicalLayer().setShowMidtier( new ShowMidtierSQL( getDataSource() ) );

		final WebCacheAppImpl webCacheApp = new WebCacheAppImpl();
		webCacheApp.setWebCacheMidtier( getTechnicalLayer().getWebCacheMidtier() );
		getApplicationLayer().setWebCacheApp( webCacheApp );

		final TVDBApp tvdbApp = new TVDBAppImpl();
		tvdbApp.setShowMidtier( getTechnicalLayer().getShowMidtier() );
		tvdbApp.setTVDBMidtier( getTechnicalLayer().getTvdbMidtier() );
		getApplicationLayer().setTvdbApp( tvdbApp );
		
		final ShowApp showApp = new ShowAppImpl();
		showApp.setShowMidtier( getTechnicalLayer().getShowMidtier() );
		showApp.setWebCacheApp( webCacheApp );
		getApplicationLayer().setShowApp( showApp );
		
		if ( config.getTvdbKey().length() > 0 )
		{
			getTechnicalLayer().getTvdbMidtier().setTvdbKey( config.getTvdbKey() );
		}
		
		
		
		wireMessageBus();
	}

	/**
	 * Wire up the message bus to allow various components to talk to each other
	 */
	private void wireMessageBus()
	{
		final MessageBus bus = MessageBus.getInstance();
		bus.addListener( TVDBApiKeyUpdatedEvent.class, new TVDBApiKeyUpdatedEventListener( getTechnicalLayer().getTvdbMidtier() ) );
	}

	public Configuration getConfig()
	{
		return config;
	}

	public void setConfig( Configuration config )
	{
		this.config = config;
	}



	public ApplicationLayer getApplicationLayer()
	{
		return applicationLayer;
	}

	public TechnicalLayer getTechnicalLayer()
	{
		return technicalLayer;
	}

	public DataSource getDataSource()
	{
		return dataSource;
	}

	public void setDataSource( DataSource dataSource )
	{
		this.dataSource = dataSource;
	}

	public PostActionEngine getActionEngine()
	{
		return actionEngine;
	}

	public void setActionEngine( PostActionEngine actionEngine )
	{
		this.actionEngine = actionEngine;
	}

	/**
	 * Get the browser
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public FileSystemBrowser getVFSBrowser() throws OpenMMMidtierException
	{
		final FileSystemBrowser browser = new FileSystemBrowser();
		for ( final RootFolder folder: getTechnicalLayer().getConfigMidtier().listRootFolders() )
		{
			browser.mount( folder.getMountPoint(), new FileSystemFactory().createFileSystem( folder ) );
		}

		return browser;
	}

	/**
	 * Get the browser for the web cache
	 * @return
	 */
	public FileSystemBrowser getWebCacheBrowser()
	{
		final FileSystemBrowser browser = new FileSystemBrowser();
		browser.mount( "/", new LocalFileSystem( config.getWebCache() ) );
		return browser;

	}
	
	/**
	 * Get the text resource bundle
	 * @return
	 */
	public ResourceBundle getTextBundle()
	{
		bundle = ResourceBundle.getBundle( "OpenmmServerUIBundle" );
		return bundle;
	}
	
	/**
	 * Execute a background task
	 * @param r
	 */
	public void executeTask( Runnable r )
	{
		workQueue.add( r );
	}

	public void shutdown()
	{
		if ( pool != null )
		{
			pool.shutdown();
		}
	}

}