package com.abcd.data;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.core.reflection.AnnotatedMethod;
import com.sun.jersey.core.reflection.MethodList.Filter;

public class HttpsEnforcer implements Filter {
	  public static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";

	  public void init(FilterConfig filterConfig) throws ServletException {}

	  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	    HttpServletRequest request = (HttpServletRequest) servletRequest;
	    HttpServletResponse response = (HttpServletResponse) servletResponse;

	    if (request.getHeader(X_FORWARDED_PROTO) != null) {
	      if (request.getHeader(X_FORWARDED_PROTO).indexOf("https") != 0) {
	        String pathInfo = (request.getPathInfo() != null) ? request.getPathInfo() : "";
	        response.sendRedirect("https://" + request.getServerName() + pathInfo);
	        return;
	      }
	    }

	    filterChain.doFilter(request, response);
	  }

	  public void destroy() { }

	@Override
	public boolean keep(AnnotatedMethod m) {
		// TODO Auto-generated method stub
		return false;
	}
	}