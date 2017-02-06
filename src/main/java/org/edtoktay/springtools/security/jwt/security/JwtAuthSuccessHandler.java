/**
 * 
 */
package org.edtoktay.springtools.security.jwt.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edtoktay.springtools.security.jwt.annotation.impl.ScanSingleton;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author deniz.toktay
 *
 */
public class JwtAuthSuccessHandler implements AuthenticationSuccessHandler {

	ScanSingleton scanSingleton = ScanSingleton.getInstance();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	}

}
