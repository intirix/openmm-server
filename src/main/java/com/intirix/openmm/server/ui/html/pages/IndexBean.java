package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Root;

import com.intirix.openmm.server.ui.html.PageData;
import com.intirix.openmm.server.ui.html.pages.beans.UserInfoBean;

@Root
@Default(DefaultType.PROPERTY)
public class IndexBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserInfoBean userInfo;

	public UserInfoBean getUserInfo()
	{
		return userInfo;
	}

	public void setUserInfo( UserInfoBean userInfo )
	{
		this.userInfo = userInfo;
	}
	
	

}
