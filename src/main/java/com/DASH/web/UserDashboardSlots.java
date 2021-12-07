package com.DASH.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DASH.config.SmsSender;
import com.DASH.model.Slots;
import com.DASH.model.User;
import com.DASH.repository.SlotRepository;
import com.DASH.repository.UserRepository;
import com.DASH.service.SlotService;

@Controller
public class UserDashboardSlots {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SlotService employeeService;	
	@Autowired
	private SlotRepository slotRepository;
	@GetMapping("/userslot")
	public String viewHomePage(Model model, Principal principal) {
		return findPagin(1, "location", "asc", model,principal);		
	}
	@GetMapping("/showNewUserSlotForm")
	public String showNewEmployeeForm(Model model) {
		Slots slots = new Slots();
		
		model.addAttribute("slots", slots);
		return "newuserslot";
	}	
	@PostMapping("/saveUserSlot")
	public String saveslots(@ModelAttribute("slots") Slots slots) {
		employeeService.saveslots(slots);
		return "redirect:/userslot";
	}
	@GetMapping("/showFormForUserSlotUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {	
		Slots slots = employeeService.getslotsbyid(id);
		model.addAttribute("slots", slots);
		return "createbook";
	}
	@GetMapping("/deleteUserSlot/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {	
		this.employeeService.deleteslotsbyid(id);
		return "redirect:/userslot";
	}
	@GetMapping("/userslotpage/{pageNo}")
	public String findPagin(@PathVariable (value = "pageslot") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model,Principal principal) {
		int pageSize = 5;	
		User user = userRepository.findByEmail(principal.getName());
		model.addAttribute("user", user);
		Page<Slots> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		
		List<Slots> listslots = page.getContent();
		List<Slots> loloipop = slotRepository.findSlotsByStatusIsNot(0);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listslots", loloipop);
		return "userslot";
	}
	
	@RequestMapping("/process/{id}")
	public String url(@PathVariable("id")Long id, Model model,Principal principal) {
		Slots slot = slotRepository.findById(id).get();
		User u=userRepository.findByEmail(principal.getName());
		String rt=u.getFirstName();
		slot.setstatus(slot.getstatus()-1);
		String ss=slot.getuser();
		if(ss==null) {
			ss=rt;
		}
		else {
		ss=ss+" "+rt;
		}
		slot.setuser(ss);
		String sta=slot.getstart();
		String ste=slot.getend();
		String contact=u.getPhoneno();
		slotRepository.save(slot);
		char[] staa = sta.toCharArray();
		char[] stee = ste.toCharArray();
		int sh=0,sm=0,eh=0,em=0;
		for(int i=0;i<2;i++){
		    int dig=(int)staa[i];
		    dig-=48;
		    sh*=10;
		    sh+=dig;
		    
		    int dig1=(int)staa[i+3];
		    dig1-=48;sm*=10;
		    sm+=dig1;
		    
		    int dig2=(int)stee[i];
		    dig2-=48;eh*=10;
		    eh+=dig2;
		    
		    int dig3=(int)stee[i+3];
		    dig3-=48;em*=10;
		    em+=dig3;
		    
		}
		int difh=eh-sh;
		int difm=em-sm;
		difh*=60;
		float costf=100;
		difh+=difm;
		float hourly=25;
		hourly*=difh;
		hourly/=60;
		costf+=hourly;
		int cost=(int)costf;
		System.out.println(cost);
		model.addAttribute("slot", slot);
		model.addAttribute("id", id);
		model.addAttribute("cost",cost);
			System.out.print("page1");
		SmsSender sms=new SmsSender();
	sms.sendSms("Your Booking Is Successful with Booking ID: "+slot.getId()+" Location: "+slot.getlocation()+" Date: "+slot.getdate()+" Start: "+slot.getstart()+" End: "+slot.getend(), contact);
	return "payment";	
	}
}

