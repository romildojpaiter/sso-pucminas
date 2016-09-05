package br.org.doasoft.oauth2.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.org.doasoft.oauth2.entity.UserRoles;

public interface UserRolesRepository extends Repository<UserRoles, Integer> {
	
	List<UserRoles> findByUsername(String username);

}
