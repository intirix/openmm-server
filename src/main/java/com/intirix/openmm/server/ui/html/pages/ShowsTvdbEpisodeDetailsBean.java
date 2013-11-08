package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.TvdbEpisodeBean;

@Default
public class ShowsTvdbEpisodeDetailsBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TvdbEpisodeBean episode;

	public TvdbEpisodeBean getEpisode()
	{
		return episode;
	}

	public void setEpisode( TvdbEpisodeBean episode )
	{
		this.episode = episode;
	}
	
	


}
