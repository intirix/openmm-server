package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.TvdbShowSearchResultBean;

@Default
public class ShowsTvdbSearchBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TvdbShowSearchResultBean results[] = new TvdbShowSearchResultBean[]{};

	public TvdbShowSearchResultBean[] getResults()
	{
		return results;
	}

	public void setResults( TvdbShowSearchResultBean[] results )
	{
		this.results = results;
	}

	

}
