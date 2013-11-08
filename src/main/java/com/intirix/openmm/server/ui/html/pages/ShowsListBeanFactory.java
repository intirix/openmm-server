package com.intirix.openmm.server.ui.html.pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.OpenMMServerRuntime;
import com.intirix.openmm.server.api.beans.Show;
import com.intirix.openmm.server.mt.OpenMMMidtierException;
import com.intirix.openmm.server.ui.html.PageBeanFactory;
import com.intirix.openmm.server.ui.html.PageData;

public class ShowsListBeanFactory implements PageBeanFactory
{
	private OpenMMServerRuntime runtime;

	public void setRuntime( OpenMMServerRuntime runtime )
	{
		this.runtime = runtime;
	}

	public PageData createPageBean( HttpServletRequest request, Object actionResult ) throws ServletException
	{
		final ShowsListBean page = new ShowsListBean();

		try
		{
			page.setShows( runtime.getTechnicalLayer().getShowMidtier().listShows().toArray( new Show[]{} ) );
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}


		return page;
	}

}
