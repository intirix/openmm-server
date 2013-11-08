package com.intirix.openmm.server.mt.technical.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.mt.technical.sql.SQLHelper.ObjectFactory;

/**
 * Create a Season object
 * @author jeff
 * @see com.intirix.openmm.server.api.beans.Season
 */
public class SeasonObjectFactory implements ObjectFactory< Season >
{


	public Season createObject( ResultSet rs ) throws SQLException
	{
		final Season season = new Season();
		
		season.setId( rs.getInt( "SEASON_ID" ) );
		season.setName( rs.getString( "NAME" ) );
		season.setShowId( rs.getInt( "SHOW_ID" ) );
		season.setNumber( rs.getInt( "NUMBER" ) );
		
		return season;
	}

}
