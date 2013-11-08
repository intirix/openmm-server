package com.intirix.openmm.server.mt.technical.sql;

import java.sql.Timestamp;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.technical.WebCacheMidtier;
import com.intirix.openmm.server.mt.technical.beans.StringObjectFactory;

/**
 * Store webcache url/hash map in the database
 * @author jeff
 *
 */
public class WebCacheMidtierSQL implements WebCacheMidtier
{

	private final SQLHelper sqlHelper;

	public WebCacheMidtierSQL( DataSource ds )
	{
		sqlHelper = new SQLHelper( getClass(), ds );
	}

	public String registerCachableUrl( String url ) throws OpenMMMidtierException
	{
		try
		{
			final String ret = sqlHelper.executeQueryZeroOrOneRow( new StringObjectFactory(), "webcache_get_hash.sql", url );
			if ( ret == null )
			{
				sqlHelper.executeUpdate( "webcache_add.sql", url, DigestUtils.md5Hex( url ), new Timestamp( System.currentTimeMillis() ) );
				return sqlHelper.executeQuerySingleRow( new StringObjectFactory(), "webcache_get_hash.sql", url );
			}
			return ret;
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( "Failed to register cachable url", e );
		}
	}

	public String getUrlForHash( String hash ) throws OpenMMMidtierException
	{
		try
		{
			sqlHelper.executeUpdate( "webcache_hit.sql", new Timestamp( System.currentTimeMillis() ), hash );
			return sqlHelper.executeQuerySingleRow( new StringObjectFactory(), "webcache_get_url.sql", hash );
		}
		catch ( Exception e )
		{
			throw new OpenMMMidtierException( "Failed to get cachable url", e );
		}
	}

}
