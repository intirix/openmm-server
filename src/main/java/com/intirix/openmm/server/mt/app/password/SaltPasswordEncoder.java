package com.intirix.openmm.server.mt.app.password;

/**
 * Prepend the salt to the password
 * @author jeff
 *
 */
public class SaltPasswordEncoder implements PasswordEncoder
{

	public byte[] encode( String salt, byte[] input ) throws RuntimeException
	{
		return ( salt + ':' + new String( input ) ).getBytes();
	}

}
