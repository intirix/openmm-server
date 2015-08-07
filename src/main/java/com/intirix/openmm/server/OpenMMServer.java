package com.intirix.openmm.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.Servlet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import com.intirix.openmm.server.api.QueryServlet;
import com.intirix.openmm.server.api.UpdateServlet;
import com.intirix.openmm.server.filter.AuthenticationFilter;
import com.intirix.openmm.server.filter.FilterServlet;
import com.intirix.openmm.server.ui.RootRedirectServlet;
import com.intirix.openmm.server.ui.SingleStaticFileServlet;
import com.intirix.openmm.server.ui.StaticResourceServlet;
import com.intirix.openmm.server.ui.html.HtmlTemplateEngineServlet;
import com.intirix.openmm.server.vfs.FileServlet;
import com.intirix.openmm.server.vfs.VFSFileServlet;
import com.intirix.openmm.server.vfs.WebCacheFileServlet;

/**
 * The driver program that actually starts the server
 * @author jeff
 *
 */
public class OpenMMServer
{
	private final Logger log = Logger.getLogger( OpenMMServer.class );

	private final Configuration config;
	private final OpenMMServerRuntime runtime;

	private ServletEngine engine;
	
	private final List< Filter > filters = new ArrayList< Filter >();

	
	public OpenMMServer() throws IOException
	{
		initLogging();

		
		
		
		config = Configuration.loadFromUserDir();
		runtime = new OpenMMServerRuntime();

		runtime.setConfig( config );

		runtime.init();
		
		
//		engine = new TJWSServletEngine();
		engine = new JettyServletEngine();
		engine.init( runtime, config.getHttpPort() );
		initServlets();
		
		initPaths();

	}
	
	private void addSecureServlet( String uri, Servlet servlet )
	{
		engine.addServlet( uri, new FilterServlet( filters, servlet ) );
	}
	
	private void addPublicServlet( String uri, Servlet servlet )
	{
		engine.addServlet( uri, servlet );
	}

	private void initServlets()
	{
		final AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		authenticationFilter.setUserApp( runtime.getApplicationLayer().getUserApp() );
		
		filters.add( authenticationFilter );
		
		
		final HtmlTemplateEngineServlet htmlServlet = new HtmlTemplateEngineServlet( runtime );
		addSecureServlet( "/openmm/html", htmlServlet );
		addSecureServlet( "/openmm/html/*", htmlServlet );

		final Servlet webjarsServlet = new StaticResourceServlet( "/META-INF/resources/webjars" );
		addSecureServlet( "/openmm/staticlib", webjarsServlet );
		addSecureServlet( "/openmm/staticlib/*", webjarsServlet );

		final Servlet staticServlet = new StaticResourceServlet( "/web" );
		addSecureServlet( "/openmm/static", staticServlet );
		addSecureServlet( "/openmm/static/*", staticServlet );
		
		final FileServlet fileServlet = new VFSFileServlet();
		fileServlet.setRuntime( runtime );
		addSecureServlet( "/openmm/download", fileServlet );
		addSecureServlet( "/openmm/download/*", fileServlet );
		
		final UpdateServlet updateServlet = new UpdateServlet();
		updateServlet.setEngine( runtime.getActionEngine() );
		addSecureServlet( "/openmm/api/update", updateServlet );
		
		final QueryServlet queryServlet = new QueryServlet();
		queryServlet.setRuntime( runtime );
		addSecureServlet( "/openmm/api/get", queryServlet );
		addSecureServlet( "/openmm/api/get/*", queryServlet );

		final FileServlet webCacheServlet = new WebCacheFileServlet();
		webCacheServlet.setRuntime( runtime );
		addSecureServlet( "/openmm/cache", webCacheServlet );
		addSecureServlet( "/openmm/cache/*", webCacheServlet );


		addPublicServlet( "/favicon.ico", new SingleStaticFileServlet( "/ic_launcher-web.ico" ) );

		// redirect the root to the website
		addSecureServlet( "/index.html", new RootRedirectServlet() );
		addPublicServlet( "/", new RootRedirectServlet() );
	}


	private void initLogging()
	{
		final File logdir = new File( System.getProperty( "user.home" ) + "/.openmm-server" );
		if ( !logdir.exists() )
		{
			logdir.mkdirs();
		}
		try
		{
			final RollingFileAppender appender = new RollingFileAppender( new PatternLayout( "%d{yyyy-MM-dd HH:mm:ss} %-5p [%t] %c{2} - %m%n" ), logdir.getAbsolutePath() + "/debug.log", true );
			appender.setMaxBackupIndex( 4 );
			appender.setMaximumFileSize( 1024 * 1024 );
			appender.setThreshold( Level.DEBUG );
			Logger.getRootLogger().addAppender( appender );
		}
		catch ( Exception e )
		{
			log.error( "Failed to setup logfile", e );
		}
	}
	
	private void initPaths()
	{
		final File webCacheFile = new File( config.getWebCache() );
		if ( !webCacheFile.exists() )
		{
			webCacheFile.mkdirs();
		}
	}

	public void start()
	{
		engine.start();
	}



	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main( String[] args ) throws IOException
	{
		final OpenMMServer server = new OpenMMServer();
		server.start();
	}

}
