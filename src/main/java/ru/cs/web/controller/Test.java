package ru.cs.web.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.cs.security.User;
import ru.cs.util.WebUtils;

@Controller
public class Test {

	@RequestMapping(value = "/test_param", method = RequestMethod.GET)
	public String simple1Page(@RequestParam(name="name", required=false, defaultValue="default text") String name, Model model) {
		model.addAttribute("name", name);
		return "test_param";
	}
	
	@RequestMapping(value = "/test_admin", method = RequestMethod.GET)
	public String adminPage(Model model, Principal principal) {
		
		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		return "test_admin";
	}
	
	@RequestMapping(value = "/test_user", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {

		// After user login successfully.
		//String userName = principal.getName();

		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);

		return "test_user";
	}
}
