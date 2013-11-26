package com.intirix.openmm.server.mt.technical.beans;

public class UserBean implements Cloneable
{
	private int userId;

	private String username = "";
	
	private String displayName = "User";
	
	private boolean admin = false;
	
	private String salt = "";
	
	private String passwordPipeline = "PLAIN";
	
	private String encodedPassword = "";

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

	public String getSalt()
	{
		return salt;
	}

	public void setSalt( String salt )
	{
		this.salt = salt;
	}

	public String getPasswordPipeline()
	{
		return passwordPipeline;
	}

	public void setPasswordPipeline( String passwordPipeline )
	{
		this.passwordPipeline = passwordPipeline;
	}

	public String getEncodedPassword()
	{
		return encodedPassword;
	}

	public void setEncodedPassword( String encodedPassword )
	{
		this.encodedPassword = encodedPassword;
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		final UserBean bean = new UserBean();
		
		bean.setAdmin( isAdmin() );
		bean.setDisplayName( getDisplayName() );
		bean.setEncodedPassword( getEncodedPassword() );
		bean.setPasswordPipeline( getPasswordPipeline() );
		bean.setSalt( getSalt() );
		bean.setUserId( getUserId() );
		bean.setUsername( getUsername() );
		
		return bean;
	}

	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "User: " + getUsername();
	}
	
	
	
}
