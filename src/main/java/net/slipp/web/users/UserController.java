package net.slipp.web.users;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import net.slipp.dao.users.User;
import net.slipp.dao.users.UserDao;

@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/form")
	public String form(Model model) {
		model.addAttribute("user", new User());
		return "users/form";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult) {
		logger.debug("User : {}", user);
		
		if (bindingResult.hasErrors()) {
			logger.debug("Binding Result has error!");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				logger.debug("error : {}, {} {}",error.getCode(), error.getDefaultMessage());
			}
			return "users/form";
		}
		
		userDao.create(user);
		logger.debug("Database: {}", userDao.findById(user.getUserId()));
		return "redirect:/";
	}
	
	@RequestMapping("/login/form")
	public String loginForm(Model model) {
		model.addAttribute("authenticate", new Authenticate());
		return "users/login";
	}
	
	@RequestMapping("/login")
	public String login(@Valid Authenticate authenticate, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "users/login";
		}
		
		User user = userDao.findById(authenticate.getUserId());
		if (user == null) {
			//유저가 존재 하지 않을 때.
			model.addAttribute("errorMessage", "존재하지 않는 사용자 입니다.");
			return "users/login";
		}
		
		if (!user.getPassword().equals(authenticate.getPassword())) {
			// 비밀번호가 다를 때.
			
		}
		// 세션에 사용자 정보가 저장되어 있을 때.
		
		return "users/login";
	}
}
