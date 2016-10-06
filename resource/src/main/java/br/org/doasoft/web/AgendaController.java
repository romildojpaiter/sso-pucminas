package br.org.doasoft.web;

import br.org.doasoft.entity.Agenda;
import br.org.doasoft.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by romildopaiter on 01/10/16.
 */
@RestController
@RequestMapping(value = "/agendac")
public class AgendaController {

    @Autowired
    AgendaRepository agendaRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST, params = {"nome", "laboratorio", "data"}, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Agenda> salvar(Principal user,
                                         @RequestParam(value="nome") String nome,
                                         @RequestParam(value="laboratorio") String laboratorio,
                                         @RequestParam(value="data") String data ){

        System.out.println(nome + " " + laboratorio + " " + data);

        // return ResponseEntity.status(HttpStatus.OK).body(CertidaoNegativaPjeDTO.transformList(processos));

        return null;
    }

}
