package br.org.doasoft.oauth2.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}

}
