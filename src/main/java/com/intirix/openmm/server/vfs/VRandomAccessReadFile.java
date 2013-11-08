package com.intirix.openmm.server.vfs;

import java.io.Closeable;
import java.io.IOException;

/**
 * Interface that virtual filesystems need to implement
 * @author jeff
 *
 */
public interface VRandomAccessReadFile extends Closeable
{
	
	/**
	 * Seek into the file
	 * @param pos
	 * @throws IOException
	 */
	public void seek( long pos ) throws IOException;
	
	/**
	 * Get the length of the file
	 * @return
	 * @throws IOException
	 */
	public long length() throws IOException;
	
	/**
	 * Read from the file
	 * @param buffer
	 * @return
	 * @throws IOException
	 */
	public int read( byte[] buffer ) throws IOException;

}
