package net.slipp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeC {
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
}
