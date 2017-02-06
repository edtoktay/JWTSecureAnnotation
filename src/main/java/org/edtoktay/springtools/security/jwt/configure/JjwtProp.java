/**
 * 
 */
package org.edtoktay.springtools.security.jwt.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author deniz.toktay
 *
 */
@ConfigurationProperties("security.jjwt")
public class JjwtProp {
	private int tokenExpirationTime;
	private int refreshTokenExpTime;
	private String tokenIssuer;
	private String tokenSignKey;
	private String tokenHeader;
	private String headerPrefix;
	private String authenticationEntryPoint;
	public int getTokenExpirationTime() {
		return tokenExpirationTime;
	}
	public void setTokenExpirationTime(int tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}
	public int getRefreshTokenExpTime() {
		return refreshTokenExpTime;
	}
	public void setRefreshTokenExpTime(int refreshTokenExpTime) {
		this.refreshTokenExpTime = refreshTokenExpTime;
	}
	public String getTokenIssuer() {
		return tokenIssuer;
	}
	public void setTokenIssuer(String tokenIssuer) {
		this.tokenIssuer = tokenIssuer;
	}
	public String getTokenSignKey() {
		return tokenSignKey;
	}
	public void setTokenSignKey(String tokenSignKey) {
		this.tokenSignKey = tokenSignKey;
	}
	public String getTokenHeader() {
		return tokenHeader;
	}
	public void setTokenHeader(String tokenHeader) {
		this.tokenHeader = tokenHeader;
	}
	public String getHeaderPrefix() {
		return headerPrefix;
	}
	public void setHeaderPrefix(String headerPrefix) {
		this.headerPrefix = headerPrefix;
	}
	public String getAuthenticationEntryPoint() {
		return authenticationEntryPoint;
	}
	public void setAuthenticationEntryPoint(String authenticationEntryPoint) {
		this.authenticationEntryPoint = authenticationEntryPoint;
	}
}
