package com.DASH.web;

import java.security.Principal;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Controller
public class MainController {
	
	@GetMapping("/userlogin")
	public String login() {
		return "userlogin";
	}	
	@GetMapping("/userdashboard")
	public String home() {
		return "userdashboard";
	}
	@RequestMapping("/admin")
	public String admin() {
		return "admindash";
	}
	@RequestMapping("/")
	public String adminsub() {
		return "test";
	}
	@RequestMapping("/adminlogin")
	public String adsub() {
		return "testing";
	}
	@RequestMapping("/workerlogin")
	public String wlogin() {
		return "workerlogin";
	}
	@RequestMapping("/workerdashboard")
	public String workerdash() {
		return "workerdashboard";
	}
	@RequestMapping("/userslot")
	public String userslot() {
		return "userslot";
	}
	@RequestMapping("/payment")
	public String paymentslot() {
		return "payment";
	}
	@RequestMapping("/userprofile")
	public String profile() {
	return "userprofile";	
	}
	@RequestMapping("/about")
	public String about() {
		return "aboutus";
	}
	@PostMapping("/create_order")
    @ResponseBody
    public String createOrder(@RequestBody Map<String, Object> data, Principal principal) throws Exception
    {System.out.print("page1");
        int amt=Integer.parseInt(data.get("amount").toString());
        RazorpayClient client=new RazorpayClient("rzp_test_3fGEPJTbBw4c9f", "ntnofRbVEbYf5xQ7q962WQZE");
        JSONObject ob=new JSONObject();
        ob.put("amount", amt*100);
        ob.put("currency", "INR");
        ob.put("receipt", "txn_235425");
        Order order = client.Orders.create(ob);
        return order.toString();
    }
}
