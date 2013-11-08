package com.intirix.openmm.server.ui.html.pages;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBMidtier;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.TvdbEpisodeBean;
import com.intirix.openmm.server.ui.html.pages.beans.TvdbPageUtil;
import com.omertron.thetvdbapi.model.Banner;
import com.omertron.thetvdbapi.model.Episode;

public class ShowsTvdbSeasonDetailsBeanFactory implements PageBeanFactory
{

	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}


	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final ShowsTvdbSeasonDetailsBean page = new ShowsTvdbSeasonDetailsBean();
		final TVDBMidtier tvdb = runtime.getTechnicalLayer().getTvdbMidtier();


		final String id = request.getParameter( "sid" );
		page.setId( id );
		page.setSeasonNumber( Integer.parseInt( request.getParameter( "season" ) ) );
		page.setName( "Season " + page.getSeasonNumber() );
		
		final Banner banner = tvdb.getSeasonBanner( id, page.getSeasonNumber() );
		if ( banner != null )
		{
			page.setBanner( runtime.getApplicationLayer().getWebCacheApp().getWebCacheUrl( banner.getUrl() ) );
		}
		
		final List< Episode > episodes = tvdb.listSeasonEpisodes( id, page.getSeasonNumber() );
		if ( episodes != null )
		{
			final List< TvdbEpisodeBean > epbeans = new ArrayList< TvdbEpisodeBean >( episodes.size() );
			
			for ( final Episode ep: episodes )
			{
				final TvdbEpisodeBean epbean = TvdbPageUtil.createEpisodeBean( ep );
				
				// try to cache the banner
				if ( epbean.getBanner().length() > 0 )
				{
					epbean.setBanner( runtime.getApplicationLayer().getWebCacheApp().getWebCacheUrl( epbean.getBanner() ) );
				}
				epbeans.add( epbean );
			}
			
			page.setEpisodes( epbeans.toArray( new TvdbEpisodeBean[]{} ) );
		}

		return page;
	}

}
