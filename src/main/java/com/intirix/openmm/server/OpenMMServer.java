package com.intirix.openmm.server;

import java.io.File;
import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import Acme.Serve.Serve;

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

	private final Serve server = new Serve();

	public OpenMMServer() throws IOException
	{
		initLogging();

		
		
		
		config = Configuration.loadFromUserDir();
		runtime = new OpenMMServerRuntime();

		runtime.setConfig( config );

		runtime.init();
		initHttpServer();
		initServlets();
		initPaths();

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

	private void initHttpServer()
	{
		java.util.Properties properties = new java.util.Properties();
		properties.put( "port", config.getHttpPort() );
		properties.setProperty( Acme.Serve.Serve.ARG_NOHUP, "nohup" );
		server.arguments = properties;
	}

	private void initServlets()
	{
		final HtmlTemplateEngineServlet htmlServlet = new HtmlTemplateEngineServlet( runtime );
		server.addServlet( "/html", htmlServlet );
		server.addServlet( "/html/*", htmlServlet );

		final Servlet webjarsServlet = new StaticResourceServlet( "/META-INF/resources/webjars" );
		server.addServlet( "/staticlib", webjarsServlet );
		server.addServlet( "/staticlib/*", webjarsServlet );

		final Servlet staticServlet = new StaticResourceServlet( "/web" );
		server.addServlet( "/static", staticServlet );
		server.addServlet( "/static/*", staticServlet );
		
		final FileServlet fileServlet = new VFSFileServlet();
		fileServlet.setRuntime( runtime );
		server.addServlet( "/download", fileServlet );
		server.addServlet( "/download/*", fileServlet );

		final FileServlet webCacheServlet = new WebCacheFileServlet();
		webCacheServlet.setRuntime( runtime );
		server.addServlet( "/cache", webCacheServlet );
		server.addServlet( "/cache/*", webCacheServlet );


		server.addServlet( "/favicon.ico", new SingleStaticFileServlet( "/ic_launcher-web.ico" ) );

		// redirect the root to the website
		server.addServlet( "/index.html", new RootRedirectServlet() );
		server.addServlet( "/", new RootRedirectServlet() );
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
		server.serve();
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
