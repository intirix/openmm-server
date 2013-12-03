package com.intirix.openmm.server.mt.technical;

import java.util.List;

import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.mt.OpenMMMidtierException;

public interface MovieMidtier
{

	/**
	 * Add a movie
	 * @param movie
	 * @throws OpenMMMidtierException
	 */
	public int addMovie( Movie movie ) throws OpenMMMidtierException;
	
	/**
	 * Delete a movie
	 * @param movieId
	 * @throws OpenMMMidtierException
	 */
	public void deleteMovie( int movieId ) throws OpenMMMidtierException;
	
	/**
	 * Update a movie
	 * @param oldBean
	 * @param newBean
	 * @throws OpenMMMidtierException
	 */
	public void updateMovie( Movie oldBean, Movie newBean ) throws OpenMMMidtierException;
	
	/**
	 * List all movies
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public List< Movie > listMovies() throws OpenMMMidtierException;
	
	/**
	 * Watch a movie
	 * @param movieId
	 * @throws OpenMMMidtierException
	 */
	public void watchMovie( int movieId ) throws OpenMMMidtierException;
}
