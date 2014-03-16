package com.intirix.openmm.server.api.get;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.intirix.openmm.server.api.GetQuery;
import com.intirix.openmm.server.api.SearchResponse;
import com.intirix.openmm.server.api.beans.SearchResult;
import com.intirix.openmm.server.mt.OpenMMMidtierException;

public class SearchQuery extends GetQuery
{

	@Override
	public Object executeQuery( String param, HttpServletRequest req ) throws ServletException
	{
		final SearchResponse resp = new SearchResponse();
		try
		{
			resp.setResults( getRuntime().getApplicationLayer().getSearchApp().search( req.getParameter( "query" ) ).toArray( new SearchResult[]{} ) );
		}
		catch ( OpenMMMidtierException e )
		{
			throw new ServletException( e );
		}
		return resp;
	}

}
