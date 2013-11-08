package com.intirix.openmm.server.ui.html.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBMidtier;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.TvdbEpisodeBean;
import com.intirix.openmm.server.ui.html.pages.beans.TvdbPageUtil;

public class ShowsTvdbEpisodeDetailsBeanFactory implements PageBeanFactory
{

	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}


	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final ShowsTvdbEpisodeDetailsBean page = new ShowsTvdbEpisodeDetailsBean();
		final TVDBMidtier tvdb = runtime.getTechnicalLayer().getTvdbMidtier();


		final String id = request.getParameter( "epid" );
		final TvdbEpisodeBean epbean = TvdbPageUtil.createEpisodeBean( tvdb.getEpisode( id ) );
		
		// try to cache the banner
		if ( epbean.getBanner().length() > 0 )
		{
			epbean.setBanner( runtime.getApplicationLayer().getWebCacheApp().getWebCacheUrl( epbean.getBanner() ) );
		}

		page.setEpisode( epbean );

		return page;
	}

}
