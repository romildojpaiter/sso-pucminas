package br.org.doasoft.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.org.doasoft.entity.Agenda;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by romildopaiter on 9/28/16.
 */
@RepositoryRestResource(collectionResourceRel = "agenda", path = "agenda")
@PreAuthorize("hasRole('ROLE_USER')")
public interface AgendaRepository extends PagingAndSortingRepository<Agenda, Long> {
	
}
