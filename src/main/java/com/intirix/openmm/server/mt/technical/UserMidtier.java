package com.intirix.openmm.server.mt.technical;

import java.util.List;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.beans.UserBean;

/**
 * Midtier for user operations
 * @author jeff
 *
 */
public interface UserMidtier
{

	/**
	 * List the system users
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public List< UserBean > listUsers() throws OpenMMMidtierException;
	
	/**
	 * Add a user
	 * @param user
	 * @return user id
	 * @throws OpenMMMidtierException
	 */
	public int addUser( UserBean user ) throws OpenMMMidtierException;
	
	/**
	 * Update a user
	 * @param oldBean
	 * @param newBean
	 * @throws OpenMMMidtierException
	 */
	public void updateUser( UserBean oldBean, UserBean newBean ) throws OpenMMMidtierException;
	
	/**
	 * Delete a user
	 * @param userId
	 * @throws OpenMMMidtierException
	 */
	public void deleteUser( int userId ) throws OpenMMMidtierException;
}
