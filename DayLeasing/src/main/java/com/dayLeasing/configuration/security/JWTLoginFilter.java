package com.dayLeasing.configuration.security;

import com.dayLeasing.dao.DayLeasingUserDao;
import com.dayLeasing.dao.model.DayleasingUsers;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

// TODO: Auto-generated Javadoc

/**
 * The Class JWTLoginFilter.
 *
 * @author Balaram
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	static final String ORIGIN = "Origin";

	/**
	 * Instantiates a new JWT login filter.
	 *
	 * @param url
	 *            the url
	 * @param authManager
	 *            the auth manager
	 */
	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.authentication.
	 * AbstractAuthenticationProcessingFilter#
	 * attemptAuthentication(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException,
			IOException, ServletException {

		ServletInputStream inputStream = req.getInputStream();
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
			return null;
		}

		// pass the request along the filter chain

		Enumeration<String> headerNames = req.getHeaderNames();
		String string = inputStream.toString();
		Map<String, String[]> parameter = req.getParameterMap();
		AccountCredentials creds;
		try {
			creds = new ObjectMapper().readValue(req.getInputStream(),
					AccountCredentials.class);

			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getUsername(), creds.getPassword(),
							Collections.emptyList()));
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.authentication.
	 * AbstractAuthenticationProcessingFilter#
	 * successfulAuthentication(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain, Authentication auth)
			throws IOException, ServletException {

		TokenAuthenticationService.addAuthentication(res, auth.getName(),
				auth.getCredentials(), auth.getAuthorities());
	}
}