package com.DASH.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DASH.model.*;

import com.DASH.service.*;

@Controller
public class SlotController {
	@Autowired
	private SlotService employeeService;	
	@GetMapping("/slot")
	public String viewHomePage(Model model) {
		return findPagin(1, "location", "asc", model);		
	}
	@GetMapping("/showNewSlotForm")
	public String showNewEmployeeForm(Model model) {
		Slots slots = new Slots();
		model.addAttribute("slots", slots);
		return "new_slot";
	}	
	@PostMapping("/saveSlot")
	public String saveslots(@ModelAttribute("slots") Slots slots) {
		employeeService.saveslots(slots);
		return "redirect:/slot";
	}
	@GetMapping("/showFormForSlotUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {	
		Slots slots = employeeService.getslotsbyid(id);
		model.addAttribute("slots", slots);
		
		return "update_slot";
	}
	@GetMapping("/deleteSlot/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {	
		this.employeeService.deleteslotsbyid(id);
		return "redirect:/slot";
	}
	@GetMapping("/slotpage/{pageNo}")
	public String findPagin(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;	
		Page<Slots> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Slots> listslots = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listslots", listslots);
		return "SlotDash";
	}
}
