package com.intirix.openmm.server.mt.technical.impl;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.WebCacheMidtier;

public class WebCacheMidtierDecorator implements WebCacheMidtier
{
	
	private final WebCacheMidtier child;
	
	public WebCacheMidtierDecorator( WebCacheMidtier child )
	{
		this.child = child;
	}

	public String registerCachableUrl( String url ) throws OpenMMMidtierException
	{
		return child.registerCachableUrl( url );
	}

	public String getUrlForHash( String hash ) throws OpenMMMidtierException
	{
		return child.getUrlForHash( hash );
	}

}
