package com.intirix.openmm.server.mt.technical.rottentomatoes;

import it.jtomato.JTomato;
import it.jtomato.gson.Movie;
import it.jtomato.net.NetHttpClient;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RTMidtierImpl implements RTMidtier
{
	private String key;

	/**
	 * Rotten Tomatoes API
	 */
	private JTomato api;

	/**
	 * Logger
	 */
	private final Log log = LogFactory.getLog( RTMidtierImpl.class );


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

		// try to get the synopsis from the list instead of the get
		for ( final Movie result: searchMovies( m.title ) )
		{
			if ( id.equals( result.id ) )
			{
				m.synopsis = result.synopsis;
			}
		}

		// Rotten Tomatoes doesn't always provide the description
		if ( m.synopsis == null || m.synopsis.length() == 0 )
		{
			try
			{
				final NetHttpClient client = new NetHttpClient();
				final String url = "http://omdbapi.com/?i=&t=" + URLEncoder.encode( m.title, "UTF-8" ) + "&y=" + m.year;
				log.debug( "Synopsis is empty, trying to download from omdbapi: " + url );
				final String response = client.get( url );
				
				log.debug( "Got response: " + response );

				JsonParser parser = new JsonParser();
				JsonObject jsonResponse = parser.parse(response).getAsJsonObject();
				final JsonElement plotElement = jsonResponse.get( "Plot" );
				if ( plotElement == null )
				{
					log.debug( "Could not find plot" );
				}
				else
				{
					m.synopsis = plotElement.getAsString();
				}
			}
			catch ( Exception e )
			{
				log.error( "Failed to download from OMDB Api", e );
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
