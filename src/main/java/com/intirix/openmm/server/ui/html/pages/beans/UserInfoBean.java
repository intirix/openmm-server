package com.intirix.openmm.server.ui.html.pages.beans;

import org.simpleframework.xml.Default;

@Default
public class UserInfoBean implements Comparable< UserInfoBean >
{

	private int userId;

	private String username = "";
	
	private String displayName = "User";
	
	private boolean admin = false;

	public int getUserId()
	{
		return userId;
	}

	public void setUserId( int userId )
	{
		this.userId = userId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername( String username )
	{
		this.username = username;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName( String displayName )
	{
		this.displayName = displayName;
	}

	public boolean isAdmin()
	{
		return admin;
	}

	public void setAdmin( boolean admin )
	{
		this.admin = admin;
	}

	public int compareTo( UserInfoBean o )
	{
		return getUsername().compareToIgnoreCase( o.getUsername() );
	}

	
}
