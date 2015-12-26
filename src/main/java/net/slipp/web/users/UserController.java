package net.slipp.web.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.slipp.dao.users.User;
import net.slipp.dao.users.UserDao;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/users/form")
	public String form() {
		return "users/form";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String create(User user) {
		logger.debug("User : {}", user);
		userDao.create(user);
		logger.debug("Database: {}", userDao.findById(user.getUserId()));
		return "users/form";
	}
}
