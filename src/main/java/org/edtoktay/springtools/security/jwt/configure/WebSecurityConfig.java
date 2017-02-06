/**
 * 
 */
package org.edtoktay.springtools.security.jwt.configure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.edtoktay.springtools.security.jwt.annotation.impl.JwtSecuredClasses;
import org.edtoktay.springtools.security.jwt.annotation.impl.JwtSecuredScanner;
import org.edtoktay.springtools.security.jwt.annotation.impl.ScanSingleton;
import org.edtoktay.springtools.security.jwt.model.RequestPathsMatcher;
import org.edtoktay.springtools.security.jwt.security.JwtAuthEntryPoint;
import org.edtoktay.springtools.security.jwt.security.JwtAuthProvider;
import org.edtoktay.springtools.security.jwt.security.JwtAuthSuccessHandler;
import org.edtoktay.springtools.security.jwt.security.JwtAuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author deniz.toktay
 *
 */
@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;
	@Autowired
	private JwtAuthProvider jwtAuthProvider;
	@SuppressWarnings("unused")
	@Autowired
	private JwtSecuredScanner jwtSecuredScanner;

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {

		return new ProviderManager(Arrays.asList(jwtAuthProvider));
	}

	@Bean
	public JwtAuthTokenFilter authenticationTokenFilterBean() throws Exception {
		ScanSingleton scanSingleton = ScanSingleton.getInstance();
		List<String> privateRequests = new ArrayList<String>();
		for (JwtSecuredClasses jsc : scanSingleton.getPrivateObj())
			privateRequests.add(jsc.getPath());
		RequestPathsMatcher rpm = new RequestPathsMatcher(scanSingleton.getPublicPaths(), privateRequests);
		JwtAuthTokenFilter authTokenFilter = new JwtAuthTokenFilter(rpm);
		authTokenFilter.setAuthenticationManager(authenticationManager());
		authTokenFilter.setAuthenticationSuccessHandler(new JwtAuthSuccessHandler());
		return authTokenFilter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(jwtAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ScanSingleton scanSingleton = ScanSingleton.getInstance();
		//@formatter:off
		http
			.csrf().disable()
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthEntryPoint)
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		for (String path : scanSingleton.getPublicPaths())
			http
				.authorizeRequests()
					.antMatchers(path)
						.permitAll();
		for (JwtSecuredClasses jsc : scanSingleton.getPrivateObj()){
			if (jsc.getRoles().length > 0 && StringUtils.isNotBlank(jsc.getRoles()[0])){
				http
					.authorizeRequests()
						.antMatchers(jsc.getPath())
						.access(scanSingleton.getAccessString(jsc.getPath()));
			} else {
				http
					.authorizeRequests()
						.antMatchers(jsc.getPath())
						.authenticated();
			}
		}
		http
			.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
			.headers()
				.cacheControl();
		//@formatter:on
	}
}
