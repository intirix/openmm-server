package com.intirix.openmm.server.vfs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Browser for the file system
 * @author jeff
 *
 */
public class FileSystemBrowser implements VFileSystem
{

	private final VNode root = new VNode( "" );

	public FileSystemBrowser()
	{
	}

	/**
	 * Add a folder
	 * @param path
	 * @param name
	 */
	void addFolder( String path, String name )
	{
		final String[] parts = path.split( "/" );

		VNode node = root;

		for ( final String p: parts )
		{
			if ( p.length() > 0 )
			{
				VNode n = node.getSubFolder( p );
				if ( n == null )
				{
					n = new VNode( p );
					node.addFolder( n );
				}
				node = n;
			}
		}

		node.addFile( new VNode( name ) );
	}

	/**
	 * Mount a file system to the virtual file system
	 * @param path
	 * @param vfs
	 */
	public void mount( String path, VFileSystem vfs )
	{
		final String[] parts = path.split( "/" );

		VNode node = root;

		for ( final String p: parts )
		{
			if ( p.length() > 0 )
			{
				VNode n = node.getSubFolder( p );
				if ( n == null )
				{
					n = new VNode( p );
					node.addFolder( n );
				}
				node = n;
			}
		}

		node.addMount( vfs );
	}

	public String[] listFolders( String path ) throws IOException
	{

		final Set< String > set = new HashSet< String >( 16 );
		final String filename = path.replaceAll( ".*/", "" );
		final String[] parts = path.split( "/" );
		VNode node = root;

		final StringBuilder buffer = new StringBuilder( 128 );

		for ( int i = 0; i < parts.length; i++ )
		{
			final String p = parts[ i ];
			String base = null;
			if ( p.length() > 0 )
			{
				VNode n = node.getSubFolder( p );
				if ( n == null )
				{
					n = new VNode( p );
					node.addFolder( n );
				}
				node = n;


				buffer.append( '/' );
				buffer.append( node.getName() );


				base = buffer.toString();
			}
			else if ( p.length() == 0 && i == 0 )
			{
				base = "/";
			}

			for ( final VFileSystem vfs: node.getMounts() )
			{
				String vfsSubPath = "/";
				if ( base.length() > 1 )
				{
					vfsSubPath = path.replace( base, "" );
				}
				String vfsSubDir = "/";
				if ( vfsSubPath.length() <= 1 )
				{
					vfsSubDir = "/";
				}
				else
				{
					vfsSubDir = vfsSubPath;
				}

				final String[] vfsRet = vfs.listFolders( vfsSubDir );
				set.addAll( Arrays.asList( vfsRet ) );
			}

		}
		
		for ( final VFileSystem vfs: node.getMounts() )
		{
			final String[] vfsRet = vfs.listFolders( "/" );
			set.addAll( Arrays.asList( vfsRet ) );
		}

		for ( final VNode vn: node.getFolders() )
		{
			set.add( vn.getName() );
		}


		final String[] ret = set.toArray( new String[]{} );

		Arrays.sort( ret );

		return ret;
	}

	public String[] listFiles( String path ) throws IOException
	{
		final Set< String > set = new HashSet< String >( 16 );
		final String filename = path.replaceAll( ".*/", "" );
		final String[] parts = path.split( "/" );
		VNode node = root;

		final StringBuilder buffer = new StringBuilder( 128 );

		for ( int i = 0; i < parts.length; i++ )
		{
			final String p = parts[ i ];
			String base = null;
			if ( p.length() > 0 )
			{
				VNode n = node.getSubFolder( p );
				if ( n == null )
				{
					n = new VNode( p );
					node.addFolder( n );
				}
				node = n;


				buffer.append( '/' );
				buffer.append( node.getName() );


				base = buffer.toString();
			}
			else if ( p.length() == 0 && i == 0 )
			{
				base = "/";
			}

			for ( final VFileSystem vfs: node.getMounts() )
			{
				String vfsSubPath = "/";
				if ( base.length() > 1 )
				{
					vfsSubPath = path.replace( base, "" );
				}
				String vfsSubDir = "/";
				if ( vfsSubPath.length() <= 1 )
				{
					vfsSubDir = "/";
				}
				else
				{
					vfsSubDir = new File( vfsSubDir ).getParent();
				}
				final String[] vfsRet = vfs.listFiles( vfsSubPath );
				set.addAll( Arrays.asList( vfsRet ) );

			}			
		}
		
		for ( final VFileSystem vfs: node.getMounts() )
		{
			final String[] vfsRet = vfs.listFiles( "/" );
			set.addAll( Arrays.asList( vfsRet ) );
		}

		for ( final VNode vn: node.getFiles() )
		{
			set.add( vn.getName() );
		}


		final String[] ret = set.toArray( new String[]{} );

		Arrays.sort( ret );

		return ret;
	}

