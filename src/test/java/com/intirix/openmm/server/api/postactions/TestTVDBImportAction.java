package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockHttpServletRequest;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.TVDBApp;

public class TestTVDBImportAction
{
	private OpenMMServerRuntime runtime;
	
	private TVDBImportAction action = new TVDBImportAction();

	@Before
	public void setUp()
	{
		runtime = new OpenMMServerRuntime();
		action.setRuntime( runtime );
	}

	@Test
	public void test1() throws ServletException, OpenMMMidtierException
	{
		TVDBApp app = EasyMock.createMock( TVDBApp.class );
		EasyMock.expect( app.importShow( "5" ) ).andReturn( 1 );
		EasyMock.replay( app );
		
		runtime.getApplicationLayer().setTvdbApp( app );
		
		final MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter( "tvdbId", "5" );

		Assert.assertEquals( "Successfully imported show", action.processAction( req ).getActionMessage() );
	}

}
