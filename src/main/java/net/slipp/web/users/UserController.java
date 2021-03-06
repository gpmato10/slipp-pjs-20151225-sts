package net.slipp.web.users;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.slipp.dao.users.User;
import net.slipp.dao.users.UserDao;

@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userDao")
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
	public String login(@Valid Authenticate authenticate, BindingResult bindingResult, Model model
			,HttpSession session) {
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
			model.addAttribute("errorMessage", "비밀번호가 틀립니다.");
			return "users/login";
		}
		
		session.setAttribute("userId", user.getUserId());
		
		// 세션에 사용자 정보가 저장되어 있을 때.
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		return "redirect:/";
	}
	
	@RequestMapping("{userId}/form")
	public String upadateForm(@PathVariable String userId,Model model) {
		if (userId == null) {
			throw new IllegalArgumentException("사용자 아이디가 필요합니다.");
		}
		User user = userDao.findById(userId);
		
		model.addAttribute("user", user);
		return "users/form";
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public String update(@Valid User user, BindingResult bindingResult, HttpSession session) {
		logger.debug("User : {}", user);
		
		if (bindingResult.hasErrors()) {
			logger.debug("Binding Result has error!");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				logger.debug("error : {}, {} {}",error.getCode(), error.getDefaultMessage());
			}
			return "users/form";
		}
		
		//		유저아이디가 존재하지 않을 경우 에러 처리 해야함..
		Object temp = session.getAttribute("userId");
		if (temp == null) {
			throw new NullPointerException();
		}
		
		userDao.update(user);
		logger.debug("Database: {}", userDao.findById(user.getUserId()));
		return "redirect:/";
	}
}
