package com.intirix.openmm.server.mt.app;

import java.util.List;

import com.intirix.openmm.server.api.beans.MediaLink;
import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.api.beans.MovieDetails;
import com.intirix.openmm.server.api.beans.MoviePrefixCounts;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.MovieMidtier;

public interface MovieApp
{
	/**
	 * Set the movie midtier
	 * @param midtier
	 */
	public void setMovieMidtier( MovieMidtier midtier );
	
	/**
	 * Set the WebCacheApp pointer
	 * @param webCacheApp
	 */
	public void setWebCacheApp( WebCacheApp webCacheApp );


	
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
	 * Get movie details for all movies that start with a prefix
	 * @param prefix
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public List< MovieDetails > listMovieDetails( String prefix ) throws OpenMMMidtierException;
	
	/**
	 * Get a movie by id
	 * @param movieId
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public Movie getMovieById( int movieId ) throws OpenMMMidtierException;
	
	/**
	 * Get all the movie prefices and their counts
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public List< MoviePrefixCounts > listMoviePrefixes() throws OpenMMMidtierException;
	
	/**
	 * Assign a file to a movie
	 * @param movieId
	 * @param file
	 * @param size size of the file
	 * @throws OpenMMMidtierException
	 */
	public void assignFile( int movieId, String file, long size ) throws OpenMMMidtierException;
	
	/**
	 * Unassign a file
	 * @param linkId
	 * @throws OpenMMMidtierException
	 */
	public void unassignFile( int linkId ) throws OpenMMMidtierException;

	
	/**
	 * Watch a movie
	 * @param movieId
	 * @throws OpenMMMidtierException
	 */
	public void watchMovie( int movieId ) throws OpenMMMidtierException;
	
	/**
	 * Get the media links for the movie
	 * @param movieId
	 * @return
	 * @throws OpenMMMidtierException
	 */
	public List< MediaLink > getMovieLinks( int movieId ) throws OpenMMMidtierException;


}
