package com.intirix.openmm.server.mt.app.password;

/**
 * Base interface for encoding/encrypting a password as part of a pipeline
 * @author jeff
 *
 */
public interface PasswordEncoder
{

	/**
	 * Encode the password as part of a pipeline
	 * @param salt
	 * @param input
	 * @return
	 * @throws RuntimeException
	 */
	public byte[] encode( String salt, byte[] input ) throws RuntimeException;
}