	public long getModifyTimestamp( String path ) throws IOException
	{
		final String filename = path.replaceAll( ".*/", "" );
		final String[] parts = path.split( "/" );
		VNode node = root;

		final StringBuilder buffer = new StringBuilder( 128 );

		for ( int i = 0; i < parts.length; i++ )
		{
			final String p = parts[ i ];
			String base = null;
			if ( p.length() > 0 )
			{
				VNode n = node.getSubFolder( p );
				if ( n == null )
				{
					n = new VNode( p );
					node.addFolder( n );
				}
				node = n;


				buffer.append( '/' );
				buffer.append( node.getName() );


				base = buffer.toString();
			}
			else if ( p.length() == 0 && i == 0 )
			{
				base = "/";
			}

			for ( final VFileSystem vfs: node.getMounts() )
			{
				String vfsSubPath = "/";
				if ( base.length() > 1 )
				{
					vfsSubPath = path.replace( base, "" );
				}
				String vfsSubDir = "/";
				if ( vfsSubPath.length() <= 1 )
				{
					vfsSubDir = "/";
				}
				else
				{
					vfsSubDir = new File( vfsSubPath ).getParent();
				}

				final String[] vfsRet = vfs.listFiles( vfsSubDir );
				for ( final String file: vfsRet )
				{
					if ( filename.equals(  file  ) )
					{
						return vfs.getModifyTimestamp( vfsSubDir + '/' + filename );
					}
				}
			}
		}

		return 0;
	}



	public long getFileLength( String path ) throws IOException
	{
		final String filename = path.replaceAll( ".*/", "" );
		final String[] parts = path.split( "/" );
		VNode node = root;

		final StringBuilder buffer = new StringBuilder( 128 );

		for ( int i = 0; i < parts.length; i++ )
		{
			final String p = parts[ i ];
			String base = null;
			if ( p.length() > 0 )
			{
				VNode n = node.getSubFolder( p );
				if ( n == null )
				{
					n = new VNode( p );
					node.addFolder( n );
				}
				node = n;


				buffer.append( '/' );
				buffer.append( node.getName() );


				base = buffer.toString();
			}
			else if ( p.length() == 0 && i == 0 )
			{
				base = "/";
			}

			for ( final VFileSystem vfs: node.getMounts() )
			{
				String vfsSubPath = "/";
				if ( base.length() > 1 )
				{
					vfsSubPath = path.replace( base, "" );
				}
				String vfsSubDir = "/";
				if ( vfsSubPath.length() <= 1 )
				{
					vfsSubDir = "/";
				}
				else
				{
					vfsSubDir = new File( vfsSubPath ).getParent();
				}


				final String[] vfsRet = vfs.listFiles( vfsSubDir );
				for ( final String file: vfsRet )
				{
					if ( filename.equals( file ) )
					{
						return vfs.getFileLength( vfsSubDir + '/' + filename );
					}
				}
			}
		}

		return 0;
	}

