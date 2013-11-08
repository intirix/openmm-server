package com.intirix.openmm.server.ui.html.pages.beans;

import com.intirix.openmm.server.mt.technical.tvdb.TVDBSeasonBean;
import com.omertron.thetvdbapi.model.Episode;

public class TvdbPageUtil
{
	/**
	 * Create a season bean
	 * @param bean
	 * @return
	 */
	public static TvdbSeasonInfoBean createSeasonInfoBean( TVDBSeasonBean bean )
	{
		final TvdbSeasonInfoBean ret = new TvdbSeasonInfoBean();
		ret.setName( bean.getName() );
		ret.setSeasonNumber( bean.getSeasonNumber() );
		
		return ret;
	}
	
	/**
	 * Create an episode beans
	 * @param ep
	 * @return
	 */
	public static TvdbEpisodeBean createEpisodeBean( Episode ep )
	{
		final TvdbEpisodeBean epbean = new TvdbEpisodeBean();
		epbean.setId( ep.getId() );
		epbean.setName( ep.getEpisodeName() );
		epbean.setFirstAired( ep.getFirstAired() );
		epbean.setEpisodeNumber( ep.getEpisodeNumber() );
		epbean.setRating( ep.getRating() );
		epbean.setBanner( ep.getFilename() );
		epbean.setDescription( ep.getOverview() );
		epbean.setSeasonNumber( ep.getSeasonNumber() );
		try
		{
			epbean.setDvdEpisodeNumber( (int)Double.parseDouble( ep.getDvdEpisodeNumber() ) );
		}
		catch ( NumberFormatException e )
		{
			epbean.setDvdEpisodeNumber( epbean.getEpisodeNumber() );
		}
		return epbean;
	}
}
