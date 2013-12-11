package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.Element;

import com.intirix.openmm.server.api.beans.Movie;
import com.intirix.openmm.server.api.beans.MovieDetails;
import com.intirix.openmm.server.api.beans.MoviePrefixCounts;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class AssignMovieFilesBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int movieId = -1;
	
	private boolean selected = false;
	
	@Element(required=false)
	private String prefix = null;
	
	private MoviePrefixCounts[] prefixes = new MoviePrefixCounts[]{};
	
	private MovieDetails[] movies = new MovieDetails[]{};

	@Element(required=false)
	private Movie movie = null;

	private String path;
	
	private String parentPath = "";

	private String[] folders = new String[]{};
	
	private String[] files = new String[]{};

	public int getMovieId()
	{
		return movieId;
	}

	public void setMovieId( int movieId )
	{
		this.movieId = movieId;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected( boolean selected )
	{
		this.selected = selected;
	}

	public MoviePrefixCounts[] getPrefixes()
	{
		return prefixes;
	}

	public void setPrefixes( MoviePrefixCounts[] prefixes )
	{
		this.prefixes = prefixes;
	}

	public MovieDetails[] getMovies()
	{
		return movies;
	}

	public void setMovies( MovieDetails[] movies )
	{
		this.movies = movies;
	}

	public Movie getMovie()
	{
		return movie;
	}

	public void setMovie( Movie movie )
	{
		this.movie = movie;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath( String path )
	{
		this.path = path;
	}

	public String getParentPath()
	{
		return parentPath;
	}

	public void setParentPath( String parentPath )
	{
		this.parentPath = parentPath;
	}

	public String[] getFolders()
	{
		return folders;
	}

	public void setFolders( String[] folders )
	{
		this.folders = folders;
	}

	public String[] getFiles()
	{
		return files;
	}

	public void setFiles( String[] files )
	{
		this.files = files;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix( String prefix )
	{
		this.prefix = prefix;
	}
	
	


}
