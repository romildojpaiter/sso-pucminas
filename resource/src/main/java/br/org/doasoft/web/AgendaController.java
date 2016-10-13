package br.org.doasoft.web;

import br.org.doasoft.entity.Agenda;
import br.org.doasoft.repository.AgendaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by romildopaiter on 01/10/16.
 */
@RestController
@RequestMapping(value = "/agenda")
public class AgendaController {

    Logger logger = LoggerFactory.getLogger(AgendaController.class);

    @Autowired
    AgendaRepository agendaRepository;

    @RequestMapping(value = "/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Agenda> salvar(Principal user, @RequestBody Agenda agenda){

        logger.info("Inserindo um agendamento.");
        try {
			agendaRepository.save(agenda);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Agenda>(agenda, HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<Agenda>(agenda, HttpStatus.OK);
    }

}
