package com.intirix.openmm.server.mt.technical.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.intirix.openmm.server.mt.technical.sql.SQLHelper.ObjectFactory;

public class IntegerObjectFactory implements ObjectFactory< Integer >
{

	public Integer createObject( ResultSet rs ) throws SQLException
	{
		return rs.getInt( 1 );
	}

}
