package com.intirix.openmm.server.mt.technical.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.intirix.openmm.server.mt.technical.sql.SQLHelper.ObjectFactory;

/**
 * Factory for the UserBean object
 * @author jeff
 *
 */
public class UserBeanObjectFactory implements ObjectFactory< UserBean >
{

	public UserBean createObject( ResultSet rs ) throws SQLException
	{
		final UserBean bean = new UserBean();

		bean.setUserId( rs.getInt( "USER_ID" ) );
		bean.setUsername( rs.getString( "USER_NAME" ) );
		bean.setDisplayName( rs.getString( "DISPLAY_NAME" ) );
		bean.setAdmin( "Y".equals( rs.getString( "IS_ADMIN" ) ) );
		bean.setSalt( rs.getString( "SALT" ) );
		bean.setPasswordPipeline( rs.getString( "ENC_CHAIN" ) );
		bean.setEncodedPassword( rs.getString( "ENC_PASSWORD" ) );
		
		return bean;
	}

	
}
