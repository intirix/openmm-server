package com.intirix.openmm.server.mt.technical.rottentomatoes;

import it.jtomato.JTomato;
import it.jtomato.gson.Movie;

import java.util.ArrayList;
import java.util.List;

public class RTMidtierImpl implements RTMidtier
{
	private String key;
	
	private JTomato api;
	
	

	public boolean hasKey()
	{
		return key != null && key.length() > 0;
	}

	public void setKey( String key )
	{
		api = new JTomato( key );
		this.key = key;
	}

	public String getKey()
	{
		return key;
	}

	public Movie getMovieById( String id )
	{
		Movie m = new Movie();
		m.id = id;
		m = api.getMovieAdditionalInfo( m );
		
		// for some reason, the synopsis only comes back from the search, not the get details
		for ( final Movie result: searchMovies( m.title ) )
		{
			if ( id.equals( result.id ) )
			{
				m.synopsis = result.synopsis;
			}
		}
		return m;
	}

	public List< Movie > searchMovies( String query )
	{
		final List< Movie > list = new ArrayList< Movie >( 25 );
		api.searchMovie( query, list, 1 );
		return list;
	}

}
