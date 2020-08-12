package org.webproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.webproject.dao.UserDao;
import org.webproject.model.User;

@Controller
public class UserLoginController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView userLogin(@RequestParam("email") String email, @RequestParam("password") String password) {

		ModelAndView mv = new ModelAndView();

		User user = new User();
		user.setemail(email);
		user.setPassword(password);
		
		String name = userDao.loginUser(user);

		if (name != null) {

			mv.addObject("msg", "Welcome " + name + ", You have successfully logged in.");
			mv.setViewName("dashboard");

		} else {

			mv.addObject("msg", "Invalid user id or password.");
			mv.setViewName("login");
		}

		return mv;

	}

}
