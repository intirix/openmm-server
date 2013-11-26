package com.intirix.openmm.server.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet that implements a filter chain, since the TJWS api does not support adding filters
 * @author jeff
 *
 */
public class FilterServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final List< Filter > filters;
	
	private final Servlet child;
	
	public FilterServlet( List< Filter > filters, Servlet child )
	{
		this.filters = filters;
		this.child = child;
	}

	@Override
	protected void service( final HttpServletRequest req, final HttpServletResponse resp ) throws ServletException, IOException
	{
		final FilterChainList chain = new FilterChainList( filters, new Filter() {
			
			public void init( FilterConfig filterConfig ) throws ServletException
			{
			}
			
			public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException
			{
				child.service( req, resp );
			}
			
			public void destroy()
			{
			}
		} );
		chain.doFilter( req, resp );
	}
	
	
	
	

}
