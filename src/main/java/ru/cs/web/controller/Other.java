package ru.cs.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.cs.security.User;
import ru.cs.service.PasswordNewService;
import ru.cs.util.WebUtils;

@Controller
public class Other {
	
	@Autowired
	PasswordNewService passwordNewService;

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String passwordPage(Model model, Principal principal) {
		return "password";
	}

	@RequestMapping(value = "/password_new", method = RequestMethod.POST)
	public String passwordNewPage(
			@RequestParam(name="pswcur", required=true, defaultValue="") String pswcur,
			@RequestParam(name="pswnew1", required=false, defaultValue="") String pswnew1,
			@RequestParam(name="pswnew2", required=false, defaultValue="") String pswnew2,
			Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		boolean b = passwordNewService.execute(loginedUser, pswcur, pswnew1, pswnew2);
		return "under_construction";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
				+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);

		}

		return "403";
	}



}
