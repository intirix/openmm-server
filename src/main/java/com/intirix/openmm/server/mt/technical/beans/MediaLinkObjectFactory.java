package com.intirix.openmm.server.mt.technical.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.intirix.openmm.server.api.beans.MediaLink;
import com.intirix.openmm.server.mt.technical.sql.SQLHelper.ObjectFactory;

public class MediaLinkObjectFactory implements ObjectFactory< MediaLink >
{

	public MediaLink createObject( ResultSet rs ) throws SQLException
	{
		final MediaLink link = new MediaLink();
		link.setId( rs.getInt( "LINK_ID" ) );
		link.setInternal( "I".equalsIgnoreCase( rs.getString( "REF_TYPE_CODE" ) ) );
		link.setMediaTypeCode( rs.getString( "TYPE_CODE" ) );
		link.setAvailable( "Y".equalsIgnoreCase( rs.getString( "AVAILABLE" )  ));
		link.setService( rs.getString( "EXT_SERVICE" ) );
		link.setUrl( rs.getString( "URL" ) );
		link.setSize( rs.getLong( "FILE_SIZE" ) );
		
		return link;
	}

}
