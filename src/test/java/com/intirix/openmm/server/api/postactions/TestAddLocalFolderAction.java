package com.intirix.openmm.server.api.postactions;

import javax.servlet.ServletException;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockHttpServletRequest;
import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.beans.LocalRootFolder;

public class TestAddLocalFolderAction
{
	private OpenMMServerRuntime runtime;
	private AddLocalFolderAction action;
	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		action = new AddLocalFolderAction();
		action.setRuntime( runtime );
	}
	
	@Test
	public void test1() throws ServletException, OpenMMMidtierException
	{
		final LocalRootFolder folder = new LocalRootFolder();
		folder.setMountPoint( "mountpoint" );
		folder.setUrl( "file:///mnt" );
		
		final ConfigMidtier configMidtier = EasyMock.createMock( ConfigMidtier.class );
		configMidtier.addRootFolder( folder );
		EasyMock.expectLastCall();
		EasyMock.replay( configMidtier );
		runtime.getTechnicalLayer().setConfigMidtier( configMidtier );
		
		
		final MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter( "path", "/mnt" );
		req.setParameter( "mountpoint", folder.getMountPoint() );

		action.processAction( req );
	}


}
