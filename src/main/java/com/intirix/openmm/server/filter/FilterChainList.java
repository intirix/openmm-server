package com.intirix.openmm.server.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterChainList implements FilterChain
{
	
	private final Iterator< Filter > filterIt;
	
	private final Filter servlet;
	
	public FilterChainList( List< Filter > filters, Filter servlet )
	{
		this.filterIt = filters.iterator();
		this.servlet = servlet;
	}

	public void doFilter( ServletRequest request, ServletResponse response ) throws IOException, ServletException
	{
		if ( filterIt.hasNext() )
		{
			filterIt.next().doFilter( request, response, this );
		}
		else
		{
			servlet.doFilter( request, response, null );
		}
	}

}
