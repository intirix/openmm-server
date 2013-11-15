package com.intirix.openmm.server.mt;

public class OpenMMMidtierException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpenMMMidtierException()
	{
		// TODO Auto-generated constructor stub
	}

	public OpenMMMidtierException( String message )
	{
		super( message );
		// TODO Auto-generated constructor stub
	}

	public OpenMMMidtierException( Throwable cause )
	{
		super( cause );
		// TODO Auto-generated constructor stub
	}

	public OpenMMMidtierException( String message, Throwable cause )
	{
		super( message, cause );
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		return getClass().getSimpleName() + ": " + getMessage();
	}



}
