package com.intirix.openmm.server.vfs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.vfs2.FileSystemException;

import com.intirix.openmm.server.mt.technical.beans.HttpRootFolder;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;

public class FileSystemFactory
{

	private final Log log = LogFactory.getLog( FileSystemFactory.class );

	public VFileSystem createFileSystem( RootFolder folder )
	{
		final String type = folder.getUrl().split( ":" )[ 0 ];
		if ( "file".equals( type ) )
		{
			final LocalFileSystem lfs = new LocalFileSystem( folder.getUrl().replaceFirst( "file://", "" ) );
			return lfs;
		}
		else if ( "http".equals( type ) )
		{
			final HttpRootFolder folder2 = (HttpRootFolder)folder;
			try
			{
				return new HttpFileSystem( folder2.getUrl(), folder2.getUsername(), folder2.getPassword() );
			}
			catch ( FileSystemException e )
			{
				log.warn( "Failed to create http filesystem", e );
			}
		}
		return null;
	}

}
