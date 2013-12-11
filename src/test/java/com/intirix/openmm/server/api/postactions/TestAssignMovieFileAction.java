package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockHttpServletRequest;
import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.MovieApp;

public class TestAssignMovieFileAction
{
	private OpenMMServerRuntime runtime;
	private AssignMovieFileAction action;
	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		action = new AssignMovieFileAction();
		action.setRuntime( runtime );
	}
	
	@Test
	public void test1() throws ServletException, OpenMMMidtierException
	{
		final MovieApp movieApp = EasyMock.createMock( MovieApp.class );
		movieApp.assignFile( 1, "/folder/test.avi", 0 );
		EasyMock.expectLastCall();
		EasyMock.replay( movieApp );
		runtime.getApplicationLayer().setMovieApp( movieApp );
		
		
		final MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter( "movieId", "1" );
		req.setParameter( "path", "/folder" );
		req.setParameter( "filename", "test.avi" );

		action.processAction( req );
	}


}
