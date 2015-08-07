package com.intirix.openmm.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.Servlet;

import Acme.Serve.Serve;

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
 * Engine that uses the TinyJavaWebServer Servlet Engine
 * @author jeff
 *
 */
public class TJWSServletEngine implements ServletEngine
{
	private OpenMMServerRuntime runtime;
	
	private Serve server = null;

	private final List< Filter > filters = new ArrayList< Filter >();


	public void init( OpenMMServerRuntime runtime, int port )
	{
		this.runtime = runtime;
		java.util.Properties properties = new java.util.Properties();
		properties.put( "port", port );
		properties.setProperty( Acme.Serve.Serve.ARG_NOHUP, "nohup" );
		server = new Serve( properties, System.out );
		server.arguments = properties;
		
		initServlets();
	}


	public void start()
	{
		server.serve();
	}
	
	


	public void addServlet( String uri, Servlet servlet )
	{
		server.addServlet( uri, servlet );
	}


	private void mapServlet( String uri, Servlet servlet )
	{
		server.addServlet( uri, new FilterServlet( filters, servlet ) );
	}

	private void initServlets()
	{
		final AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		authenticationFilter.setUserApp( runtime.getApplicationLayer().getUserApp() );
		
		filters.add( authenticationFilter );
		
		
		final HtmlTemplateEngineServlet htmlServlet = new HtmlTemplateEngineServlet( runtime );
		mapServlet( "/openmm/html", htmlServlet );
		mapServlet( "/openmm/html/*", htmlServlet );

		final Servlet webjarsServlet = new StaticResourceServlet( "/META-INF/resources/webjars" );
		mapServlet( "/openmm/staticlib", webjarsServlet );
		mapServlet( "/openmm/staticlib/*", webjarsServlet );

		final Servlet staticServlet = new StaticResourceServlet( "/web" );
		mapServlet( "/openmm/static", staticServlet );
		mapServlet( "/openmm/static/*", staticServlet );
		
		final FileServlet fileServlet = new VFSFileServlet();
		fileServlet.setRuntime( runtime );
		mapServlet( "/openmm/download", fileServlet );
		mapServlet( "/openmm/download/*", fileServlet );
		
		final UpdateServlet updateServlet = new UpdateServlet();
		updateServlet.setEngine( runtime.getActionEngine() );
		mapServlet( "/openmm/api/update", updateServlet );
		
		final QueryServlet queryServlet = new QueryServlet();
		queryServlet.setRuntime( runtime );
		mapServlet( "/openmm/api/get", queryServlet );
		mapServlet( "/openmm/api/get/*", queryServlet );

		final FileServlet webCacheServlet = new WebCacheFileServlet();
		webCacheServlet.setRuntime( runtime );
		mapServlet( "/openmm/cache", webCacheServlet );
		mapServlet( "/openmm/cache/*", webCacheServlet );


		server.addServlet( "/favicon.ico", new SingleStaticFileServlet( "/ic_launcher-web.ico" ) );

		// redirect the root to the website
		mapServlet( "/index.html", new RootRedirectServlet() );
		server.addServlet( "/", new RootRedirectServlet() );
	}


}
