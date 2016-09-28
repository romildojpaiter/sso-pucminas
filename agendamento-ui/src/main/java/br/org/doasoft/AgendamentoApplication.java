package br.org.doasoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@RestController
@EnableZuulProxy
@EnableOAuth2Sso
@EnableOAuth2Client
@ComponentScan
public class AgendamentoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AgendamentoApplication.class, args);
	}
	
}
