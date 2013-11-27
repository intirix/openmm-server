package com.intirix.openmm.server.vfs;

import java.io.IOException;
import java.io.InputStream;

public interface VFileSystem
{

	/**
	 * List the sub-folders in a path
	 * @return
	 * @throws IOException
	 */
	public String[] listFolders( String path ) throws IOException;
	
	/**
	 * List the files in a path
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public String[] listFiles( String path ) throws IOException;
	
	/**
	 * Is the path a directory
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public boolean isDirectory( String path ) throws IOException;
	
	/**
	 * Get the modify timestamp of a file
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public long getModifyTimestamp( String path ) throws IOException;
	
	/**
	 * Get the length of the file
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public long getFileLength( String path ) throws IOException;
	
	/**
	 * Read a file
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public VRandomAccessReadFile readFile( String path ) throws IOException;
	
	/**
	 * Make directory
	 * @param path
	 * @throws IOException
	 */
	public void mkdirs( String path ) throws IOException;
	
	/**
	 * Write a file
	 * @param path
	 * @param is
	 * @throws IOException
	 */
	public void writeFile( String path, InputStream is ) throws IOException;
}
