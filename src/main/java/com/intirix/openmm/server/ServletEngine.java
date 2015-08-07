package com.intirix.openmm.server;

import javax.servlet.Servlet;

public interface ServletEngine
{

	public void init( OpenMMServerRuntime runtime, int port );
	
	public void start();
	
	public void addServlet( String uri, Servlet servlet );
}
