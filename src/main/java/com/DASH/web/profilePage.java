package com.DASH.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DASH.model.*;
import com.DASH.repository.SlotRepository;
import com.DASH.repository.UserRepository;
import com.DASH.service.*;

@Controller
public class profilePage {
	@Autowired
	private SlotService employeeService;	
	@Autowired
	private SlotRepository slotRepository;
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/userprofile")
	public String viewHomePage(Model model,Principal principal) {
		return findPaginated(1, "location", "asc", model,principal);		
	}
	@GetMapping("/pageprofile/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model,Principal principal) {
		int pageSize = 5;	
		User user = userRepository.findByEmail(principal.getName());
		Page<Slots> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Slots> listslots = slotRepository.findSlotsByUserContains(user.getFirstName());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listslots", listslots);
		return "userprofile";
	}
}
