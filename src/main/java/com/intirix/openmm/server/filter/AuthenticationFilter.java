package com.intirix.openmm.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.UserApp;

public class AuthenticationFilter implements Filter
{
	
	private UserApp userApp;
	
	

	public UserApp getUserApp()
	{
		return userApp;
	}

	public void setUserApp( UserApp userApp )
	{
		this.userApp = userApp;
	}

	public void destroy()
	{
	}

	public void doFilter( ServletRequest req, ServletResponse resp, FilterChain chain ) throws IOException,	ServletException
	{
		final HttpServletRequest request = (HttpServletRequest)req;
		final HttpServletResponse response = (HttpServletResponse)resp;
		final String authHeader = request.getHeader( "authorization" );
		if ( authHeader != null && authHeader.startsWith( "Basic" ) )
		{
			final String userpass = authHeader.replace( "Basic ", "" );
			final String decoded = new String( Base64.decodeBase64( userpass ) );
			final String parts[] = decoded.split( ":", 2 );
			final String username = parts[ 0 ];
			final String password = parts[ 1 ];
			
			try
			{
				if ( userApp.authenticateUser( username, password ) )
				{
					try
					{
						userApp.setCurrentUser( username );
						chain.doFilter( req, resp );
					}
					finally
					{
						userApp.setCurrentUser( null );
					}
				}
				else
				{
					response.setHeader( "WWW-Authenticate", "Basic realm=\"OpenMM\"" );
					response.sendError( 401 );
				}
			}
			catch ( OpenMMMidtierException e )
			{
				throw new ServletException( e );
			}
		}
		else
		{
			response.setHeader( "WWW-Authenticate", "Basic realm=\"OpenMM\"" );
			response.sendError( 401 );
		}
	}

	public void init( FilterConfig arg0 ) throws ServletException
	{
	}

}
