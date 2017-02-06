/**
 * 
 */
package org.edtoktay.springtools.security.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author deniz.toktay
 *
 */
public class AuthortyException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthortyException(String msg) {
		super(msg);
	}

}
