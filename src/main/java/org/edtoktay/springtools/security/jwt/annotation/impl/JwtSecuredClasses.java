/**
 * 
 */
package org.edtoktay.springtools.security.jwt.annotation.impl;

/**
 * @author deniz.toktay
 *
 */
public class JwtSecuredClasses {
	private String path;
	private String[] roles;
	public JwtSecuredClasses(String path, String[] roles) {
		this.path = path;
		this.roles = roles;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
}
