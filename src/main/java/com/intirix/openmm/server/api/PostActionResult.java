package com.intirix.openmm.server.api;

public class PostActionResult
{

	private String actionMessage = "";
	
	private String redirectUrl = null;

	public String getActionMessage()
	{
		return actionMessage;
	}

	public void setActionMessage( String actionMessage )
	{
		this.actionMessage = actionMessage;
	}

	public String getRedirectUrl()
	{
		return redirectUrl;
	}

	public void setRedirectUrl( String redirectUrl )
	{
		this.redirectUrl = redirectUrl;
	}
	
	
	
}
