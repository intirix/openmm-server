package com.intirix.openmm.server.ui.html.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.beans.UserBean;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.UserInfoBean;

public class IndexBeanFactory implements PageBeanFactory
{
	
	private OpenMMServerRuntime runtime;
	
	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final IndexBean indexBean = new IndexBean();
		final UserInfoBean userInfo = new UserInfoBean();
		UserBean user;
		try
		{
			user = runtime.getApplicationLayer().getUserApp().getCurrentUserBean();
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( "Failed to get current user info", e );
		}
		userInfo.setAdmin( user.isAdmin() );
		userInfo.setDisplayName( user.getDisplayName() );
		userInfo.setUsername( user.getUsername() );
		userInfo.setUserId( user.getUserId() );
		indexBean.setUserInfo( userInfo );
		return indexBean;
	}

}
