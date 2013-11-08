package com.intirix.openmm.server.mt.technical.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.technical.sql.SQLHelper.ObjectFactory;

/**
 * Create the Show object
 * @author jeff
 * @see com.intirix.openmm.server.api.beans.Show
 *
 */
public class ShowObjectFactory implements ObjectFactory< Show >
{

	public Show createObject( ResultSet rs ) throws SQLException
	{
		final Show show = new Show();
		show.setId( rs.getInt( "SHOW_ID" ) );
		show.setName( rs.getString( "NAME" ) );
		show.setDisplayName( rs.getString( "DISPLAY_NAME" ) );
		show.setTvdbId( rs.getString( "TVDB_ID" ) );
		show.setTvdbLang( rs.getString( "TVDB_LANG" ) );
		show.setImdbId( rs.getString( "IMDB_ID" ) );
		show.setZap2itId( rs.getString( "ZAP2IT_ID" ) );
		show.setActive( "Y".equalsIgnoreCase( rs.getString( "ACTIVE" ) ) );
		show.setContentRating( rs.getString( "CONTENT_RATING" ) );
		show.setBannerPath( rs.getString( "BANNER_PATH" ) );
		show.setDescription( rs.getString( "DESCRIPTION" ) );
		
		return show;
	}

}
