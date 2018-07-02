package com.dayLeasing.configuration.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.core.Authentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

// TODO: Auto-generated Javadoc

/**
 * The Class JWTAuthenticationFilter.
 *
 * @author Balaram
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		System.out.println("CORSFilter HTTP Request: " + request.getMethod());

		// Authorize (allow) all domains to consume the content
		((HttpServletResponse) res).addHeader("Access-Control-Allow-Origin",
				"*");
		((HttpServletResponse) res).addHeader("Access-Control-Allow-Methods",
				"GET, OPTIONS, HEAD, PUT, POST,DELETE");
		((HttpServletResponse) res).addHeader("Access-Control-Allow-Headers",
				request.getHeader("Access-Control-Request-Headers"));

		HttpServletResponse resp = (HttpServletResponse) res;

		// For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per
		// CORS handshake
		if (request.getMethod().equals("OPTIONS")) {

			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}
		// Authentication authentication2 =
		// SecurityContextHolder.getContext().getAuthentication();
		else {
			Authentication authentication = TokenAuthenticationService
					.getAuthentication((HttpServletRequest) request);

			SecurityContextHolder.getContext()
					.setAuthentication(authentication);

		}
		filterChain.doFilter(req, res);
	}
	
}