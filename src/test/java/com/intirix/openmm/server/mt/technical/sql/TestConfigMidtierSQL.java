package com.intirix.openmm.server.mt.technical.sql;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.beans.HttpRootFolder;
import com.intirix.openmm.server.mt.technical.beans.LocalRootFolder;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;
import com.intirix.openmm.server.mt.technical.sql.ConfigMidtierSQL;

public class TestConfigMidtierSQL
{
	
	private OpenMMServerRuntime runtime;
	private ConfigMidtier midtier;
	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		runtime.init();
		midtier = new ConfigMidtierSQL( runtime.getDataSource() );
	}

	@Test
	public void testaddRootFolderLocal() throws OpenMMMidtierException
	{
		final RootFolder folder = new LocalRootFolder();
		folder.setUrl( "file:///mnt/media" );
		midtier.addRootFolder( folder );
		
		final LocalRootFolder folder2 = (LocalRootFolder)midtier.listRootFolders().get( 0 );
		Assert.assertEquals( "file:///mnt/media", folder2.getUrl() );
	}
	
	@Test
	public void testaddRootFolderHttp() throws OpenMMMidtierException, CloneNotSupportedException
	{
		final HttpRootFolder folder = new HttpRootFolder();
		folder.setUrl( "http://files.example.com" );
		folder.setUsername( "username" );
		folder.setPassword( "password" );
		midtier.addRootFolder( folder );
		
		final HttpRootFolder folder2 = (HttpRootFolder)midtier.listRootFolders().get( 0 );
		Assert.assertEquals( folder.getUrl(), folder2.getUrl() );
		Assert.assertEquals( folder.getUsername(), folder2.getUsername() );
		
		final HttpRootFolder folder3 = (HttpRootFolder)folder2.clone();
		folder3.setPassword( "newpassword" );
		midtier.updateRootFolder( folder2, folder3 );
		
		midtier.deleteRootFolder( folder2.getId() );
	}
}
