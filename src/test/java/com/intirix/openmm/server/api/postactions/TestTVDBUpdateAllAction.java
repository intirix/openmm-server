package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockHttpServletRequest;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;

public class TestTVDBUpdateAllAction
{
	private OpenMMServerRuntime runtime;
	
	private TVDBUpdateAllAction action = new TVDBUpdateAllAction();

	@Before
	public void setUp()
	{
		runtime = new OpenMMServerRuntime();
		action.setRuntime( runtime );
	}

	@Test
	public void test1() throws ServletException, OpenMMMidtierException
	{

		
		final MockHttpServletRequest req = new MockHttpServletRequest();


		Assert.assertEquals( "Started update", action.processAction( req ).getActionMessage() );
	}

}
