/**
 * 
 */
package org.edtoktay.springtools.security.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author deniz.toktay
 *
 */
public class RoleException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoleException(String msg) {
		super(msg);
	}

}
