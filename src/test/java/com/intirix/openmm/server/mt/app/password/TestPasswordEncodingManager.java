package com.intirix.openmm.server.mt.app.password;

import org.apache.commons.codec.EncoderException;
import org.junit.Assert;
import org.junit.Test;

public class TestPasswordEncodingManager
{

	@Test
	public void testEncodings() throws EncoderException
	{
		Assert.assertEquals( "TEST", PasswordEncodingManager.encodePassword( null, "TEST", "PLAIN" ) );
		Assert.assertEquals( "54455354", PasswordEncodingManager.encodePassword( null, "TEST", "PLAIN,HEX" ) );
		Assert.assertEquals( "ABC:TEST", PasswordEncodingManager.encodePassword( "ABC", "TEST", "SALT" ) );
		Assert.assertEquals( "5656d88a4dfede00d8970045f3fc5ee1b1c51e53e37c5904d27c1b0b87e1c27e", PasswordEncodingManager.encodePassword( "ABC", "TEST", "SALT,SHA256,HEX" ) );
	}
}
