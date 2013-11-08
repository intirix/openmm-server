package com.intirix.openmm.server.vfs;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TestFileSystemBrowser
{


	@Test
	public void testBrowser() throws IOException
	{
		final FileSystemBrowser fs1 = new FileSystemBrowser();
		final FileSystemBrowser fs2 = new FileSystemBrowser();
		
		fs1.mount( "/mnt/tmp", fs2 );
		
		fs2.addFolder( "/test/folder", "test.txt" );
		
		Assert.assertEquals( "mnt", fs1.listFolders( "/" )[ 0 ] );
		Assert.assertEquals( "tmp", fs1.listFolders( "/mnt" )[ 0 ] );
		Assert.assertEquals( "test", fs1.listFolders( "/mnt/tmp" )[ 0 ] );
		Assert.assertEquals( "folder", fs1.listFolders( "/mnt/tmp/test" )[ 0 ] );
		Assert.assertEquals( "test.txt", fs1.listFiles( "/mnt/tmp/test/folder" )[ 0 ] );
		
	}
}
