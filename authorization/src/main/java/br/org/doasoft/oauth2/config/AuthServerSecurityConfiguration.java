package br.org.doasoft.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthServerSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	/*@Autowired
	private AuthenticationManager authenticationManager;*/
	
	@Autowired
	@Qualifier("doasoftUserDetailsService")
	private UserDetailsService doasoftUserDetailsService;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(doasoftUserDetailsService);
		
		// In case of password encryption - for production site
		// auth.userDetailsService(doasoftUserDetailsService).passwordEncoder(passwordEncoder());

		// FIXME : check_token api validates client credentials via basic authorization 
		/*auth.inMemoryAuthentication()
			.withUser("user").password("userpwd").roles("USER")
			.and()
			.withUser("admin").password("adminpwd").roles("ADMIN")
			.and()
			.withUser("soncrserv").password("soncrserv").roles("CLIENT");
		*/
		
		// auth.parentAuthenticationManager(authenticationManager);
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.formLogin().loginPage("/login").permitAll()
		.and()
			.requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
		.and()
			.authorizeRequests().anyRequest().authenticated();
		// @formatter:on
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}
