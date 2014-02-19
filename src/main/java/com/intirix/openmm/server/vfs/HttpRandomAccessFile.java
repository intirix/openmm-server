package com.intirix.openmm.server.vfs;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.RandomAccessContent;
import org.apache.commons.vfs2.util.RandomAccessMode;

/**
 * Random access file for the http backend
 * @author jeff
 *
 */
final class HttpRandomAccessFile implements VRandomAccessReadFile
{
	
	private final FileContent content;
	
	private final RandomAccessContent rac;
	
	private InputStream is;

	public HttpRandomAccessFile( final FileContent content ) throws IOException
	{
		this.content = content;
		rac = content.getRandomAccessContent( RandomAccessMode.READ );
		is = rac.getInputStream();
	}

	public void close() throws IOException
	{
		content.close();
	}

	public void seek( long pos ) throws IOException
	{
		rac.seek( pos );
		is = rac.getInputStream();
	}

	public long length() throws IOException
	{
		return content.getSize();
	}

	public int read( byte[] buffer ) throws IOException
	{
		return is.read( buffer );
	}

}
