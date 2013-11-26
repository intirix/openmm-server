package com.intirix.openmm.server.mt.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.simpleframework.xml.Serializer;

import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.beans.UserBean;
import com.intirix.openmm.server.mt.technical.sql.UserMidtierSQL;

public class TestUserAppImpl
{
	private OpenMMServerRuntime runtime;
	private UserMidtier midtier;
	private UserApp app;
	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		runtime.init();
		midtier = new UserMidtierSQL( runtime.getDataSource() );
		app = new UserAppImpl();
		app.setUserMidtier( midtier );
	}
	
	@Test
	public void testAuthenticate() throws OpenMMMidtierException
	{
		Assert.assertTrue( app.authenticateUser( "admin", "admin" ) );
	}
	
	@Test
	public void testResetPassword() throws OpenMMMidtierException
	{
		app.resetPassword( "admin", "password" );
		Assert.assertTrue( app.authenticateUser( "admin", "password" ) );
	}
	
	@Test
	public void testChangePassword() throws OpenMMMidtierException
	{
		Assert.assertTrue( app.authenticateUser( "admin", "admin" ) );
		Assert.assertFalse( app.changePassword( "admin", "password", "blah" ) );
		Assert.assertTrue( app.authenticateUser( "admin", "admin" ) );
		Assert.assertTrue( app.changePassword( "admin", "admin", "password" ) );
		Assert.assertTrue( app.authenticateUser( "admin", "password" ) );
	}
	
	
	
	
	
	@Test
	public void testAddRemove() throws OpenMMMidtierException
	{
		final UserBean bean = new UserBean();
		bean.setUsername( "bart" );
		
		Assert.assertEquals( 1, app.listUsers().size() );
		int userId = app.addUser( bean );
		Assert.assertEquals( 2, app.listUsers().size() );
		app.deleteUser( userId );
		Assert.assertEquals( 1, app.listUsers().size() );
		
	}
	
	@Test
	public void testUpdate() throws OpenMMMidtierException, CloneNotSupportedException
	{
		final UserBean bean1 = app.listUsers().get( 0 );
		final UserBean bean2 = (UserBean)bean1.clone();
		bean2.setAdmin( false );
		bean2.setDisplayName( "Admin" );
		
		app.updateUser( bean1, bean2 );
		final UserBean bean3 = app.listUsers().get( 0 );
		
		Assert.assertEquals( bean2.getDisplayName(), bean3.getDisplayName() );
		Assert.assertEquals( bean2.isAdmin(), bean3.isAdmin() );

	}
}
