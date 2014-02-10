package com.intirix.openmm.server.mt.technical.impl;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.beans.UserBean;

public class TestUserMidtierAccess
{

	private UserMidtierAccess obj;
	
	private UserMidtier child;
	
	@Before
	public void setUp()
	{
		child = EasyMock.createMock( UserMidtier.class );
		obj = new UserMidtierAccess( child );
	}

	
	/**
	 * Make sure the admin user can change passwords
	 * @throws CloneNotSupportedException 
	 * @throws OpenMMMidtierException 
	 */
	@Test
	public void testAdminChangePassword() throws CloneNotSupportedException, OpenMMMidtierException
	{
		final UserBean adminBean = new UserBean();
		adminBean.setAdmin( true );
		
		final UserBean oldBean = new UserBean();
		oldBean.setUsername( "admin" );
		
		final UserBean newBean = (UserBean)oldBean.clone();
		newBean.setEncodedPassword( "NEW" );
		
		child.updateUser( oldBean, newBean );
		EasyMock.expectLastCall();
		EasyMock.replay( child );
		obj.setCurrentUser( adminBean );
		
		obj.updateUser( oldBean, newBean );
		
	}
	
	
	/**
	 * Make sure a user can change their own password
	 * @throws CloneNotSupportedException 
	 * @throws OpenMMMidtierException 
	 */
	@Test
	public void testSameUserChangePassword() throws CloneNotSupportedException, OpenMMMidtierException
	{
		final UserBean adminBean = new UserBean();
		adminBean.setUserId( 5 );
		adminBean.setAdmin( false );
		
		final UserBean oldBean = new UserBean();
		oldBean.setUserId( adminBean.getUserId() );
		oldBean.setUsername( "admin" );
		
		final UserBean newBean = (UserBean)oldBean.clone();
		newBean.setEncodedPassword( "NEW" );
		
		child.updateUser( oldBean, newBean );
		EasyMock.expectLastCall();
		EasyMock.replay( child );
		obj.setCurrentUser( adminBean );
		
		obj.updateUser( oldBean, newBean );
		
	}
	
	
	
	/**
	 * Make sure everyone else cannot change the password
	 * @throws CloneNotSupportedException 
	 * @throws OpenMMMidtierException 
	 */
	@Test
	public void testChangePasswordDenied() throws CloneNotSupportedException, OpenMMMidtierException
	{
		final UserBean adminBean = new UserBean();
		adminBean.setUserId( 5 );
		adminBean.setAdmin( false );
		
		final UserBean oldBean = new UserBean();
		oldBean.setUserId( 1 );
		oldBean.setUsername( "admin" );
		
		final UserBean newBean = (UserBean)oldBean.clone();
		newBean.setEncodedPassword( "NEW" );
		
		child.updateUser( oldBean, newBean );
		EasyMock.expectLastCall();
		EasyMock.replay( child );
		obj.setCurrentUser( adminBean );
		
		try
		{
			obj.updateUser( oldBean, newBean );
			Assert.fail();
		}
		catch ( OpenMMMidtierException e )
		{
			Assert.assertEquals( "Access denied", e.getMessage() );
		}
		
	}
	
	
	
}
