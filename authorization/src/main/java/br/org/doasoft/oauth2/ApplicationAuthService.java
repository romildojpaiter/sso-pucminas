package br.org.doasoft.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.org.doasoft.oauth2.config.AuthServerSecurityConfiguration;
import br.org.doasoft.oauth2.config.OAuth2ServerConfig;

@Configuration
@EnableAutoConfiguration
@Import({OAuth2ServerConfig.class, AuthServerSecurityConfiguration.class})
@EnableJpaRepositories
@ComponentScan
public class ApplicationAuthService  extends WebMvcConfigurerAdapter {
	
	public static void main(final String[] args) {
		SpringApplication.run(ApplicationAuthService.class, args);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

	
	/*@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/test");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("password");
	    return driverManagerDataSource;
	}*/
	
}
