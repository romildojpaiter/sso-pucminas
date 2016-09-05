package br.org.doasoft.oauth2.repository;

import org.springframework.data.repository.Repository;

import br.org.doasoft.oauth2.entity.User;

public interface UserRepository extends Repository<User, Integer> {

	User findFirstByUsername(String username);

}
