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

public class TestResetPasswordAction
{
	private OpenMMServerRuntime runtime;
	private ResetPasswordAction action;
	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		action = new ResetPasswordAction();
		action.setRuntime( runtime );
	}
	
	@Test
	public void test1() throws ServletException, OpenMMMidtierException
	{
		final UserBean user = new UserBean();
		user.setAdmin( true );
		user.setDisplayName( "MyUser" );
		user.setUsername( "myuser" );
		user.setUserId( 0 );
		
		final UserApp userApp = EasyMock.createMock( UserApp.class );
		EasyMock.expect( userApp.getUserById( user.getUserId() ) ).andReturn( user );
		userApp.resetPassword( user.getUsername(), "password" );
		EasyMock.expectLastCall();
		EasyMock.replay( userApp );
		runtime.getApplicationLayer().setUserApp( userApp );
		
		
		final MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter( "userId", "" + user.getUserId() );
		req.setParameter( "password", "password" );

		action.processAction( req );
	}


}
