package com.abcd.data;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ignite.internal.processors.security.SecurityUtils;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

public class ThrottlingFilter implements javax.servlet.Filter {

    private Bucket createNewBucket() {
         long overdraft = 50;
         Refill refill = Refill.greedy(10, Duration.ofSeconds(1));
         Bandwidth limit = Bandwidth.classic(overdraft, refill);
         return Bucket4j.builder().addLimit(limit).build();
    }
    
    public static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";

    public void init1(FilterConfig filterConfig) throws ServletException {}

    public void doFilter1(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletResponse response = null;
		if (((HttpServletRequest) servletRequest).getHeader(X_FORWARDED_PROTO) != null) {
    	      if (((HttpServletRequest) servletRequest).getHeader(X_FORWARDED_PROTO).indexOf("https") != 0) {
    	        String pathInfo = (((HttpServletRequest) servletRequest).getPathInfo() != null) ? ((HttpServletRequest) servletRequest).getPathInfo() : "";
    	        response.sendRedirect("https://" + servletRequest.getServerName() + pathInfo);
    	        return;
    	      }
    	    }

    	    filterChain.doFilter(servletRequest, response);
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}