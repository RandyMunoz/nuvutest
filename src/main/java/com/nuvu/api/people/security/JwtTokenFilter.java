package com.nuvu.api.people.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nuvu.api.people.service.UserDetailsServiceImp;

public class JwtTokenFilter extends OncePerRequestFilter {

	private static Logger _logger = LoggerFactory.getLogger(JwtTokenFilter.class);

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private UserDetailsServiceImp userDetailsServiceImp;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getToken(request);
			if (token != null && jwtProvider.validToken(token)) {
				String username = jwtProvider.getUsername(token);
				UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			_logger.error("Fallo doFilterInternal - Message:" + e.getMessage());
		}
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer")) {
			return header.replace("Bearer ", "");
		} else {
			return null;
		}
	}

}
