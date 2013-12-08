package com.intirix.openmm.server;

import com.intirix.openmm.server.mt.app.MovieApp;
import com.intirix.openmm.server.mt.app.RTApp;
import com.intirix.openmm.server.mt.app.ShowApp;
import com.intirix.openmm.server.mt.app.TVDBApp;
import com.intirix.openmm.server.mt.app.UserApp;
import com.intirix.openmm.server.mt.app.VFSApp;
import com.intirix.openmm.server.mt.app.WebCacheApp;

/**
 * Application layer container
 * @author jeff
 *
 */
public class ApplicationLayer
{
	private VFSApp vfsApp;

	private WebCacheApp webCacheApp;
	
	private TVDBApp tvdbApp;
	
	private RTApp rtApp;
	
	private ShowApp showApp;
	
	private MovieApp movieApp;
	
	private UserApp userApp;
	
	
	
	public VFSApp getVfsApp()
	{
		return vfsApp;
	}

	public void setVfsApp( VFSApp vfsApp )
	{
		this.vfsApp = vfsApp;
	}

	public WebCacheApp getWebCacheApp()
	{
		return webCacheApp;
	}

	public void setWebCacheApp( WebCacheApp webCacheApp )
	{
		this.webCacheApp = webCacheApp;
	}

	public TVDBApp getTvdbApp()
	{
		return tvdbApp;
	}

	public void setTvdbApp( TVDBApp tvdbApp )
	{
		this.tvdbApp = tvdbApp;
	}

	
	public RTApp getRtApp()
	{
		return rtApp;
	}

	public void setRtApp( RTApp rtApp )
	{
		this.rtApp = rtApp;
	}

	public ShowApp getShowApp()
	{
		return showApp;
	}

	public void setShowApp( ShowApp showApp )
	{
		this.showApp = showApp;
	}
	
	

	public MovieApp getMovieApp()
	{
		return movieApp;
	}

	public void setMovieApp( MovieApp movieApp )
	{
		this.movieApp = movieApp;
	}

	public UserApp getUserApp()
	{
		return userApp;
	}

	public void setUserApp( UserApp userApp )
	{
		this.userApp = userApp;
	}

	
	
}
