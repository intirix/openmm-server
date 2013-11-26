package com.intirix.openmm.server.mt.technical.impl.cache;

import java.util.List;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.beans.UserBean;
import com.intirix.openmm.server.mt.technical.impl.UserMidtierDecorator;

/**
 * Cache for the user midtier
 * @author jeff
 *
 */
public class UserMidtierCache extends UserMidtierDecorator
{
	
	private List< UserBean > cache  = null;

	public UserMidtierCache( UserMidtier child )
	{
		super( child );
	}

	@Override
	public synchronized List< UserBean > listUsers() throws OpenMMMidtierException
	{
		if ( cache == null )
		{
			cache = super.listUsers();
		}
		return cache;
	}

	@Override
	public synchronized int addUser( UserBean user ) throws OpenMMMidtierException
	{
		cache = null;
		return super.addUser( user );
	}

	@Override
	public synchronized void updateUser( UserBean oldBean, UserBean newBean ) throws OpenMMMidtierException
	{
		cache = null;
		super.updateUser( oldBean, newBean );
	}

	@Override
	public synchronized void deleteUser( int userId ) throws OpenMMMidtierException
	{
		cache = null;
		super.deleteUser( userId );
	}
	
	

}
