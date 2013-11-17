package com.intirix.openmm.server.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PostActionEngine engine;
	
	

	public void setEngine( PostActionEngine engine )
	{
		this.engine = engine;
	}



	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
	{
		PostAction action;
		try
		{
			action = engine.getAction( req.getParameter( "action" ) );
		}
		catch ( OpenMMAPIException e )
		{
			throw new ServletException( e );
		}
		action.processAction( req );
	}

	
}
