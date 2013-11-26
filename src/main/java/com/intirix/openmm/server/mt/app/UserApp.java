package com.intirix.openmm.server.mt.app;

import java.util.List;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.beans.UserBean;

public interface UserApp
{
	
	public void setUserMidtier( UserMidtier userMidtier );
	
	/**
	 * Get user by userid
	 * @param userId
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public UserBean getUserById( int userId ) throws OpenMMMidtierException;
	
	/**
	 * Get user by username
	 * @param username
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public UserBean getUserByUserName( String username ) throws OpenMMMidtierException;

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
	
	/**
	 * Authenticate a user
	 * @param username
	 * @param password
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public boolean authenticateUser( String username, String password ) throws OpenMMMidtierException;
	
	/**
	 * Reset a user's password
	 * @param username
	 * @param password
	 * @throws OpenMMMidtierException
	 */
	public void resetPassword( String username, String password ) throws OpenMMMidtierException;
	
	/**
	 * Change a password
	 * @param username
	 * @param existingPassword
	 * @param newPassword
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public boolean changePassword( String username, String existingPassword, String newPassword ) throws OpenMMMidtierException;
	
	/**
	 * Set the current user for the thread
	 * @param username
	 * @throws OpenMMMidtierException
	 */
	public void setCurrentUser( String username ) throws OpenMMMidtierException;
	
	/**
	 * Get the current user bean
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public UserBean getCurrentUserBean() throws OpenMMMidtierException;

}
