package com.intirix.openmm.server.mt.app.password;

/**
 * Simple encoder that does nothing.  This is used to store a password in plain text
 * @author jeff
 *
 */
public class PlainPasswordEncoder implements PasswordEncoder
{

	public byte[] encode( String salt, byte[] input ) throws RuntimeException
	{
		return input;
	}

}
