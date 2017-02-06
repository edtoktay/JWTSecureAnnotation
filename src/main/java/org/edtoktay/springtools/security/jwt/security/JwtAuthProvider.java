/**
 * 
 */
package org.edtoktay.springtools.security.jwt.security;

import org.edtoktay.springtools.security.jwt.model.AuthToken;
import org.edtoktay.springtools.security.jwt.operations.TokenParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author deniz.toktay
 *
 */
@Component
public class JwtAuthProvider extends AbstractUserDetailsAuthenticationProvider {
	@Autowired private TokenParser tokenParser;
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		AuthToken authToken = (AuthToken) authentication;
		String token = authToken.getToken();
		UserDetails userDetails = tokenParser.parseClaims(token);
		return userDetails;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return AuthToken.class.isAssignableFrom(authentication);
	}

}
