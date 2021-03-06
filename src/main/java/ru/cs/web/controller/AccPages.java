package ru.cs.web.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import ru.cs.entity.Acc;
import ru.cs.security.User;
import ru.cs.service.AccService;
import ru.cs.service.SessionParam;

@Controller
public class AccPages {

	@Autowired
	AccService accService;
	
	@Autowired
	SessionParam  sessionParam;

	@RequestMapping(value = "/acc", method = RequestMethod.GET)
	public String accPage(Model model, Principal principal, HttpSession ses,
			@RequestParam(name = "page", required = false, defaultValue = "1") int pageNum) {

		Long userId = ((User) ((Authentication) principal).getPrincipal()).getId();
		Pageable p = PageRequest.of(pageNum - 1, 10);

		Long firmId = sessionParam.getFirmId();
		
		Page<ru.cs.entity.Acc> dataPage = accService.findAllByFirmIdOrderById(firmId, p);

		model.addAttribute("dataPage", dataPage);
		return "acc";
	}

	@RequestMapping(value = "/acc/edit", method = RequestMethod.GET)
	public String accEditPage(Model model, Principal principal,
			@RequestParam(name = "id", required = false, defaultValue = "0") Long accId) {

		Long userId = ((User) ((Authentication) principal).getPrincipal()).getId();

		Acc acc;
		if (accId != 0) {
			if (accService.isAccess(accId, sessionParam.getFirmId())==false) return "forward:/acc";
			Optional<Acc> oAcc = accService.findById(accId);
			acc = oAcc.get();
		} else {
			acc = new Acc();
		}

		model.addAttribute("acc", acc);
		model.addAttribute("accId", accId);
		return "acc_edit";
	}

	@RequestMapping(value = "/acc/edit/save", method = RequestMethod.POST)
	public String accEditSavePage(Model model, Principal principal, @ModelAttribute("acc") Acc acc) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		
		if (acc.getId()!=null) {
			if (accService.isAccess(acc.getId(), sessionParam.getFirmId())==false) return "forward:/acc";
		}

		acc.setFirmId(sessionParam.getFirmId());
		accService.save(acc);

		return "redirect:/acc";
	}

	@RequestMapping(value = "/acc/edit/delete", method = RequestMethod.GET)
	public String accEditDeletePage(Model model, Principal principal,
			@RequestParam(name = "id", required = false, defaultValue = "0") Long accId) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		if (accService.isAccess(accId, sessionParam.getFirmId())==false) return "redirect:/acc";
		
		accService.deleteById(accId);
		return "redirect:/acc";
	}

}
