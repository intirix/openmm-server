package com.intirix.openmm.server.vfs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.intirix.openmm.server.mt.OpenMMMidtierException;

public class WebCacheFileServlet extends FileServlet
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private final Log log = LogFactory.getLog( WebCacheFileServlet.class );


	@Override
	protected String preprocessRequestedFile( FileSystemBrowser browser, String requestedFile ) throws OpenMMMidtierException, IOException
	{

		final String hash = requestedFile.replaceFirst( "\\..*", "" ).replaceFirst( ".*/", "" );
		if ( browser.exists( '/' + hash ) )
		{
			log.debug( "Serving cached file " + hash );
		}
		else
		{
			// if it doesn't exist, we must download it
			final String url = getRuntime().getTechnicalLayer().getWebCacheMidtier().getUrlForHash( hash );


			final HttpClient client = new DefaultHttpClient();
			HttpGet httpGet;
			try
			{
				log.info( "Caching " + url + " to " + hash );
				httpGet = new HttpGet( new URL( url ).toURI() );
			}
			catch ( URISyntaxException e )
			{
				throw new IOException( e );
			}
			final HttpResponse resp = client.execute( httpGet );

			// write the file to the cache directory using the hash name
			browser.writeFile( '/' + hash, resp.getEntity().getContent() );



		}
		return requestedFile.replaceFirst( "\\..*", "" );
	}



	protected FileSystemBrowser getFileSystemBrowser() throws OpenMMMidtierException
	{
		final FileSystemBrowser browser = getRuntime().getWebCacheBrowser();
		return browser;
	}
}
