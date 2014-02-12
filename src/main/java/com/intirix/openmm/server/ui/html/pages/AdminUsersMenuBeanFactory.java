package com.intirix.openmm.server.ui.html.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.technical.beans.UserBean;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.UserInfoBean;

public class AdminUsersMenuBeanFactory implements PageBeanFactory
{

	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		try
		{
			final AdminUsersMenuBean page = new AdminUsersMenuBean();

			final int userId = Integer.parseInt( request.getParameter( "userId" ) );
			final UserBean user = runtime.getApplicationLayer().getUserApp().getUserById( userId );

			final UserInfoBean bean = new UserInfoBean();
			bean.setAdmin( user.isAdmin() );
			bean.setDisplayName( user.getDisplayName() );
			bean.setUserId( user.getUserId() );
			bean.setUsername( user.getUsername() );

			page.setUser( bean );
			return page;
		}
		catch ( Exception e )
		{
			throw new ServletException( e );
		}
	}

}
