package com.intirix.openmm.server.mt.technical.impl.cache;

import java.util.Map;
import java.util.WeakHashMap;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.WebCacheMidtier;
import com.intirix.openmm.server.mt.technical.impl.WebCacheMidtierDecorator;

public class WebCacheMidtierCache extends WebCacheMidtierDecorator
{
	
	private final Map< String, String > cache = new WeakHashMap< String, String >( 128 );

	public WebCacheMidtierCache( WebCacheMidtier child )
	{
		super( child );
	}

	@Override
	public String getUrlForHash( String hash ) throws OpenMMMidtierException
	{
		synchronized ( cache )
		{
			if ( cache.containsKey( hash ) )
			{
				return cache.get( hash );
			}
		}
		
		final String ret = super.getUrlForHash( hash );
		
		synchronized ( cache )
		{
			cache.put( hash, ret );
		}
		return ret;
	}
	
	

}
