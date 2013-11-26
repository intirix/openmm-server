package com.intirix.openmm.server.mt.app.password;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Encode using SHA256
 * @author jeff
 *
 */
public class Sha256Encoder implements PasswordEncoder
{

	public byte[] encode( String salt, byte[] input ) throws RuntimeException
	{
		return DigestUtils.sha256( input );
	}

}
