package com.intirix.openmm.server.mt.app.password;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.EncoderException;

public final class PasswordEncodingManager
{

	private static final Map< String, PasswordEncoder > encoders = new HashMap< String, PasswordEncoder >( 16 );
	
	static
	{
		encoders.put( "PLAIN", new PlainPasswordEncoder() );
		encoders.put( "SALT", new SaltPasswordEncoder() );
		encoders.put( "SHA256", new Sha256Encoder() );
		encoders.put( "HEX", new HexPasswordEncoder() );
	}

	/**
	 * Encode a password using a salt and the given pipeline
	 * @param salt
	 * @param password
	 * @param pipeline
	 * @return
	 * @throws EncoderException 
	 */
	public static String encodePassword( String salt, String password, String pipeline ) throws EncoderException
	{
		byte[] data = password.getBytes();
		for ( final String part: pipeline.split( "," ) )
		{
			if ( !encoders.containsKey( part ) )
			{
				throw new EncoderException( "Invalid encoder: " + part );
			}
			data = encoders.get( part ).encode( salt, data );
		}
		return new String( data );
	}
}
