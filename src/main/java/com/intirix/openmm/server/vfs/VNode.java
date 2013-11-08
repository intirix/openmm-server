package com.intirix.openmm.server.vfs;

import java.util.ArrayList;
import java.util.List;

class VNode
{

	private final String name;
	
	private final List< VNode > folders = new ArrayList< VNode >( 4 );
	
	private final List< VNode > files = new ArrayList< VNode >( 4 );
	
	private final List< VFileSystem > mounts = new ArrayList< VFileSystem >( 4 );
	
	public VNode( String name )
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	
	public void addFolder( VNode folder )
	{
		folders.add( folder );
	}

	public List< VNode > getFolders()
	{
		return folders;
	}
	
	/**
	 * Get a sub folder
	 * @param name
	 * @return
	 */
	public VNode getSubFolder( String name )
	{
		for ( final VNode n: folders )
		{
			if ( name.equals( n.getName() ) )
			{
				return n;
			}
		}
		return null;
	}
	
	public void addFile( VNode file )
	{
		files.add( file );
	}

	public List< VNode > getFiles()
	{
		return files;
	}
	
	public void addMount( VFileSystem vfs )
	{
		mounts.add( vfs );
	}

	public List< VFileSystem > getMounts()
	{
		return mounts;
	}

	@Override
	public String toString()
	{
		return '[' + name + "] - " + super.toString();
	}
	
	
	

}
