package com.intirix.openmm.server.ui.html.pages;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.SearchResult;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.SearchApp;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class SearchBeanFactory implements PageBeanFactory
{
	
	private final Log log = LogFactory.getLog( SearchBeanFactory.class );
	
	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final SearchBean bean = new SearchBean();
		
		final String query = request.getParameter( "query" );
		if ( query == null )
		{
			bean.setResults( new SearchResult[]{} );
		}
		else
		{
			try
			{
				final SearchApp searchApp = runtime.getApplicationLayer().getSearchApp();
				if ( "on".equalsIgnoreCase( request.getParameter( "reindex" ) ) )
				{
					searchApp.reindex();
				}
				bean.setQuery( query );
				final List< SearchResult > results = searchApp.search( query );
				bean.setResults( results.toArray( new SearchResult[]{} ) );
			}
			catch ( OpenMMMidtierException e )
			{
				log.error( "Failed to perform query", e );
				bean.setActionMessage( e.getMessage() );
			}
		}
		
		
		return bean;
	}

}
