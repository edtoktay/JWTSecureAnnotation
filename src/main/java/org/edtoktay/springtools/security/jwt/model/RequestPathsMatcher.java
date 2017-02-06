/**
 * 
 */
package org.edtoktay.springtools.security.jwt.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

/**
 * @author deniz.toktay
 *
 */
public class RequestPathsMatcher implements RequestMatcher {
	private OrRequestMatcher publicMatchers;
	private OrRequestMatcher privateMatchers;

	public RequestPathsMatcher(List<String> publicRequests, List<String> privateRequests) {
		Assert.notNull(publicRequests);
		List<RequestMatcher> publics = publicRequests.stream().map(url -> new AntPathRequestMatcher(url))
				.collect(Collectors.toList());
		List<RequestMatcher> privates = privateRequests.stream().map(url -> new AntPathRequestMatcher(url))
				.collect(Collectors.toList());
		this.publicMatchers = new OrRequestMatcher(publics);
		this.privateMatchers = new OrRequestMatcher(privates);
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		return publicMatchers.matches(request) ? false : privateMatchers.matches(request) ? true : false;

	}

	public OrRequestMatcher getPublicMatchers() {
		return publicMatchers;
	}

	public OrRequestMatcher getPrivateMatchers() {
		return privateMatchers;
	}

}
