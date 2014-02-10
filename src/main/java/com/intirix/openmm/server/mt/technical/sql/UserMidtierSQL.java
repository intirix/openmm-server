package com.intirix.openmm.server.mt.technical.sql;

import java.util.List;

import javax.sql.DataSource;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.beans.UserBean;
import com.intirix.openmm.server.mt.technical.beans.UserBeanObjectFactory;

/**
 * SQL Implementation of the user midtier
 * @author jeff
 *
 */
public class UserMidtierSQL implements UserMidtier
{
	private final SQLHelper sqlHelper;

	public UserMidtierSQL( DataSource ds )
	{
		sqlHelper = new SQLHelper( UserMidtierSQL.class, ds );
	}


	public List< UserBean > listUsers() throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuery( new UserBeanObjectFactory(), "user_list.sql" );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( "Failed to list users", e );
		}

	}

	public int addUser( UserBean user ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "user_add.sql", user.getUsername(), user.getDisplayName(), ( user.isAdmin() ? "Y" : "N" ),
					user.getSalt(), user.getPasswordPipeline(), user.getEncodedPassword() );
			return sqlHelper.executeQuerySingleRow( new UserBeanObjectFactory(), "user_get_by_username.sql", user.getUsername() ).getUserId();
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( "Failed to add user", e );
		}
	}

	public void updateUser( UserBean oldBean, UserBean newBean ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "user_update.sql", newBean.getUsername(), newBean.getDisplayName(), ( newBean.isAdmin() ? "Y" : "N" ),
					newBean.getSalt(), newBean.getPasswordPipeline(), newBean.getEncodedPassword(), oldBean.getUserId() );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( "Failed to update user", e );
		}
	}

	public void deleteUser( int userId ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "user_delete.sql", userId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( "Failed to delete user", e );
		}
	}


	public void setCurrentUser( UserBean bean ) throws OpenMMMidtierException
	{
		throw new OpenMMMidtierException( "Unsupported method" );
	}


	public UserBean getCurrentUserBean() throws OpenMMMidtierException
	{
		throw new OpenMMMidtierException( "Unsupported method" );
	}
	
	

}
