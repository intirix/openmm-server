package com.intirix.openmm.server.ui.html.pages;

import org.simpleframework.xml.Default;

import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.ui.html.PageData;

@Default
public class AssignShowFilesBean extends PageData
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int showId = -1;
	
	private int seasonNumber = -1;
	
	private int epNum = -1;
	
	private boolean selected = false;
	
	private Show[] shows = new Show[]{};
	
	private Season[] seasons = new Season[]{};
	
	private Episode[] episodes = new Episode[]{};

	private String path;
	
	private String parentPath = "";

	private String[] folders = new String[]{};
	
	private String[] files = new String[]{};


	public int getShowId()
	{
		return showId;
	}

	public void setShowId( int showId )
	{
		this.showId = showId;
	}

	public int getSeasonNumber()
	{
		return seasonNumber;
	}

	public void setSeasonNumber( int seasonNumber )
	{
		this.seasonNumber = seasonNumber;
	}

	public int getEpNum()
	{
		return epNum;
	}

	public void setEpNum( int epNum )
	{
		this.epNum = epNum;
	}
	
	

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected( boolean selected )
	{
		this.selected = selected;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath( String path )
	{
		this.path = path;
	}

	public Show[] getShows()
	{
		return shows;
	}

	public void setShows( Show[] shows )
	{
		this.shows = shows;
	}

	public Season[] getSeasons()
	{
		return seasons;
	}

	public void setSeasons( Season[] seasons )
	{
		this.seasons = seasons;
	}

	public Episode[] getEpisodes()
	{
		return episodes;
	}

	public void setEpisodes( Episode[] episodes )
	{
		this.episodes = episodes;
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
	
	

}
