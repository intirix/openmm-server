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
import com.intirix.openmm.server.mt.TimingProxy;
import com.intirix.openmm.server.mt.app.MovieApp;
import com.intirix.openmm.server.mt.app.MovieAppImpl;
import com.intirix.openmm.server.mt.app.RTApp;
import com.intirix.openmm.server.mt.app.RTAppImpl;
import com.intirix.openmm.server.mt.app.ShowApp;
import com.intirix.openmm.server.mt.app.ShowAppImpl;
import com.intirix.openmm.server.mt.app.TVDBApp;
import com.intirix.openmm.server.mt.app.TVDBAppImpl;
import com.intirix.openmm.server.mt.app.UserApp;
import com.intirix.openmm.server.mt.app.UserAppImpl;
import com.intirix.openmm.server.mt.app.VFSApp;
import com.intirix.openmm.server.mt.app.VFSAppCache;
import com.intirix.openmm.server.mt.app.VFSAppImpl;
import com.intirix.openmm.server.mt.app.WebCacheApp;
import com.intirix.openmm.server.mt.app.WebCacheAppImpl;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.MovieMidtier;
import com.intirix.openmm.server.mt.technical.ShowMidtier;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.WebCacheMidtier;
import com.intirix.openmm.server.mt.technical.events.VFSConfigUpdatedEvent;
import com.intirix.openmm.server.mt.technical.impl.UserMidtierAccess;
import com.intirix.openmm.server.mt.technical.impl.UserMidtierValidation;
import com.intirix.openmm.server.mt.technical.impl.cache.ConfigMidtierCache;
import com.intirix.openmm.server.mt.technical.impl.cache.MovieMidtierCache;
import com.intirix.openmm.server.mt.technical.impl.cache.UserMidtierCache;
import com.intirix.openmm.server.mt.technical.impl.cache.WebCacheMidtierCache;
import com.intirix.openmm.server.mt.technical.rottentomatoes.RottenTomatoesApiKeyUpdatedEvent;
import com.intirix.openmm.server.mt.technical.rottentomatoes.RottenTomatoesApiKeyUpdatedEventListener;
import com.intirix.openmm.server.mt.technical.sql.ConfigMidtierSQL;
import com.intirix.openmm.server.mt.technical.sql.MovieMidtierSQL;
import com.intirix.openmm.server.mt.technical.sql.ShowMidtierSQL;
import com.intirix.openmm.server.mt.technical.sql.UserMidtierSQL;
import com.intirix.openmm.server.mt.technical.sql.WebCacheMidtierSQL;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBApiKeyUpdatedEvent;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBApiKeyUpdatedEventListener;
import com.intirix.openmm.server.vfs.FileSystemBrowser;
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

		getTechnicalLayer().setConfigMidtier( TimingProxy.create( ConfigMidtier.class, new ConfigMidtierCache( TimingProxy.create( ConfigMidtier.class, new ConfigMidtierSQL( getDataSource() ) ) ) ) );
		getTechnicalLayer().setWebCacheMidtier( TimingProxy.create( WebCacheMidtier.class, new WebCacheMidtierCache( TimingProxy.create( WebCacheMidtier.class, new WebCacheMidtierSQL( getDataSource() ) ) ) ) );
		getTechnicalLayer().setShowMidtier( TimingProxy.create( ShowMidtier.class, new ShowMidtierSQL( getDataSource() ) ) );
		getTechnicalLayer().setUserMidtier(
			TimingProxy.create(	UserMidtier.class, new UserMidtierAccess(
							TimingProxy.create(	UserMidtier.class, new UserMidtierValidation(
											TimingProxy.create( UserMidtier.class, new UserMidtierCache(
															TimingProxy.create( UserMidtier.class, new UserMidtierSQL( getDataSource() ) ) ) ) ) ) ) ) );
		getTechnicalLayer().setMovieMidtier( TimingProxy.create( MovieMidtier.class, new MovieMidtierCache( TimingProxy.create( MovieMidtier.class, new MovieMidtierSQL( getDataSource() ) ) ) ) );
		
		final VFSApp vfsApp = new VFSAppImpl();
		final VFSAppCache vfsAppCache = new VFSAppCache( vfsApp );
		vfsAppCache.setConfigMidtier( getTechnicalLayer().getConfigMidtier() );
		MessageBus.getInstance().addListener( VFSConfigUpdatedEvent.class, vfsAppCache );
		getApplicationLayer().setVfsApp( vfsAppCache );

		final WebCacheAppImpl webCacheApp = new WebCacheAppImpl();
		webCacheApp.setWebCacheMidtier( getTechnicalLayer().getWebCacheMidtier() );
		getApplicationLayer().setWebCacheApp( TimingProxy.create( WebCacheApp.class, webCacheApp ) );

		final TVDBApp tvdbApp = new TVDBAppImpl();
		tvdbApp.setShowMidtier( getTechnicalLayer().getShowMidtier() );
		tvdbApp.setTVDBMidtier( getTechnicalLayer().getTvdbMidtier() );
		getApplicationLayer().setTvdbApp( TimingProxy.create( TVDBApp.class, tvdbApp ) );
		
		final ShowApp showApp = new ShowAppImpl();
		showApp.setShowMidtier( getTechnicalLayer().getShowMidtier() );
		showApp.setWebCacheApp( webCacheApp );
		getApplicationLayer().setShowApp( TimingProxy.create( ShowApp.class, showApp ) );
		
		final MovieApp movieApp = new MovieAppImpl();
		movieApp.setMovieMidtier( getTechnicalLayer().getMovieMidtier() );
		movieApp.setWebCacheApp( webCacheApp );
		getApplicationLayer().setMovieApp( TimingProxy.create( MovieApp.class, movieApp ) );
		
		final RTApp rtApp = new RTAppImpl();
		rtApp.setMovieMidtier( getTechnicalLayer().getMovieMidtier() );
		rtApp.setRTMidtier( getTechnicalLayer().getRtMidtier() );
		getApplicationLayer().setRtApp( TimingProxy.create( RTApp.class, rtApp ) );
		
		if ( config.getTvdbKey().length() > 0 )
		{
			getTechnicalLayer().getTvdbMidtier().setTvdbKey( config.getTvdbKey() );
		}
		if ( config.getRottenTomatoesKey().length() > 0 )
		{
			getTechnicalLayer().getRtMidtier().setKey( config.getRottenTomatoesKey() );
		}
		
		final UserApp userApp = new UserAppImpl();
		userApp.setUserMidtier( getTechnicalLayer().getUserMidtier() );
		getApplicationLayer().setUserApp( TimingProxy.create( UserApp.class, userApp ) );
		
		
		wireMessageBus();
	}
	
	/**
	 * Wire up the message bus to allow various components to talk to each other
	 */
	private void wireMessageBus()
	{
		final MessageBus bus = MessageBus.getInstance();
		bus.addListener( TVDBApiKeyUpdatedEvent.class, new TVDBApiKeyUpdatedEventListener( getTechnicalLayer().getTvdbMidtier() ) );
		bus.addListener( RottenTomatoesApiKeyUpdatedEvent.class, new RottenTomatoesApiKeyUpdatedEventListener( getTechnicalLayer().getRtMidtier() ) );
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
