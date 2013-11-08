package com.intirix.openmm.server.mt.technical.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.intirix.openmm.server.mt.technical.sql.SQLHelper.ObjectFactory;

/**
 * Factory for the root folder
 * @author jeff
 *
 */
public class RootFolderObjectFactory implements ObjectFactory< RootFolder >
{

	public RootFolder createObject( ResultSet rs ) throws SQLException
	{
		final String path = rs.getString( "PATH" );
		final String prefix = path.replaceFirst( ":.*", "" );
		
		if ( "file".equals( prefix ) )
		{
			final LocalRootFolder ret = new LocalRootFolder();
			ret.setId( rs.getInt( "FOLDER_ID" ) );
			ret.setMountPoint( rs.getString( "MOUNTPOINT" ) );
			ret.setUrl( path );
			return ret;
		}
		return null;
	}

}
