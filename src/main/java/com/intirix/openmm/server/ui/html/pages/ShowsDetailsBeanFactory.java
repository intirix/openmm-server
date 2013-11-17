package com.intirix.openmm.server.ui.html.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.SeasonDetails;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.app.ShowApp;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class ShowsDetailsBeanFactory implements PageBeanFactory
{
	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final ShowsDetailsBean page = new ShowsDetailsBean();

		try
		{
			final int showId = Integer.parseInt( request.getParameter( "id" ) );
			final ShowApp showApp = runtime.getApplicationLayer().getShowApp();
			final Show show = showApp.getShow( showId );
			page.setShow( show );
			page.setSeasons( showApp.listSeasonDetails( showId ).toArray( new SeasonDetails[]{} ) );
		}
		catch ( Exception e )
		{
			throw new ServletException( e );
		}


		return page;
	}

}
