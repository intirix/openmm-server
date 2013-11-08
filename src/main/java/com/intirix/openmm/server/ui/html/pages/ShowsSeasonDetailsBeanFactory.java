package com.intirix.openmm.server.ui.html.pages;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class ShowsSeasonDetailsBeanFactory implements PageBeanFactory
{

	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}


	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		try
		{
			final ShowsSeasonDetailsBean page = new ShowsSeasonDetailsBean();


			final int id = Integer.parseInt( request.getParameter( "sid" ) );
			final int seasonNumber = Integer.parseInt( request.getParameter( "season" ) );
			
			final Show show = runtime.getApplicationLayer().getShowApp().getShow( id );
			final Season season = runtime.getApplicationLayer().getShowApp().getSeason( id, seasonNumber );

			if ( season != null )
			{
				page.setSeason( season );


				final List< Episode > episodes = runtime.getApplicationLayer().getShowApp().listEpisodes( season.getId() );
				page.setEpisodes( episodes.toArray( new Episode[]{} ) );
				
				if ( show.getTvdbId().length() > 0 )
				{
					final String bannerUrl = runtime.getTechnicalLayer().getTvdbMidtier().getSeasonBanner( show.getTvdbId(), seasonNumber ).getUrl();
					page.setBannerPath( runtime.getApplicationLayer().getWebCacheApp().getWebCacheUrl( bannerUrl ) );
				}
			}


			return page;
		}
		catch ( Exception e )
		{
			throw new ServletException( e );
		}
	}

}
