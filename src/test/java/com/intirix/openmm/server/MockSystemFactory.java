package com.intirix.openmm.server;

import java.sql.Connection;
import java.sql.DriverManager;

public class MockSystemFactory
{

	public static OpenMMServerRuntime createMockRuntime() throws Exception
	{
		final OpenMMServerRuntime runtime = new OpenMMServerRuntime();
		runtime.setConfig( new Configuration() );

		Class.forName("org.hsqldb.jdbc.JDBCDriver" );

		Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb" + Math.random(), "SA", "");
		final SingleConnectionDataSource ds = new SingleConnectionDataSource( new NoCloseConnection( c ) );
		runtime.setDataSource( ds );

		
		return runtime;
	}
}
