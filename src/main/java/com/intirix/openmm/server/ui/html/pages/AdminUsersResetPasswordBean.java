package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.UserInfoBean;

@Default
public class AdminUsersResetPasswordBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserInfoBean user;

	public UserInfoBean getUser()
	{
		return user;
	}

	public void setUser( UserInfoBean user )
	{
		this.user = user;
	}

	
	
	
	
}
