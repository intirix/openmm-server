package com.intirix.openmm.server.mt.technical;

import java.util.List;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.beans.RootFolder;

public interface ConfigMidtier
{

	/**
	 * List the root folders
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public List< RootFolder > listRootFolders() throws OpenMMMidtierException;
	
	/**
	 * Add a root folder
	 * @param folder
	 * @throws OpenMMMidtierException
	 */
	public void addRootFolder( RootFolder folder ) throws OpenMMMidtierException;
	
	/**
	 * Get a root folder
	 * @param folderId
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public RootFolder getRootFolder( int folderId ) throws OpenMMMidtierException;
	
	/**
	 * Delete a root folder
	 * @param folderId
	 * @throws OpenMMMidtierException
	 */
	public void deleteRootFolder( int folderId ) throws OpenMMMidtierException;
	
	/**
	 * Update a folder bean
	 * @param oldBean
	 * @param newBean
	 * @throws OpenMMMidtierException
	 */
	public void updateRootFolder( RootFolder oldBean, RootFolder newBean ) throws OpenMMMidtierException;

}
