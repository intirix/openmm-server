package com.intirix.openmm.server.mt.technical.rottentomatoes;

import it.jtomato.gson.Movie;

import java.util.List;

public interface RTMidtier
{
	
	/**
	 * Does the midtier have a key to interact with RT
	 * @return
	 */
	public boolean hasKey();

	/**
	 * Set the Rotten Tomatoes API key
	 * @param key
	 */
	public void setKey( String key );
	
	/**
	 * Get the Rotten Tomatoes API key
	 * @return
	 */
	public String getKey();

	/**
	 * Get a movie by id
	 * @param id
	 * @return
	 */
	public Movie getMovieById( String id );
	
	/**
	 * Find movies
	 * @param query
	 * @return
	 */
	public List< Movie > searchMovies( String query );
}
