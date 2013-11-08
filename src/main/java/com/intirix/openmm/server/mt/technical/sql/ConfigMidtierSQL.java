package com.intirix.openmm.server.mt.technical.sql;

import java.util.List;

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
			return sqlHelper.executeQuery( new RootFolderObjectFactory(), "root_folder_list_folders.sql" );
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
			return sqlHelper.executeQuerySingleRow( new RootFolderObjectFactory(), "root_folder_get_folder.sql", folderId );
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
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}
	
	

}
