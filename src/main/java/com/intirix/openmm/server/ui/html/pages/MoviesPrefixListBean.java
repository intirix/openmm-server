package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.mt.technical.beans.MoviePrefixCounts;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class MoviesPrefixListBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MoviePrefixCounts prefixes[] = new MoviePrefixCounts[]{};

	public MoviePrefixCounts[] getPrefixes()
	{
		return prefixes;
	}

	public void setPrefixes( MoviePrefixCounts[] prefixes )
	{
		this.prefixes = prefixes;
	}
	
	

	
}