	public VRandomAccessReadFile readFile( String path ) throws IOException
	{
		final String filename = path.replaceAll( ".*/", "" );
		final String[] parts = path.split( "/" );
		VNode node = root;

		final StringBuilder buffer = new StringBuilder( 128 );

		for ( int i = 0; i < parts.length; i++ )
		{
			final String p = parts[ i ];
			String base = null;
			if ( p.length() > 0 )
			{
				VNode n = node.getSubFolder( p );
				if ( n == null )
				{
					n = new VNode( p );
					node.addFolder( n );
				}
				node = n;


				buffer.append( '/' );
				buffer.append( node.getName() );


				base = buffer.toString();
			}
			else if ( p.length() == 0 && i == 0 )
			{
				base = "/";
			}

			for ( final VFileSystem vfs: node.getMounts() )
			{
				String vfsSubPath = "/";
				if ( base.length() > 1 )
				{
					vfsSubPath = path.replace( base, "" );
				}
				String vfsSubDir = "/";
				if ( vfsSubPath.length() <= 1 )
				{
					vfsSubDir = "/";
				}
				else
				{
					vfsSubDir = new File( vfsSubPath ).getParent();
				}

				final String[] vfsRet = vfs.listFiles( vfsSubDir );
				for ( final String file: vfsRet )
				{
					if ( filename.equals(  file  ) )
					{
						return vfs.readFile( vfsSubDir + '/' + filename );
					}
				}
			}
		}

		return null;
	}

	public void writeFile( String path, InputStream is ) throws IOException
	{
		final String filename = path.replaceAll( ".*/", "" );
		final String[] parts = path.split( "/" );
		VNode node = root;

		final StringBuilder buffer = new StringBuilder( 128 );

		for ( int i = 0; i < parts.length; i++ )
		{
			final String p = parts[ i ];
			String base = null;
			if ( p.length() > 0 )
			{
				VNode n = node.getSubFolder( p );
				if ( n == null )
				{
					n = new VNode( p );
					node.addFolder( n );
				}
				node = n;


				buffer.append( '/' );
				buffer.append( node.getName() );


				base = buffer.toString();
			}
			else if ( p.length() == 0 && i == 0 )
			{
				base = "/";
			}

			for ( final VFileSystem vfs: node.getMounts() )
			{
				String vfsSubPath = "/";
				if ( base.length() > 1 )
				{
					vfsSubPath = path.replace( base, "" );
				}
				String vfsSubDir = "/";
				if ( vfsSubPath.length() <= 1 )
				{
					vfsSubDir = "/";
				}
				else
				{
					vfsSubDir = new File( vfsSubPath ).getParent();
				}


				vfs.writeFile( vfsSubDir + '/' + filename, is );
			}

		}
	}

	public void mkdirs( String path ) throws IOException
	{
		final String[] parts = path.split( "/" );
		VNode node = root;

		final StringBuilder buffer = new StringBuilder( 128 );

		for ( int i = 0; i < parts.length; i++ )
		{
			final String p = parts[ i ];
			if ( p.length() > 0 )
			{
				VNode n = node.getSubFolder( p );
				if ( n == null )
				{
					n = new VNode( p );
					node.addFolder( n );
				}
				node = n;


				buffer.append( '/' );
				buffer.append( node.getName() );


				final String base = buffer.toString();

				for ( final VFileSystem vfs: node.getMounts() )
				{
					String vfsSubPath = path.replace( base, "" );
					String vfsSubDir = "/";
					if ( vfsSubPath.length() == 0 )
					{
						vfsSubPath = "/";
					}
					else
					{
						vfsSubDir = new File( vfsSubPath ).getParent();
					}

					vfs.mkdirs( vfsSubDir );
				}

			}			
		}
	}

	public boolean exists( String path ) throws IOException
	{
		final Set< String > filesInDir = new HashSet< String >( 16 );
		String parent = path;
		if ( path.length() > 1 )
		{
			parent = new File( path ).getParent();
		}
		filesInDir.addAll( Arrays.asList( listFolders( parent ) ) );
		filesInDir.addAll( Arrays.asList( listFiles( parent ) ) );

		return filesInDir.contains( path.replaceFirst( ".*/", "" ) );
	}



}
