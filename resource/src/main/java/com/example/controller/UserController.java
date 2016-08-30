package com.example.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping(path = "api/user", method = RequestMethod.GET)
	String getMessages(Principal principal) {
        return principal.getName();
    }

}
