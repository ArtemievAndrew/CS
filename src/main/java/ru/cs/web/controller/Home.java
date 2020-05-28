package ru.cs.web.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.cs.security.User;

@Controller
public class Home {
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(Model model, Principal principal) {

		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		model.addAttribute("name", loginedUser.getUsername());

		return "home";
	}

}
