package com.intirix.openmm.server.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.intirix.openmm.server.OpenMMServerRuntime;

public class QueryServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Log log = LogFactory.getLog( QueryServlet.class );

	private OpenMMServerRuntime runtime;



	public OpenMMServerRuntime getRuntime()
	{
		return runtime;
	}



	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}



	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
	{
		final String uri = req.getRequestURI();
		final String query = uri.replaceFirst( ".*/api/get/", "" );
		final String parts[] = query.split( "/" );
		if ( parts.length >= 2 )
		{
			final StringBuilder classNameBuffer = new StringBuilder( 10 );
			classNameBuffer.append( getClass().getPackage().getName() );
			classNameBuffer.append( ".get." );

			for ( int i = 0; i < parts.length - 1; i++ )
			{
				final String part = parts[ i ];
				if ( part.length() > 1 )
				{
					classNameBuffer.append( part.substring( 0, 1 ).toUpperCase() );
					classNameBuffer.append( part.substring( 1 ) );
				}
			}
			classNameBuffer.append( "Query" );

			final String end = parts[ parts.length - 1 ];
			final String ext = end.replaceFirst( ".*\\.", "" );
			final String queryId = end.replace( '.' + ext, "" );

			try
			{

				final Class< ? > klass = Class.forName( classNameBuffer.toString() );
				if ( GetQuery.class.isAssignableFrom( klass ) )
				{
					final GetQuery queryObject = (GetQuery)klass.newInstance();
					queryObject.setRuntime( runtime );
					final Object response = queryObject.executeQuery( queryId, req );

					if ( "xml".equals( ext ) )
					{
						final Serializer serializer = new Persister();
						serializer.write( response, resp.getOutputStream() );
					}

				}

			}
			catch ( Exception e )
			{
				log.warn( "Failed to process query", e );
			}
		}
		else
		{
			log.debug( "Query too small: " + uri );
		}

	}


}
