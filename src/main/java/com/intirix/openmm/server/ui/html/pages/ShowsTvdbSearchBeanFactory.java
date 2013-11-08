package com.intirix.openmm.server.ui.html.pages;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.TvdbShowSearchResultBean;
import com.omertron.thetvdbapi.model.Series;

public class ShowsTvdbSearchBeanFactory implements PageBeanFactory
{

	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}


	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final ShowsTvdbSearchBean page = new ShowsTvdbSearchBean();

		if ( request.getParameter( "query" ) != null )
		{
			try
			{
				final List< Series > results = runtime.getTechnicalLayer().getTvdbMidtier().searchShows( request.getParameter( "query" ) );
				final List< TvdbShowSearchResultBean > list = new ArrayList< TvdbShowSearchResultBean >( results.size() );
				for ( final Series series: results )
				{
					final TvdbShowSearchResultBean result = new TvdbShowSearchResultBean();
					result.setId( series.getId() );
					result.setFirstAirDate( series.getFirstAired() );
					result.setLang( series.getLanguage() );
					result.setDisplayName( series.getSeriesName() );
					list.add( result );
				}
				page.setResults( list.toArray( new TvdbShowSearchResultBean[]{} ) );
			}
			catch ( NullPointerException e )
			{
				page.setActionMessage( "No Results Found" );
			}
		}

		return page;
	}

}
