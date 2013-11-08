package com.intirix.openmm.server.vfs;

import com.intirix.openmm.server.mt.technical.beans.RootFolder;

public class FileSystemFactory
{


	public VFileSystem createFileSystem( RootFolder folder )
	{
		final String type = folder.getUrl().split( ":" )[ 0 ];
		if ( "file".equals( type ) )
		{
			final LocalFileSystem lfs = new LocalFileSystem( folder.getUrl().replaceFirst( "file://", "" ) );
			return lfs;
		}
		return null;
	}

}
