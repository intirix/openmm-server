package com.intirix.openmm.server.mt.technical.impl;

import java.util.List;

import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ShowMidtier;

public class ShowMidtierDecorator implements ShowMidtier
{
	
	private final ShowMidtier child;

	public ShowMidtierDecorator( ShowMidtier child )
	{
		this.child = child;
	}

	public int addShow( Show show ) throws OpenMMMidtierException
	{
		return child.addShow( show );
	}

	public List< Show > listShows() throws OpenMMMidtierException
	{
		return child.listShows();
	}

	public Show getShow( int id ) throws OpenMMMidtierException
	{
		return child.getShow( id );
	}

	public void updateShow( Show oldBean, Show newBean ) throws OpenMMMidtierException
	{
		child.updateShow( oldBean, newBean );
	}

	public int addSeason( Season season ) throws OpenMMMidtierException
	{
		return child.addSeason( season );
	}

	public List< Season > listSeasons( int showId ) throws OpenMMMidtierException
	{
		return child.listSeasons( showId );
	}

	public Season getSeason( int seasonId ) throws OpenMMMidtierException
	{
		return child.getSeason( seasonId );
	}

	public void updateSeason( Season oldBean, Season newBean ) throws OpenMMMidtierException
	{
		child.updateSeason( oldBean, newBean );
	}

	public int addEpisode( Episode episode ) throws OpenMMMidtierException
	{
		return child.addEpisode( episode );
	}

	public List< Episode > listEpisodes( int seasonId ) throws OpenMMMidtierException
	{
		return child.listEpisodes( seasonId );
	}	

	public Episode getEpisode( int epid ) throws OpenMMMidtierException
	{
		return child.getEpisode( epid );
	}

	public void updateEpisode( Episode oldBean, Episode newBean ) throws OpenMMMidtierException
	{
		child.updateEpisode( oldBean, newBean );
	}

	public void watchEpisode( int episodeId ) throws OpenMMMidtierException
	{
		child.watchEpisode( episodeId );
	}

	public void assignFile( int episodeId, String file, long size ) throws OpenMMMidtierException
	{
		child.assignFile( episodeId, file, size );
	}
	
	

}
