package br.org.doasoft.oauth2.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.doasoft.oauth2.entity.UserRoles;
import br.org.doasoft.oauth2.repository.UserRepository;
import br.org.doasoft.oauth2.repository.UserRolesRepository;

@Service("doasoftUserDetailsService")
public class DoasoftUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRolesRepository userRolesRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		br.org.doasoft.oauth2.entity.User usuarioLogin = userRepository.findFirstByUsername(username);
		List<UserRoles> userRoles = userRolesRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildUserAuthority(new HashSet<UserRoles>(userRoles));
		return buildUserForAuthentication(usuarioLogin, authorities);
	}
	
	private User buildUserForAuthentication(br.org.doasoft.oauth2.entity.User user, List<GrantedAuthority> authorities) 
	{	
		boolean enabled = true;
	    boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
	    
	    return new User(
	            user.getUsername(),
	            user.getPassword(),
	            enabled,
	            accountNonExpired,
	            credentialsNonExpired,
	            accountNonLocked,
	            authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRoles> userRoles) 
	{
		// private List<GrantedAuthority> buildUserAuthority() {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
		for (UserRoles userRole : userRoles) {
			authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		// authorities.add(new SimpleGrantedAuthority("Administrator"));
		
		return authorities;
	}	

}
