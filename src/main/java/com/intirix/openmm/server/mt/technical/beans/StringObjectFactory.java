package com.intirix.openmm.server.mt.technical.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.intirix.openmm.server.mt.technical.sql.SQLHelper.ObjectFactory;

public class StringObjectFactory implements ObjectFactory< String >
{

	public String createObject( ResultSet rs ) throws SQLException
	{
		return rs.getString( 1 );
	}

}
