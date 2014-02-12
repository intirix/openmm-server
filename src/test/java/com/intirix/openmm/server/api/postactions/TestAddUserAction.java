package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockHttpServletRequest;
import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.UserApp;
import com.intirix.openmm.server.mt.technical.beans.UserBean;

public class TestAddUserAction
{
	private OpenMMServerRuntime runtime;
	private AddUserAction action;
	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		action = new AddUserAction();
		action.setRuntime( runtime );
	}
	
	@Test
	public void test1() throws ServletException, OpenMMMidtierException
	{
		final UserBean user = new UserBean();
		user.setAdmin( true );
		user.setDisplayName( "MyUser" );
		user.setUsername( "myuser" );
		
		final UserApp userApp = EasyMock.createMock( UserApp.class );
		EasyMock.expect( userApp.addUser( user ) ).andReturn( 0 );
		EasyMock.replay( userApp );
		runtime.getApplicationLayer().setUserApp( userApp );
		
		
		final MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter( "admin", "Y" );
		req.setParameter( "displayName", "MyUser" );
		req.setParameter( "username", "myuser" );

		action.processAction( req );
	}


}
