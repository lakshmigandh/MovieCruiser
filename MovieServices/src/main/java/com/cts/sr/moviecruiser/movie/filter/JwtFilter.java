package com.cts.sr.moviecruiser.movie.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;

import com.cts.sr.moviecruiser.movie.utils.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtFilter extends GenericFilterBean implements Constants {

	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader(AUTH_HEADER);
		if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} else {
			if (authHeader == null || !authHeader.startsWith(AUTH_BEARER)){
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Access");
				return;
			}
			final String token = authHeader.substring(7);
			final Claims claims;
			try{
				claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
			}
			catch(ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex){
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Access");
				return;
			}
			request.setAttribute(JWT_CLAIMS, claims);
			chain.doFilter(req, res);
		}
	}
}