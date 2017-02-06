/**
 * 
 */
package org.edtoktay.springtools.security.jwt.operations;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.edtoktay.springtools.security.jwt.configure.JjwtProp;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author deniz.toktay
 *
 */
@Component
public class TokenCreator {
	@Autowired
	private JjwtProp jjwtProp;

	public String createToken(UserDetails context) {
		if (StringUtils.isBlank(context.getUsername()))
			throw new IllegalArgumentException("Cannot create JWT Token without username");

		if (context.getAuthorities() == null || context.getAuthorities().isEmpty())
			throw new IllegalArgumentException("User doesn't have any privileges");
		Claims claims = Jwts.claims().setSubject(context.getUsername());
		claims.put("roles",
				context.getAuthorities().stream().map(role -> role.toString()).collect(Collectors.toList()));
		DateTime now = new DateTime();
		String token = Jwts.builder().setClaims(claims).setIssuer(jjwtProp.getTokenIssuer()).setIssuedAt(now.toDate())
				.setExpiration(now.plusMinutes(jjwtProp.getTokenExpirationTime()).toDate())
				.signWith(SignatureAlgorithm.HS512, jjwtProp.getTokenSignKey()).compact();
		return token;
	}
}
