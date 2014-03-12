package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.api.beans.SearchResult;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class SearchBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String query = "";
	
	private SearchResult[] results;

	public SearchResult[] getResults()
	{
		return results;
	}

	public void setResults( SearchResult[] results )
	{
		this.results = results;
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery( String query )
	{
		if ( query != null )
		{
			this.query = query;
		}
	}
	
	
	
	

}
