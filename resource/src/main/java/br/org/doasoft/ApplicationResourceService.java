package br.org.doasoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.org.doasoft.config.MethodSecurityConfig;
import br.org.doasoft.config.OAuth2ResourceConfig;

@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, /*securedEnabled = true, */proxyTargetClass = true)
@Import({ OAuth2ResourceConfig.class, MethodSecurityConfig.class })
@ComponentScan
public class ApplicationResourceService extends WebMvcConfigurerAdapter {
	
	public static void main(final String[] args) {	
		SpringApplication.run(ApplicationResourceService.class, args);
	}
	
}
