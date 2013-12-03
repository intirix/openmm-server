package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.RTMovieSearchResultBean;

@Default
public class MoviesRTSearchBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RTMovieSearchResultBean results[] = new RTMovieSearchResultBean[]{};

	public RTMovieSearchResultBean[] getResults()
	{
		return results;
	}

	public void setResults( RTMovieSearchResultBean[] results )
	{
		this.results = results;
	}

	

}
