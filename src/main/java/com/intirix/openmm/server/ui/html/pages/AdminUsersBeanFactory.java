package com.intirix.openmm.server.ui.html.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.technical.beans.UserBean;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.UserInfoBean;

public class AdminUsersBeanFactory implements PageBeanFactory
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
			final AdminUsersBean page = new AdminUsersBean();
			final List< UserInfoBean > users = new ArrayList< UserInfoBean >();
			for ( final UserBean user: runtime.getApplicationLayer().getUserApp().listUsers() )
			{
				final UserInfoBean bean = new UserInfoBean();
				bean.setAdmin( user.isAdmin() );
				bean.setDisplayName( user.getDisplayName() );
				bean.setUserId( user.getUserId() );
				bean.setUsername( user.getUsername() );
				users.add( bean );
			}
			Collections.sort( users );
			page.setUsers( users.toArray( new UserInfoBean[]{} ) );
			return page;
		}
		catch ( Exception e )
		{
			throw new ServletException( e );
		}
	}

}
