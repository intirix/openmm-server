package com.intirix.openmm.server.vfs;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Wrapper for RandomAccessFile
 * @author jeff
 *
 */
public class LocalRandomAccessReadFile implements VRandomAccessReadFile
{
	
	private final RandomAccessFile file;

	public LocalRandomAccessReadFile( RandomAccessFile file)
	{
		this.file = file;
	}

	public void seek( long pos ) throws IOException
	{
		file.seek( pos );
	}

	public long length() throws IOException
	{
		return file.length();
	}

	public int read( byte[] buffer ) throws IOException
	{
		return file.read( buffer );
	}

	public void close() throws IOException
	{
		file.close();
	}
	
	

}
