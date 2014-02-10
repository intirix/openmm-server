package com.intirix.openmm.server.mt;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TimingProxy< E > implements InvocationHandler
{
	
	private final E inst;
	
	private final Log log;
	
	public TimingProxy( E inst )
	{
		this.inst = inst;
		log = LogFactory.getLog( inst.getClass() );
	}

	public Object invoke( Object inst, Method m, Object[] args ) throws Throwable
	{
		final long t1 = System.currentTimeMillis();
		try
		{
			return m.invoke( this.inst, args );
		}
		finally
		{
			final long t2 = System.currentTimeMillis();
			final long dt = t2 - t1;
			log.trace( "Executed " + m.getName() + "() in " + dt + "ms" );
		}
	}

	
	public static < F > F create( Class< F > kl, Object inst )
	{
		final F ret = (F)Proxy.newProxyInstance( inst.getClass().getClassLoader(), new Class[]{ kl }, new TimingProxy< F >( (F)inst ) );
		return ret;
	}

}
