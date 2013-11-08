package com.intirix.openmm.server;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class OpenMMDataSource implements DataSource
{

	private final Configuration config;

	public OpenMMDataSource( Configuration config )
	{
		this.config = config;
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

	public void setLogWriter( PrintWriter arg0 ) throws SQLException
	{
	}

	public void setLoginTimeout( int arg0 ) throws SQLException
	{
	}

	public boolean isWrapperFor( Class< ? > arg0 ) throws SQLException
	{
		return false;
	}

	public < T > T unwrap( Class< T > arg0 ) throws SQLException
	{
		return null;
	}

	public Connection getConnection() throws SQLException
	{
		final Connection conn = DriverManager.getConnection( getConnectionPath(), "SA", "" );
		return conn;
	}

	public Connection getConnection( String username, String password ) throws SQLException
	{
		return getConnection();
	}



	/**
	 * Get the JDBC Connection path
	 * @return
	 */
	String getConnectionPath()
	{
		return "jdbc:hsqldb:file:" + config.getDatabasePath();
	}

}
