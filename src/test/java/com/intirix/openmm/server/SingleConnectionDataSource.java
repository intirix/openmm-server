package com.intirix.openmm.server;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class SingleConnectionDataSource implements DataSource
{
	
	private final Connection conn;
	
	public SingleConnectionDataSource( Connection conn )
	{
		this.conn = conn;
	}

	public PrintWriter getLogWriter() throws SQLException
	{
		return null;
	}

	public int getLoginTimeout() throws SQLException
	{
		return 0;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException
	{
		return null;
	}

	public void setLogWriter( PrintWriter out ) throws SQLException
	{
	}

	public void setLoginTimeout( int seconds ) throws SQLException
	{
	}

	public boolean isWrapperFor( Class< ? > iface ) throws SQLException
	{
		return false;
	}

	public < T > T unwrap( Class< T > iface ) throws SQLException
	{
		return null;
	}

	public Connection getConnection() throws SQLException
	{
		return conn;
	}

	public Connection getConnection( String username, String password ) throws SQLException
	{
		return conn;
	}

}
