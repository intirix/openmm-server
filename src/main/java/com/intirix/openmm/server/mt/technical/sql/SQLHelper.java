package com.intirix.openmm.server.mt.technical.sql;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.intirix.openmm.server.mt.technical.sql.SQLNull.NullObject;

/**
 * Helper class for executing sql
 * @author jeff
 *
 */
public class SQLHelper
{

	private final Logger log;

	private final Class< ? > klass;

	private final DataSource ds;

	public static interface ObjectFactory< T >
	{
		public T createObject( ResultSet rs ) throws SQLException;
	}

	public SQLHelper( Class< ? > klass, DataSource ds )
	{
		this.klass = klass;
		log = Logger.getLogger( klass );
		this.ds = ds;
	}

	public < T > List< T > executeQuery( ObjectFactory< T > factory, String path, Object...args ) throws SQLException, IOException
	{
		final long t1 = System.currentTimeMillis();
		final List< T > ret = new ArrayList< T >( 10 );

		final String sql = getSql( path );
		final Connection conn = ds.getConnection();
		try
		{
			final PreparedStatement ps = conn.prepareStatement( sql );
			try
			{
				if ( args != null )
				{
					for ( int i = 0; i < args.length; i++ )
					{
						ps.setObject( i + 1, args[ i ] );
					}
				}

				final ResultSet rs = ps.executeQuery();
				try
				{
					while ( rs.next() )
					{
						final T obj = factory.createObject( rs );
						ret.add( obj );
					}
				}
				finally
				{
					rs.close();
				}
			}
			finally
			{
				ps.close();
			}
		}
		finally
		{
			conn.close();
			final long t2 = System.currentTimeMillis();
			final long dt = t2 - t1;
			log.debug( "Executed query [" + path + "] in " + dt + "ms" );
		}
		

		return ret;
	}

	/**
	 * Execute a query that returns two columns and returns a Properties table of the name-value pairs
	 * @param path
	 * @param args
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public Properties executeQueryLookupTable( String path, Object...args ) throws SQLException, IOException
	{
		final long t1 = System.currentTimeMillis();
		final Properties props = new Properties();

		final String sql = getSql( path );
		final Connection conn = ds.getConnection();
		try
		{
			final PreparedStatement ps = conn.prepareStatement( sql );
			try
			{
				if ( args != null )
				{
					for ( int i = 0; i < args.length; i++ )
					{
						ps.setObject( i + 1, args[ i ] );
					}
				}

				final ResultSet rs = ps.executeQuery();
				try
				{
					while ( rs.next() )
					{
						props.setProperty( rs.getString( 1 ), rs.getString( 2 ) );
					}
				}
				finally
				{
					rs.close();
				}
			}
			finally
			{
				ps.close();
			}
		}
		finally
		{
			conn.close();
			final long t2 = System.currentTimeMillis();
			final long dt = t2 - t1;
			log.debug( "Executed query [" + path + "] in " + dt + "ms" );
		}
		

		return props;
	}

	/**
	 * Get a single row from the database
	 * @param factory
	 * @param path
	 * @param args
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public < T > T executeQuerySingleRow( ObjectFactory< T > factory, String path, Object...args ) throws SQLException, IOException
	{
		final List< T > list = executeQuery( factory, path, args );
		if ( list.size() > 0 )
		{
			return list.get( 0 );
		}
		throw new SQLException( "No results returned" );
	}

	/**
	 * Get a single row from the database
	 * @param factory
	 * @param path
	 * @param args
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public < T > T executeQueryZeroOrOneRow( ObjectFactory< T > factory, String path, Object...args ) throws SQLException, IOException
	{
		final List< T > list = executeQuery( factory, path, args );
		if ( list.size() > 0 )
		{
			return list.get( 0 );
		}
		return null;
	}
	
	public void executeUpdate( String path, Object...args ) throws SQLException, IOException
	{
		final long t1 = System.currentTimeMillis();
		final String sql = getSql( path );
		final Connection conn = ds.getConnection();
		try
		{
			final PreparedStatement ps = conn.prepareStatement( sql );
			try
			{
				if ( args != null )
				{
					for ( int i = 0; i < args.length; i++ )
					{
						if ( args[ i ] == null )
						{
							ps.setNull( i + 1, Types.TIMESTAMP );
						}
						else if ( args[ i ] instanceof NullObject )
						{
							final NullObject no = (NullObject)args[ i ];
							ps.setNull( i + 1, no.getType() );
						}
						else
						{
							ps.setObject( i + 1, args[ i ] );
						}
					}
				}
				ps.executeUpdate();
			}
			finally
			{
				ps.close();
			}
		}
		finally
		{
			conn.close();
			final long t2 = System.currentTimeMillis();
			final long dt = t2 - t1;
			log.debug( "Executed update [" + path + "] in " + dt + "ms" );
		}
	}

	/**
	 * Get the sql for the path
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public String getSql( String path ) throws IOException
	{
		final ByteArrayOutputStream buffer = new ByteArrayOutputStream( 1024 );
		final InputStream is = klass.getResourceAsStream( path );
		if ( is == null )
		{
			throw new FileNotFoundException( path );
		}
		IOUtils.copy( is, buffer );
		final String sql = buffer.toString();
		return sql;
	}
}
