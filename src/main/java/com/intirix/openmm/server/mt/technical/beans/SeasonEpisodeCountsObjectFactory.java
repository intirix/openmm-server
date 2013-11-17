package com.intirix.openmm.server.mt.technical.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.intirix.openmm.server.mt.technical.sql.SQLHelper.ObjectFactory;

public class SeasonEpisodeCountsObjectFactory implements ObjectFactory< SeasonEpisodeCounts >
{

	public SeasonEpisodeCounts createObject( ResultSet rs ) throws SQLException
	{
		final SeasonEpisodeCounts bean = new SeasonEpisodeCounts();
		bean.setSeasonId( rs.getInt( "SEASON_ID" ) );
		bean.setNumEpisodesAvailable( rs.getInt( "NUM_EP_AVAILABLE" ) );
		bean.setNumEpisodes( rs.getInt( "NUM_EP" ) );
		return bean;
	}

}
