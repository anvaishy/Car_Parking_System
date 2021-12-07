package com.DASH.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DASH.service.UserService;
import com.DASH.web.dto.UserRegistrationDto;

import java.util.Random;
class Generator {
    public static String generateRandomPassword(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                +"lmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
}
@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	@Autowired
    private EmailController emailService;
	private UserService userService;
	 Generator obj=new Generator();
	    public String Otp= obj.generateRandomPassword(8);    
	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	@GetMapping
	public String showRegistrationForm() {	
		return "registration";
	}
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @RequestParam(value = "otp", defaultValue="")String otp, UserRegistrationDto registrationDto ) {	
		if (!otp.equalsIgnoreCase(Otp)) {
			emailService.sendOtp(registrationDto.getEmail(), registrationDto.getFirstName(), Otp);	
		}
		if (otp.equalsIgnoreCase(Otp)) {
			userService.save(registrationDto);
			emailService.sendHelloEmail(registrationDto.getEmail(), registrationDto.getFirstName());	
		}		
		return "redirect:/registration?success";
	}
}
