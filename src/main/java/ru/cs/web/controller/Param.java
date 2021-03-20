package ru.cs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.cs.service.SessionParam;

@Controller
public class Param {

	@Autowired
	SessionParam  sessionParam;
	
	@RequestMapping(value = { "/param" }, method = RequestMethod.GET)
	public String paramPage(Model model) {

		sessionParam.setFirmId(1L);
		return "param";
	}

}
