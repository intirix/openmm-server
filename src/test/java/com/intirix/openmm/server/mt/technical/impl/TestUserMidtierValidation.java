package com.intirix.openmm.server.mt.technical.impl;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.beans.UserBean;

public class TestUserMidtierValidation
{

	private UserMidtierValidation obj;
	
	private UserMidtier child;
	
	@Before
	public void setUp()
	{
		child = EasyMock.createMock( UserMidtier.class );
		obj = new UserMidtierValidation( child );
	}


	/**
	 * Test that the admin user can't have its admin access revoked
	 * @throws CloneNotSupportedException 
	 * @throws OpenMMMidtierException 
	 */
	@Test
	public void testCannotRevokeAdmin() throws CloneNotSupportedException, OpenMMMidtierException
	{
		final UserBean oldBean = new UserBean();
		oldBean.setUsername( "admin" );
		oldBean.setAdmin( true );
		
		final UserBean newBean = (UserBean)oldBean.clone();
		newBean.setAdmin( false );
		
		try
		{
			obj.updateUser( oldBean, newBean );
			Assert.fail();
		}
		catch ( OpenMMMidtierException e )
		{
			Assert.assertEquals( "Cannot revoke admin access for user 'admin'", e.getMessage() );
		}
	}
	
}
