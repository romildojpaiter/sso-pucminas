package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(-20)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("password").roles("USER")
			.and()
			.withUser("john").password("123").roles("USER")
			.and()
			.withUser("tom").password("111").roles("ADMIN");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

   /* @Override
    protected void configure(final HttpSecurity http) throws Exception {
        
		http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and().formLogin()
				.permitAll();
		
    }*/
    
    
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .formLogin().loginPage("/login").permitAll()
	        .and()
		        .requestMatchers()
		        .antMatchers("/", "/login", "/oauth/authorize", "/oauth/confirm_access")
		        .and()
		        .authorizeRequests()
		        .anyRequest().authenticated();
        
    }
	
}