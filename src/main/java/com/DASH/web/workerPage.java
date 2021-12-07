package com.DASH.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.DASH.model.Slots;
import com.DASH.repository.SlotRepository;
import com.DASH.repository.UserRepository;
import com.DASH.service.SlotService;
@Controller
public class workerPage {
	@Autowired
	private SlotService employeeService;	
	@Autowired
	private SlotRepository slotRepository;
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/workerslot")
	public String viewHomePage(Model model) {
	return findPaginated(1, "location", "asc", model);		
	}
	@GetMapping("/worker/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
		@RequestParam("sortField") String sortField,
		@RequestParam("sortDir") String sortDir,
		Model model) {
		int pageSize = 5;	
		Page<Slots> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Slots> listslots = slotRepository.findSlotsByWorkerContains("Mohit");
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listslots", listslots);
		return "workerslot";
	}
}