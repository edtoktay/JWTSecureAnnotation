/**
 * 
 */
package org.edtoktay.springtools.security.jwt.annotation.impl;

import java.util.List;

/**
 * @author deniz.toktay
 *
 */
public class ScanObject {
	private List<String> publicMethods;
	private List<JwtSecuredClasses> privateMethods;
	public ScanObject(List<String> publicMethods, List<JwtSecuredClasses> privateMethods) {
		super();
		this.publicMethods = publicMethods;
		this.privateMethods = privateMethods;
	}
	public List<String> getPublicMethods() {
		return publicMethods;
	}
	
	public List<JwtSecuredClasses> getPrivateMethods() {
		return privateMethods;
	}
}
