package com.intirix.openmm.server.mt.app.password;

import org.apache.commons.codec.binary.Hex;

/**
 * Encode using hex
 * @author jeff
 *
 */
public class HexPasswordEncoder implements PasswordEncoder
{

	public byte[] encode( String salt, byte[] input ) throws RuntimeException
	{
		return Hex.encodeHexString( input ).getBytes();
	}

}
