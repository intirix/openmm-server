package com.intirix.openmm.server.ui.html.pages;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.technical.tvdb.TVDBSeasonBean;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.TvdbPageUtil;
import com.intirix.openmm.server.ui.html.pages.beans.TvdbSeasonInfoBean;
import com.omertron.thetvdbapi.model.Series;

public class ShowsTvdbDetailsBeanFactory implements PageBeanFactory
{

	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}


	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final ShowsTvdbDetailsBean page = new ShowsTvdbDetailsBean();


		final String id = request.getParameter( "id" );
		page.setId( id );
		final Series series = runtime.getTechnicalLayer().getTvdbMidtier().getShowDetails( id );

		if ( series.getBanner() != null )
		{
			page.setBanner( runtime.getApplicationLayer().getWebCacheApp().getWebCacheUrl( series.getBanner() ) );
		}

		//page.setBanner( series.getBanner() );
		page.setDescription( series.getOverview() );
		page.setName( series.getSeriesName() );
		page.setRating( series.getRating() );

		final List< TvdbSeasonInfoBean > seasons = new ArrayList< TvdbSeasonInfoBean >( 10 );
		for ( final TVDBSeasonBean season: runtime.getTechnicalLayer().getTvdbMidtier().listShowSeasons( id ) )
		{
			seasons.add( TvdbPageUtil.createSeasonInfoBean( season ) );
		}

		page.setSeasons( seasons.toArray( new TvdbSeasonInfoBean[]{} ) );

		return page;
	}

}
