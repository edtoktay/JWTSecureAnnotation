/**
 * 
 */
package org.edtoktay.springtools.security.jwt.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edtoktay.springtools.security.jwt.exception.TokenMissingException;
import org.edtoktay.springtools.security.jwt.model.AuthToken;
import org.edtoktay.springtools.security.jwt.model.RequestPathsMatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

/**
 * @author deniz.toktay
 *
 */

public class JwtAuthTokenFilter extends AbstractAuthenticationProcessingFilter {
	@Value(value = "${security.jjwt.token-header}")
	private String tokenHeader;
	@Value(value = "${security.jjwt.header-prefix}")
	private String headerPrefix;

	public JwtAuthTokenFilter(RequestPathsMatcher requestPathsMatcher) {
		super(requestPathsMatcher);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String header = request.getHeader(this.tokenHeader);
		if (header == null || !header.startsWith(headerPrefix)) {
			throw new TokenMissingException("No JWT token found in request headers");
		}
		String token = header.substring(headerPrefix.length());
		AuthToken authToken = new AuthToken(token);
		
		return getAuthenticationManager().authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

}
