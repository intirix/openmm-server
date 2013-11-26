package com.intirix.openmm.server.mt.app;

import java.util.List;

import org.apache.commons.codec.EncoderException;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.password.PasswordEncodingManager;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.beans.UserBean;

public class UserAppImpl implements UserApp
{

	private static final String DEFAULT_NEW_ENCODING_PIPELINE = "SALT,SHA256,HEX";
	private UserMidtier userMidtier;

	private final ThreadLocal< UserBean > tlCurrentUser = new ThreadLocal< UserBean >();



	public void setUserMidtier( UserMidtier userMidtier )
	{
		this.userMidtier = userMidtier;
	}



	public List< UserBean > listUsers() throws OpenMMMidtierException
	{
		return userMidtier.listUsers();
	}



	public UserBean getUserById( int userId ) throws OpenMMMidtierException
	{
		for ( final UserBean bean: listUsers() )
		{
			if ( bean.getUserId() == userId )
			{
				return bean;
			}
		}
		return null;
	}

	public UserBean getUserByUserName( String username ) throws OpenMMMidtierException
	{
		for ( final UserBean bean: listUsers() )
		{
			if ( bean.getUsername().equalsIgnoreCase( username ) )
			{
				return bean;
			}
		}
		return null;
	}

	public int addUser( UserBean user ) throws OpenMMMidtierException
	{
		return userMidtier.addUser( user );
	}

	public void updateUser( UserBean oldBean, UserBean newBean ) throws OpenMMMidtierException
	{
		userMidtier.updateUser( oldBean, newBean );
	}

	public void deleteUser( int userId ) throws OpenMMMidtierException
	{
		userMidtier.deleteUser( userId );
	}

	public boolean authenticateUser( String username, String password ) throws OpenMMMidtierException
	{
		final UserBean user = getUserByUserName( username );
		if ( user == null )
		{
			// could not find user
			return false;
		}

		final String pipeline = user.getPasswordPipeline();
		final String encodedPassword = user.getEncodedPassword();
		final String salt = user.getSalt();

		String providedEncodedPassword;
		try
		{
			providedEncodedPassword = PasswordEncodingManager.encodePassword( salt, password, pipeline );
		}
		catch ( EncoderException e )
		{
			throw new OpenMMMidtierException( "Encryption failure", e );
		}

		return encodedPassword.equals( providedEncodedPassword );
	}

	public void resetPassword( String username, String password ) throws OpenMMMidtierException
	{
		final UserBean user = getUserByUserName( username );
		if ( user != null )
		{
			final String pipeline = DEFAULT_NEW_ENCODING_PIPELINE;
			final String salt = "" + Math.random();

			try
			{
				final String encodedPassword = PasswordEncodingManager.encodePassword( salt, password, pipeline );

				final UserBean newBean = (UserBean)user.clone();
				newBean.setSalt( salt );
				newBean.setPasswordPipeline( pipeline );
				newBean.setEncodedPassword( encodedPassword );
				userMidtier.updateUser( user, newBean );
			}
			catch ( Exception e )
			{
				throw new OpenMMMidtierException( "Failed to reset password", e );
			}
		}

	}

	public boolean changePassword( String username, String existingPassword, String newPassword ) throws OpenMMMidtierException
	{
		final UserBean user = getUserByUserName( username );
		if ( user == null )
		{
			// could not find user
			return false;
		}

		String pipeline = user.getPasswordPipeline();
		String encodedPassword = user.getEncodedPassword();
		String salt = user.getSalt();

		try
		{

			final String providedEncodedPassword = PasswordEncodingManager.encodePassword( salt, existingPassword, pipeline );

			if ( encodedPassword.equals( providedEncodedPassword ) )
			{
				pipeline = DEFAULT_NEW_ENCODING_PIPELINE;
				salt = "" + Math.random();

				encodedPassword = PasswordEncodingManager.encodePassword( salt, newPassword, pipeline );

				final UserBean newBean = (UserBean)user.clone();
				newBean.setSalt( salt );
				newBean.setPasswordPipeline( pipeline );
				newBean.setEncodedPassword( encodedPassword );
				userMidtier.updateUser( user, newBean );
				return true;
			}
			else
			{
				return false;
			}
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( "Failed to change password", e );
		}
	}

	public void setCurrentUser( String username ) throws OpenMMMidtierException
	{
		tlCurrentUser.set( getUserByUserName( username ) );
	}

	public UserBean getCurrentUserBean() throws OpenMMMidtierException
	{
		return tlCurrentUser.get();
	}
	
	



}
