/**
 * 
 */
package org.edtoktay.springtools.security.jwt.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author deniz.toktay
 *
 */
public class AuthToken extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String token;

	public AuthToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public AuthToken(String token) {
		super(null, null);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}
}
