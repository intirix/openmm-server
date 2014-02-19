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
import com.intirix.openmm.server.mt.technical.beans.HttpRootFolder;

public class TestAddHttpFolderAction
{
	private OpenMMServerRuntime runtime;
	private AddHttpFolderAction action;
	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		action = new AddHttpFolderAction();
		action.setRuntime( runtime );
	}
	
	@Test
	public void test1() throws ServletException, OpenMMMidtierException
	{
		final HttpRootFolder folder = new HttpRootFolder();
		folder.setMountPoint( "mountpoint" );
		folder.setUrl( "http://files.example.com" );
		folder.setUsername( "myusername" );
		folder.setPassword( "mypassowrd" );
		
		final ConfigMidtier configMidtier = EasyMock.createMock( ConfigMidtier.class );
		configMidtier.addRootFolder( folder );
		EasyMock.expectLastCall();
		EasyMock.replay( configMidtier );
		runtime.getTechnicalLayer().setConfigMidtier( configMidtier );
		
		
		final MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter( "username", folder.getUsername() );
		req.setParameter( "password", folder.getPassword() );
		req.setParameter( "url", folder.getUrl() );
		req.setParameter( "mountpoint", folder.getMountPoint() );

		action.processAction( req );
	}


}
