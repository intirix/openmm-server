package com.intirix.openmm.server.ui.html.pages;

import java.io.ByteArrayOutputStream;

import org.custommonkey.xmlunit.XMLTestCase;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockHttpServletRequest;
import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.beans.LocalRootFolder;
import com.intirix.openmm.server.ui.html.HtmlTemplateEngine;

public class TestAdminFoldersEditLocal extends XMLTestCase
{
	private OpenMMServerRuntime runtime;
	
	private HtmlTemplateEngine engine;

	
	@Before
	public void setUp() throws Exception
	{
		runtime = MockSystemFactory.createMockRuntime();
		engine = new HtmlTemplateEngine( runtime, null );
	}

	@Test
	public void testXml() throws Exception
	{
		// pass in id=1
		final MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter( "id", "0" );
		
		final LocalRootFolder folder = new LocalRootFolder();
		folder.setId( 0 );
		folder.setMountPoint( "/movies" );
		folder.setUrl( "file:///mnt/movies" );
		
		final ConfigMidtier configMidtier = EasyMock.createMock( ConfigMidtier.class );
		EasyMock.expect( configMidtier.getRootFolder( folder.getId() ) ).andReturn( folder );
		EasyMock.expect( configMidtier.getRootFolder( folder.getId() ) ).andReturn( folder );
		EasyMock.replay( configMidtier );
		runtime.getTechnicalLayer().setConfigMidtier( configMidtier );
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream( 1024 );
		engine.renderPage( "adminFoldersEditLocal.xml", engine.createPageBean( "adminFoldersEditLocal.xml", req, null ), buffer );
		System.out.println( buffer.toString() );
		assertXpathEvaluatesTo( folder.getMountPoint(), "//folder/mountPoint", buffer.toString() );
		

		buffer = new ByteArrayOutputStream( 1024 );
		engine.renderPage( "adminFoldersEditLocal.phtml", engine.createPageBean( "adminFoldersEditLocal.phtml", req, null ), buffer );
		System.out.println( buffer.toString() );
		Assert.assertTrue( buffer.toString().contains( "<input type=\"text\" name=\"mountpoint\" id=\"editFolderLocalMountPoint\" value=\"/movies\"/>"  ) );
	}
}
