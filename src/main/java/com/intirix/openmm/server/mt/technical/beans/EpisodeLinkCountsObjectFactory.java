package com.intirix.openmm.server.mt.technical.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.intirix.openmm.server.mt.technical.sql.SQLHelper.ObjectFactory;

public class EpisodeLinkCountsObjectFactory implements ObjectFactory< EpisodeLinkCounts >
{

	public EpisodeLinkCounts createObject( ResultSet rs ) throws SQLException
	{
		final EpisodeLinkCounts bean = new EpisodeLinkCounts();
		bean.setEpisodeId( rs.getInt( "EPISODE_ID" ) );
		bean.setNumInternalLinks( rs.getInt( "NUM_LINK_INT" ) );
		bean.setNumExternalLinks( rs.getInt( "NUM_LINK_EXT" ) );
		
		return bean;
	}

}
