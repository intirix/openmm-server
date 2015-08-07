package com.intirix.openmm.server;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyServletEngine implements ServletEngine
{
	private Server server;
	private ServletContextHandler scontext;


	public void init( OpenMMServerRuntime runtime, int port )
	{
		server = new Server( port );
		
		scontext = new ServletContextHandler( ServletContextHandler.NO_SESSIONS );
		scontext.setContextPath( "/" );
		server.setHandler( scontext );

	}

	public void start()
	{
		try
		{
			server.start();
		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void addServlet( String uri, Servlet servlet )
	{
		scontext.addServlet( new ServletHolder( servlet ), uri );
	}

}
