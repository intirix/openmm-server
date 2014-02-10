package com.intirix.openmm.server.mt.technical.impl;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.beans.UserBean;

/**
 * Check for access violations
 * @author jeff
 *
 */
public class UserMidtierAccess extends UserMidtierDecorator
{

	public UserMidtierAccess( UserMidtier child )
	{
		super( child );
	}
	
	/**
	 * Store the user bean
	 */
	private final ThreadLocal< UserBean > tlCurrentUser = new ThreadLocal< UserBean >();
	
	//////////////////////////////////////////////////
	//
	// Intercept methods
	//
	//////////////////////////////////////////////////

	public void setCurrentUser( UserBean bean ) throws OpenMMMidtierException
	{
		tlCurrentUser.set( bean );
	}

	public UserBean getCurrentUserBean() throws OpenMMMidtierException
	{
		return tlCurrentUser.get();
	}

	//////////////////////////////////////////////////
	//
	// Helper methods
	//
	//////////////////////////////////////////////////

	/**
	 * Validate if the user has admin access
	 * @throws OpenMMMidtierException
	 */
	private void checkAdmin() throws OpenMMMidtierException
	{
		if ( !getCurrentUserBean().isAdmin() )
		{
			throw new OpenMMMidtierException( "Access denied" );
		}
	}

	/**
	 * Validate that the current user matches the input user
	 * @param userId
	 * @throws OpenMMMidtierException
	 */
	private void checkSameUser( int userId ) throws OpenMMMidtierException
	{
		if ( getCurrentUserBean().getUserId() != userId )
		{
			throw new OpenMMMidtierException( "Access denied" );
		}
	}
	
	/**
	 * Validate that the current user can modify the input user
	 * @param userId
	 * @throws OpenMMMidtierException
	 */
	private void checkAdminOrSameUser( int userId ) throws OpenMMMidtierException
	{
		if ( !getCurrentUserBean().isAdmin() && getCurrentUserBean().getUserId() != userId )
		{
			throw new OpenMMMidtierException( "Access denied" );
		}
	}

	
	//////////////////////////////////////////////////
	//
	// Wrappers
	//
	//////////////////////////////////////////////////

	@Override
	public int addUser( UserBean user ) throws OpenMMMidtierException
	{
		checkAdmin();
		return super.addUser( user );
	}

	@Override
	public void updateUser( UserBean oldBean, UserBean newBean ) throws OpenMMMidtierException
	{
		checkAdminOrSameUser( oldBean.getUserId() );
		
		// only an admin can switch the admin flag
		if ( oldBean.isAdmin() != newBean.isAdmin() )
		{
			checkAdmin();
			
		}
		
		if ( !oldBean.getEncodedPassword().equals( newBean.getEncodedPassword() ) )
		{
			checkAdminOrSameUser( oldBean.getUserId() );
		}
		
		super.updateUser( oldBean, newBean );
	}

	@Override
	public void deleteUser( int userId ) throws OpenMMMidtierException
	{
		checkAdmin();
		super.deleteUser( userId );
	}

	
}
