package com.apibucket.loginapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.apibucket.loginapi.repository.UsersJpaRepository;
import com.apibucket.loginapi.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UsersJpaRepository.class)
public class LoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	private static String REALM = "MY_TEST_REALM";

	/*
	 * @Autowired private JwtAuthenticationProvider authenticationProvider;
	 */

	/*
	 * @Autowired private JwtAuthenticationEntryPoint entryPoint;
	 */

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	/*
	 * @Bean public AuthenticationManager authenticationManager() { return new
	 * ProviderManager(Collections.singletonList(authenticationProvider)); }
	 */
	/*
	 * @Bean public JwtAuthenticationTokenFilter authenticationTokenFilter() {
	 * JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
	 * filter.setAuthenticationManager(authenticationManager());
	 * filter.setAuthenticationSuccessHandler(new JwtSuccessHandler()); return
	 * filter; }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(getBCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/user/signin").authenticated().and()
				.httpBasic().realmName(REALM).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user/signup").anonymous().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	/*	http.authorizeRequests()
		.anyRequest().authenticated()
	.and()
	.formLogin()
		.loginPage("/userLogin")
		.loginProcessingUrl("/auth")
		.permitAll();*/

	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * 
	 * web.ignoring().antMatchers(HttpMethod.OPTIONS,
	 * "/**").antMatchers(HttpMethod.DELETE, "/**").antMatchers(HttpMethod.GET,
	 * "/**").antMatchers(HttpMethod.HEAD, "/**").antMatchers(HttpMethod.PATCH,
	 * "/**").antMatchers(HttpMethod.PUT, "/**").antMatchers(HttpMethod.TRACE,
	 * "/**");
	 * 
	 * // web.ignoring().antMatchers(HttpMethod.POST, "/user/signup");
	 * web.ignoring().antMatchers(HttpMethod.POST, "/useddr/signup");
	 * 
	 * }
	 */

	/*
	 * @Bean public IgnoredRequestCustomizer optionsIgnoredRequestsCustomizer() {
	 * return configurer -> { List<RequestMatcher> matchers = new ArrayList<>();
	 * matchers.add(new AntPathRequestMatcher("/**", "OPTIONS"));
	 * configurer.requestMatchers(new OrRequestMatcher(matchers)); }; }
	 */
}