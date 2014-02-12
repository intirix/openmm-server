package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.UserInfoBean;

@Default
public class AdminUsersBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserInfoBean users[];

	public UserInfoBean[] getUsers()
	{
		return users;
	}

	public void setUsers( UserInfoBean[] users )
	{
		this.users = users;
	}
	
	
	
	
}
