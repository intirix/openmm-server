package com.intirix.openmm.server.api;

import com.intirix.openmm.server.OpenMMServerRuntime;

public class PostActionEngine
{
	
	private OpenMMServerRuntime runtime;
	
	

	public OpenMMServerRuntime getRuntime()
	{
		return runtime;
	}



	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}



	public PostAction getAction( String action ) throws OpenMMAPIException
	{
		if (action == null )
		{
			return null;
		}
		
		final String klassName = "com.intirix.openmm.server.api.postactions." + action + "Action";
		try
		{
			final Class< ? > klass = Class.forName( klassName );
			if ( PostAction.class.isAssignableFrom( klass ) )
			{
				final PostAction obj = (PostAction)klass.newInstance();
				obj.setRuntime( runtime );
				return obj;
			}
			throw new OpenMMAPIException( "Could not find action [" + action + ']' );
		}
		catch ( Exception e )
		{
			throw new OpenMMAPIException( "Could not find action [" + action + ']', e );
		}
	}
}
