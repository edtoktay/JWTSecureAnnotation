/**
 * 
 */
package org.edtoktay.springtools.security.jwt.operations;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.edtoktay.springtools.security.jwt.configure.JjwtProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

/**
 * @author deniz.toktay
 *
 */
@Component
public class TokenParser {
	@Autowired
	private JjwtProp jjwtProp;

	@SuppressWarnings("unchecked")
	public UserDetails parseClaims(String token) {
		Jws<Claims> claims = Jwts.parser().setSigningKey(jjwtProp.getTokenSignKey()).parseClaimsJws(token);
		return new User(claims.getBody().getSubject(), "",
				(Collection<? extends GrantedAuthority>) (claims.getBody().get("roles", List.class)).stream()
						.map(auth -> new SimpleGrantedAuthority((String) auth)).collect(Collectors.toList()));
	}
}
