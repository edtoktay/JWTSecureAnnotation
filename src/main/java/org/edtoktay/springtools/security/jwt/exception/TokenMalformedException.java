/**
 * 
 */
package org.edtoktay.springtools.security.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author deniz.toktay
 *
 */
public class TokenMalformedException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenMalformedException(String msg) {
		super(msg);
	}

}
