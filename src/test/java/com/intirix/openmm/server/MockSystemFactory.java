package com.intirix.openmm.server;

import java.sql.Connection;
import java.sql.DriverManager;

import org.easymock.EasyMock;

import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.mt.app.VFSApp;
import com.intirix.openmm.server.mt.technical.ConfigMidtier;
import com.intirix.openmm.server.vfs.FileSystemBrowser;

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
		
		// create a mock file system browser
		final FileSystemBrowser browser = EasyMock.createMock( FileSystemBrowser.class );

		runtime.getApplicationLayer().setVfsApp( new VFSApp() {
			
			public void setConfigMidtier( ConfigMidtier configMidtier )
			{
			}
			
			public FileSystemBrowser getBrowser() throws OpenMMMidtierException
			{
				return browser;
			}
		} );
		
		return runtime;
	}
}
