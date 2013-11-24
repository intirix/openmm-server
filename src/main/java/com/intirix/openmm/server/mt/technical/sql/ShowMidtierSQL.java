package com.intirix.openmm.server.mt.technical.sql;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intirix.openmm.server.api.beans.Episode;
import com.intirix.openmm.server.api.beans.MediaLink;
import com.intirix.openmm.server.api.beans.Season;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.ShowMidtier;
import com.intirix.openmm.server.mt.technical.beans.EpisodeLinkCounts;
import com.intirix.openmm.server.mt.technical.beans.EpisodeLinkCountsObjectFactory;
import com.intirix.openmm.server.mt.technical.beans.EpisodeObjectFactory;
import com.intirix.openmm.server.mt.technical.beans.IntegerObjectFactory;
import com.intirix.openmm.server.mt.technical.beans.MediaLinkObjectFactory;
import com.intirix.openmm.server.mt.technical.beans.SeasonEpisodeCounts;
import com.intirix.openmm.server.mt.technical.beans.SeasonEpisodeCountsObjectFactory;
import com.intirix.openmm.server.mt.technical.beans.SeasonObjectFactory;
import com.intirix.openmm.server.mt.technical.beans.ShowObjectFactory;

public class ShowMidtierSQL implements ShowMidtier
{
	private final Log log = LogFactory.getLog( ShowMidtierSQL.class );

	private final SQLHelper sqlHelper;

	public ShowMidtierSQL( DataSource ds )
	{
		sqlHelper = new SQLHelper( ShowMidtierSQL.class, ds );
	}


	public int addShow( Show show ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "show_add.sql", show.getName(), show.getDisplayName(), show.getTvdbId(),
					show.getTvdbLang(), show.getImdbId(), show.getZap2itId(), ( show.getActive() ? "Y" : "N" ), show.getContentRating(), show.getBannerPath(),
					show.getDescription() );
			return sqlHelper.executeQuerySingleRow( new IntegerObjectFactory(), "show_get_id_by_name.sql", show.getName() );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public List< Show > listShows() throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuery( new ShowObjectFactory(), "shows_list.sql" );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}
	
	

	public Show getShow( int id ) throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuery( new ShowObjectFactory(), "shows_get_by_id.sql", id ).get( 0 );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}


	public void updateShow( Show oldBean, Show newBean ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "show_update.sql", newBean.getName(), newBean.getDisplayName(), newBean.getTvdbId(),
					newBean.getTvdbLang(), newBean.getImdbId(), newBean.getZap2itId(), ( newBean.getActive() ? "Y" : "N" ),
					newBean.getContentRating(), newBean.getBannerPath(),
					newBean.getDescription(), oldBean.getId() );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public int addSeason( Season season ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "show_season_add.sql", season.getShowId(), season.getName(), season.getNumber() );
			return sqlHelper.executeQuerySingleRow( new IntegerObjectFactory(), "show_season_get_id.sql", season.getShowId(), season.getName() );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public List< Season > listSeasons( int showId ) throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuery( new SeasonObjectFactory(), "show_season_list.sql", showId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}
	
	
	public List< SeasonEpisodeCounts > listSeasonEpisodeCounts( int showId ) throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuery( new SeasonEpisodeCountsObjectFactory(), "show_season_list_available.sql", showId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}


	public Season getSeason( int seasonId ) throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuerySingleRow( new SeasonObjectFactory(), "show_season_get_by_id.sql", seasonId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public void updateSeason( Season oldBean, Season newBean ) throws OpenMMMidtierException
	{
		try
		{
		sqlHelper.executeUpdate( "show_season_update.sql", newBean.getShowId(), newBean.getName(), newBean.getNumber(), oldBean.getId() );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}


	public int addEpisode( Episode episode ) throws OpenMMMidtierException
	{
		try
		{
			final Object lastWatched = calcLastWatched( episode );
			sqlHelper.executeUpdate( "show_episode_add.sql", episode.getSeasonId(), episode.getTvdbId(), episode.getName(),
					episode.getEpNum(), episode.getDvdNum(), episode.getScreenshotPath(), episode.getDescription(),
					episode.getGuests(), episode.getAirDate(), episode.getRating(), lastWatched, episode.getWatchCount() );
			return sqlHelper.executeQuerySingleRow( new IntegerObjectFactory(), "show_episode_find_id.sql", episode.getSeasonId(),
					episode.getName(), episode.getEpNum() );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}

	public List< Episode > listEpisodes( int seasonId ) throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuery( new EpisodeObjectFactory(), "show_episodes_list.sql", seasonId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}
	

	public List< EpisodeLinkCounts > listEpisodeLinkCounts( int seasonId ) throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuery( new EpisodeLinkCountsObjectFactory(), "show_episodes_list_available.sql", seasonId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}


	public Episode getEpisode( int epid ) throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuerySingleRow( new EpisodeObjectFactory(), "show_get_episode_by_id.sql", epid );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}


	public void updateEpisode( Episode oldBean, Episode newBean ) throws OpenMMMidtierException
	{
		try
		{
			final Object lastWatched = calcLastWatched( newBean );
			
			sqlHelper.executeUpdate( "show_episode_update.sql", newBean.getSeasonId(), newBean.getTvdbId(), newBean.getName(),
					newBean.getEpNum(), newBean.getDvdNum(), newBean.getScreenshotPath(), newBean.getDescription(),
					newBean.getGuests(), newBean.getAirDate(), newBean.getRating(), lastWatched,
					newBean.getWatchCount(), oldBean.getId() );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}


	private Object calcLastWatched( Episode episode ) throws ParseException
	{
		Object lastWatched = SQLNull.TimestampNull;
		if ( episode.getLastWatched().length() > 0 )
		{
			final SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd" );
			final Date date = sdf.parse( episode.getLastWatched() );
			final Timestamp ts = new Timestamp( date.getTime() );
			lastWatched = ts;
		}
		return lastWatched;
	}

	public void watchEpisode( int episodeId ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "show_episodes_watch.sql", new Timestamp( System.currentTimeMillis() ), episodeId );
		}
		catch ( Exception e )
		{
			log.info( "Failed to update episode with viewer data", e );
		}
	}


	public void assignFile( int episodeId, String file, long size ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "show_assign_file.sql", episodeId, "vfs://" + file, size );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( "Failed to assign file", e );
		}
	}


	public void unassignFile( int linkId ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "unassign_file.sql", linkId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}


	public List< MediaLink > getEpisodeLinks( int episodeId ) throws OpenMMMidtierException
	{
		try
		{
			return sqlHelper.executeQuery( new MediaLinkObjectFactory(), "show_episode_list_links.sql", episodeId );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( e );
		}
	}
	
	

}
