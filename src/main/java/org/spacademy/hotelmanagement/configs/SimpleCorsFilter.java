package org.spacademy.hotelmanagement.configs;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

	@Override
    public void init(FilterConfig filterConfig) throws jakarta.servlet.ServletException {
		Filter.super.init(filterConfig);
    }

	@Override
	public void doFilter(jakarta.servlet.ServletRequest servletRequest, jakarta.servlet.ServletResponse servletResponse,
			jakarta.servlet.FilterChain filterChain) throws IOException, jakarta.servlet.ServletException {

		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String originHeader = request.getHeader("origin");
		
			response.setHeader("Access-Control-Allow-Origin", originHeader);
	        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers", "*");

	        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	        	response.setStatus(HttpServletResponse.SC_OK);
	        } else {
	        	filterChain.doFilter(servletRequest, servletResponse);
	        }
	}
	
	 @Override
	 public void destroy() {
		 Filter.super.destroy();
	 }



	

}
