package com.intirix.openmm.server.mt.technical.impl;

import java.util.List;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.beans.UserBean;

public class UserMidtierDecorator implements UserMidtier
{
	
	private final UserMidtier child;
	
	public UserMidtierDecorator( UserMidtier child )
	{
		this.child = child;
	}

	public List< UserBean > listUsers() throws OpenMMMidtierException
	{
		return child.listUsers();
	}

	public int addUser( UserBean user ) throws OpenMMMidtierException
	{
		return child.addUser( user );
	}

	public void updateUser( UserBean oldBean, UserBean newBean ) throws OpenMMMidtierException
	{
		child.updateUser( oldBean, newBean );
	}

	public void deleteUser( int userId ) throws OpenMMMidtierException
	{
		child.deleteUser( userId );
	}

	public void setCurrentUser( UserBean bean ) throws OpenMMMidtierException
	{
		child.setCurrentUser( bean );
	}

	public UserBean getCurrentUserBean() throws OpenMMMidtierException
	{
		return child.getCurrentUserBean();
	}
	
	

}
