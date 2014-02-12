package com.intirix.openmm.server.ui.html.pages;

import java.io.ByteArrayOutputStream;

import org.custommonkey.xmlunit.XMLTestCase;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.intirix.openmm.server.MockHttpServletRequest;
import com.intirix.openmm.server.MockSystemFactory;
import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.app.UserApp;
import com.intirix.openmm.server.mt.technical.beans.UserBean;
import com.intirix.openmm.server.ui.html.HtmlTemplateEngine;

public class TestAdminUsersMenu extends XMLTestCase
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
		final UserBean userBean = new UserBean();
		userBean.setUserId( 0 );
		userBean.setUsername( "myuser" );

		// pass in id=1
		final MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter( "userId", "" + userBean.getUserId() );
		
		final UserApp userApp = EasyMock.createMock( UserApp.class );
		EasyMock.expect( userApp.getUserById( userBean.getUserId() ) ).andReturn( userBean );
		EasyMock.replay( userApp );
		runtime.getApplicationLayer().setUserApp( userApp );
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream( 1024 );
		engine.renderPage( "adminUsersMenu.xml", engine.createPageBean( "adminUsersMenu.xml", req, null ), buffer );
		System.out.println( buffer.toString() );
		assertXpathEvaluatesTo( userBean.getUsername(), "adminUsersMenuBean/user/username", buffer.toString() );
		
	}

}
