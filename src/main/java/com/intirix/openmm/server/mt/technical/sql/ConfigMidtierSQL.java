package com.intirix.openmm.server.mt.technical.sql;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;
import com.intirix.openmm.server.mt.technical.beans.RootFolderObjectFactory;

public final class ConfigMidtierSQL implements ConfigMidtier
{

	private final SQLHelper sqlHelper;

	public ConfigMidtierSQL( DataSource ds )
	{
		sqlHelper = new SQLHelper( getClass(), ds );
	}

	public List< RootFolder > listRootFolders() throws OpenMMMidtierException
	{
		try
		{
			final List< RootFolder > ret = sqlHelper.executeQuery( new RootFolderObjectFactory(), "root_folder_list_folders.sql" );

			for ( final RootFolder folder: ret )
			{
				final Properties props = sqlHelper.executeQueryLookupTable( "root_folder_list_folder_options.sql", folder.getId() );
				folder.loadConfigFromProperties( props );
			}

			return ret;			
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public RootFolder getRootFolder( int folderId ) throws OpenMMMidtierException
	{
		try
		{
			final RootFolder folder = sqlHelper.executeQuerySingleRow( new RootFolderObjectFactory(), "root_folder_get_folder.sql", folderId );
			final Properties props = sqlHelper.executeQueryLookupTable( "root_folder_list_folder_options.sql", folder.getId() );
			folder.loadConfigFromProperties( props );
			return folder;
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public void addRootFolder( RootFolder folder ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "root_folder_add_file_folder.sql", folder.getMountPoint(), folder.getUrl() );

			// determine the folder id
			int folderId = -1;
			for ( final RootFolder f: sqlHelper.executeQuery( new RootFolderObjectFactory(), "root_folder_list_folders.sql" ) )
			{
				if ( f.getMountPoint().equals( folder.getMountPoint() ) )
				{
					folderId = f.getId();
				}
			}


			final Properties props = folder.createPropertiesFromConfig();
			for ( final Object k: props.keySet() )
			{
				final String key = k.toString();
				sqlHelper.executeUpdate( "root_folder_add_option.sql", folderId, key, props.getProperty( key ) );
			}
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public void deleteRootFolder( int folderId ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "root_folder_delete_options.sql", folderId );
			sqlHelper.executeUpdate( "root_folder_delete_folder.sql", folderId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public void updateRootFolder( RootFolder oldBean, RootFolder newBean ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "root_folder_update_folder.sql", newBean.getMountPoint(), newBean.getUrl(), oldBean.getId() );
			sqlHelper.executeUpdate( "root_folder_delete_options.sql", oldBean.getId() );

			final Properties props = newBean.createPropertiesFromConfig();
			for ( final Object k: props.keySet() )
			{
				final String key = k.toString();
				sqlHelper.executeUpdate( "root_folder_add_option.sql", oldBean.getId(), key, props.getProperty( key ) );
			}
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}



}
