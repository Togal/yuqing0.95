package com.yuqing.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FilterLogin implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		String path = ((HttpServletRequest)request).getContextPath();
		String allPath = ((HttpServletRequest)request).getRequestURI();
		int index = allPath.indexOf(path);
		path = allPath.substring(index+path.length()+1).trim();
	//	System.out.println("path:"+allPath);
		
		if(!path.equals("") && !path.equals("login") && !path.equals("login.jsp")) {
			if(session.getAttribute("user") == null && 
					!path.contains("source") && !path.contains("validateCode")
					&& !path.equals("about.jsp")
					&& !path.equals("feedback.jsp")) {
				request.setAttribute("notLogin", 1);
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
