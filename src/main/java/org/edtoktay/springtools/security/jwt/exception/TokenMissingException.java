/**
 * 
 */
package org.edtoktay.springtools.security.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author deniz.toktay
 *
 */
public class TokenMissingException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TokenMissingException(String msg) {
		super(msg);
	}
}
