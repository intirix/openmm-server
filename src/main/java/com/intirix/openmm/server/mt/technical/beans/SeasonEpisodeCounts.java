package com.intirix.openmm.server.mt.technical.beans;


public class SeasonEpisodeCounts
{
	private int seasonId;

	private int numEpisodes = 0;
	
	private int numEpisodesAvailable = 0;
	
	

	public int getSeasonId()
	{
		return seasonId;
	}

	public void setSeasonId( int seasonId )
	{
		this.seasonId = seasonId;
	}

	public int getNumEpisodes()
	{
		return numEpisodes;
	}

	public void setNumEpisodes( int numEpisodes )
	{
		this.numEpisodes = numEpisodes;
	}

	public int getNumEpisodesAvailable()
	{
		return numEpisodesAvailable;
	}

	public void setNumEpisodesAvailable( int numEpisodesAvailable )
	{
		this.numEpisodesAvailable = numEpisodesAvailable;
	}
	

}
