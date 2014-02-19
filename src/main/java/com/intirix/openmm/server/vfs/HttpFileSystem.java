package com.intirix.openmm.server.vfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;

public class HttpFileSystem implements VFileSystem
{
	
	private String url;
	
	private String username;
	
	private String password;
	
	private final FileSystemManager vfs;
	
	private final FileObject root;

	public HttpFileSystem( String url, String username, String password ) throws FileSystemException
	{
		this.url = url;
		this.username = username;
		this.password = password;
		vfs = VFS.getManager();
		
		if ( username != null && password != null )
		{
			final StaticUserAuthenticator sua = new StaticUserAuthenticator( null, username, password );
			final FileSystemOptions opts = new FileSystemOptions();
			DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator( opts, sua );
			root = vfs.resolveFile( url + '/', opts );
		}
		else
		{
			root = vfs.resolveFile( url + '/' );
		}
	}
	
	public String[] listFolders( String path ) throws IOException
	{
		final List< String > ret = new ArrayList< String >();
		final FileObject fo = root.resolveFile( "." + path + '/' );
		if ( fo == null || !fo.exists() )
		{
			throw new FileNotFoundException( path );
		}
		for ( final FileObject foc: fo.getChildren() )
		{
			if ( foc.getType() == FileType.FOLDER )
			{
				ret.add( foc.getName().getBaseName() );
			}
		}
		return ret.toArray( new String[]{} );
	}

	public String[] listFiles( String path ) throws IOException
	{
		final List< String > ret = new ArrayList< String >();
		final FileObject fo = root.resolveFile( "." + path + '/' );
		if ( fo == null || !fo.exists() )
		{
			throw new FileNotFoundException( path );
		}
		for ( final FileObject foc: fo.getChildren() )
		{
			if ( foc.getType() == FileType.FILE )
			{
				ret.add( foc.getName().getBaseName() );
			}
		}
		return ret.toArray( new String[]{} );
	}

	public boolean isDirectory( String path ) throws IOException
	{
		final FileObject fo = root.resolveFile( "." + path );
		if ( fo == null || !fo.exists() )
		{
			throw new FileNotFoundException( path );
		}
		return ( fo.getType() == FileType.FOLDER );
	}

	public long getModifyTimestamp( String path ) throws IOException
	{
		final FileObject fo = root.resolveFile( "." + path );
		if ( fo == null || !fo.exists() )
		{
			throw new FileNotFoundException( path );
		}
		return fo.getContent().getLastModifiedTime();
	}

	public long getFileLength( String path ) throws IOException
	{
		final FileObject fo = root.resolveFile( "." + path );
		if ( fo == null || !fo.exists() )
		{
			throw new FileNotFoundException( path );
		}
		return fo.getContent().getSize();
	}

	public VRandomAccessReadFile readFile( String path ) throws IOException
	{
		final FileObject fo = root.resolveFile( "." + path );
		if ( fo == null || !fo.exists() )
		{
			throw new FileNotFoundException( path );
		}
		return new HttpRandomAccessFile( fo.getContent() );
	}

	public void mkdirs( String path ) throws IOException
	{
		throw new IOException( "Not implemented" );
	}

	public void writeFile( String path, InputStream is ) throws IOException
	{
		throw new IOException( "Not implemented" );
	}

}
