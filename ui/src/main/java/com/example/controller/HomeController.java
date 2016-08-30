package com.example.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.entity.Message;


@Controller
public class HomeController {

	@Autowired
	OAuth2RestTemplate restTemplate;
	
	@Value("${messages.url:http://localhost:7777}/api")
	String messagesUrl;
	
	@Value("${servidor.oauth2:localhost:9999}/uaa")
	String servidorAuth;

	@RequestMapping("/")
	String home(Model model) {
		
		model.addAttribute("servidorOauth2", servidorAuth);
		
		String username = restTemplate.getForObject(messagesUrl + "/user", String.class);
		model.addAttribute("userlogado", username);
		
		List<Message> messages = Arrays.asList(restTemplate.getForObject(messagesUrl + "/messages", Message[].class));
		model.addAttribute("messages", messages);
		return "index";
	}

	@RequestMapping(path = "messages", method = RequestMethod.POST)
	String postMessages(@RequestParam String text) {
		Message message = new Message();
		message.text = text;
		restTemplate.exchange(RequestEntity
				.post(UriComponentsBuilder.fromHttpUrl(messagesUrl).pathSegment("messages").build().toUri())
				.body(message), Message.class);
		return "redirect:/";
	}
}
