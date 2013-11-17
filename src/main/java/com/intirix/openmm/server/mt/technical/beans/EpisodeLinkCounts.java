package com.intirix.openmm.server.mt.technical.beans;


public class EpisodeLinkCounts
{
	
	private int episodeId;
	
	private int numInternalLinks = 0;
	
	private int numExternalLinks = 0;
	
	

	public int getEpisodeId()
	{
		return episodeId;
	}

	public void setEpisodeId( int episodeId )
	{
		this.episodeId = episodeId;
	}

	public int getNumInternalLinks()
	{
		return numInternalLinks;
	}

	public void setNumInternalLinks( int numInternalLinks )
	{
		this.numInternalLinks = numInternalLinks;
	}

	public int getNumExternalLinks()
	{
		return numExternalLinks;
	}

	public void setNumExternalLinks( int numExternalLinks )
	{
		this.numExternalLinks = numExternalLinks;
	}



}
