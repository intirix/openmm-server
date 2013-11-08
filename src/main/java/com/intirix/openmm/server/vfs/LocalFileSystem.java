package com.intirix.openmm.server.vfs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Access the local filesystem
 * @author jeff
 *
 */
public class LocalFileSystem implements VFileSystem
{
	private final Log log = LogFactory.getLog( LocalFileSystem.class );

	private final String basePath;

	public LocalFileSystem( String basePath )
	{
		this.basePath = basePath;
	}

	public String[] listFolders( String path ) throws IOException
	{
		final File f = new File( basePath + '/' + path );
		final List< String > ret = new ArrayList< String >();
		if ( f.exists() )
		{
			for ( final File child: f.listFiles() )
			{
				if ( child.isDirectory() )
				{
					ret.add( child.getName() );
				}
			}
		}
		return ret.toArray( new String[]{} );
	}

	public String[] listFiles( String path ) throws IOException
	{
		final File f = new File( basePath + '/' + path );
		final List< String > ret = new ArrayList< String >();
		if ( f.exists() )
		{
			final File[] files = f.listFiles();
			if ( files != null )
			{
				for ( final File child: files )
				{
					if ( child.isFile() )
					{
						ret.add( child.getName() );
					}
				}
			}
		}
		return ret.toArray( new String[]{} );
	}

	public long getModifyTimestamp( String path ) throws IOException
	{
		final File f = new File( basePath + '/' + path );
		if ( f.exists() )
		{
			return f.lastModified();
		}
		return 0;
	}



	public long getFileLength( String path ) throws IOException
	{
		final File f = new File( basePath + '/' + path );
		if ( f.exists() )
		{
			return f.length();
		}
		return 0;
	}

	public VRandomAccessReadFile readFile( String path ) throws IOException
	{
		final File f = new File( basePath + '/' + path );
		return new LocalRandomAccessReadFile( new RandomAccessFile( f, "r" ) );
	}

	public void writeFile( String path, InputStream is ) throws IOException
	{
		final File f = new File( basePath + '/' + path );
		if ( !f.getParentFile().exists() )
		{
			log.info( "Creating " + f.getParentFile().getAbsolutePath() );
			f.mkdirs();
		}
		log.info( "Creating " + f.getAbsolutePath() );
		final OutputStream os = new FileOutputStream( f );
		try
		{
			IOUtils.copy( is, os );
		}
		finally
		{
			is.close();
			os.close();
		}
	}

	public void mkdirs( String path ) throws IOException
	{
		final File f = new File( basePath + '/' + path );
		if ( !f.exists() )
		{
			log.info( "Creating " + f.getAbsolutePath() );
			f.mkdirs();
		}
	}
	
	
	

}
