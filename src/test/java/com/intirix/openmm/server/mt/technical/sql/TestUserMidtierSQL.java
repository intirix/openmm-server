package com.intirix.openmm.server.mt.technical.sql;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.UserMidtier;
import com.intirix.openmm.server.mt.technical.beans.UserBean;

public class TestUserMidtierSQL
{
	
	private OpenMMServerRuntime runtime;
	private UserMidtier midtier;
	private Serializer serializer;
	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		runtime.init();
		midtier = new UserMidtierSQL( runtime.getDataSource() );
		serializer = new Persister();
	}
	
	@Test
	public void testList() throws OpenMMMidtierException
	{
		// The admin user should already exist
		Assert.assertEquals( 1, midtier.listUsers().size() );
		final UserBean admin = midtier.listUsers().get( 0 );
		Assert.assertEquals( "admin", admin.getUsername() );
		Assert.assertEquals( "admin", admin.getEncodedPassword() );
	}
	
	@Test
	public void testAddRemove() throws OpenMMMidtierException
	{
		final UserBean bean = new UserBean();
		bean.setUsername( "bart" );
		
		Assert.assertEquals( 1, midtier.listUsers().size() );
		int userId = midtier.addUser( bean );
		Assert.assertEquals( 2, midtier.listUsers().size() );
		midtier.deleteUser( userId );
		Assert.assertEquals( 1, midtier.listUsers().size() );
		
	}
	
	@Test
	public void testUpdate() throws OpenMMMidtierException, CloneNotSupportedException
	{
		final UserBean bean1 = midtier.listUsers().get( 0 );
		final UserBean bean2 = (UserBean)bean1.clone();
		bean2.setAdmin( false );
		bean2.setDisplayName( "Admin" );
		
		midtier.updateUser( bean1, bean2 );
		final UserBean bean3 = midtier.listUsers().get( 0 );
		
		Assert.assertEquals( bean2.getDisplayName(), bean3.getDisplayName() );
		Assert.assertEquals( bean2.isAdmin(), bean3.isAdmin() );

	}
}
