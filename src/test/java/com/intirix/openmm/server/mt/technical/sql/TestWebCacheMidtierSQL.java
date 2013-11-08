package com.intirix.openmm.server.mt.technical.sql;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.WebCacheMidtier;

public class TestWebCacheMidtierSQL
{
	private OpenMMServerRuntime runtime;
	private WebCacheMidtier midtier;
	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		runtime.init();
		midtier = new WebCacheMidtierSQL( runtime.getDataSource() );
	}


	@Test
	public void test1() throws OpenMMMidtierException
	{
		final String hash = midtier.registerCachableUrl( "http://example.com/favicon.ico" );
		Assert.assertEquals( "http://example.com/favicon.ico", midtier.getUrlForHash( hash ) );
	}
}
