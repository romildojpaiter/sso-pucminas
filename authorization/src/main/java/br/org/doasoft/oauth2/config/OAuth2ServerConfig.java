package br.org.doasoft.oauth2.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception 
	{
		/*clients
			.inMemory()
				.withClient("soncui")
				.secret("soncui")
			.authorizedGrantTypes("authorization_code", "refresh_token")
			.scopes(new String[] { "read", "write" });
		*/
		
		clients
			.inMemory()
				.withClient("soncui")
				.secret("soncui")
			.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
			.scopes("read", "write")
			.accessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(1))
			.autoApprove(true);

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception 
	{
		oauthServer
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception 
	{
		endpoints
			.authenticationManager(authenticationManager)
			.accessTokenConverter(defaultAccessTokenConverter());
	}
	
	@Bean
	public DefaultAccessTokenConverter defaultAccessTokenConverter() {
		return new DefaultAccessTokenConverter();
	}
	
}
