package com.edgetech.showoff.controller;

import com.edgetech.showoff.model.User;
import com.edgetech.showoff.service.NotificationService;
import com.edgetech.showoff.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/*
 *	Handles our registration and login pages
 */
@Controller
public class AuthenticationController {

	private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	UserService userService;

	@Autowired
	NotificationService notificationService;

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = { "/name" })
	public String name(Model model) {
		model.addAttribute("email", "Gary James");
		return "login";
	}

	/*
	 *	display the registration page.
	 * 		Create an empty user
	 * 		present the register page
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		User user = new User();
		//	if you wanted to prefill some of the screen input fields
		//		you could set the values in the user object
		model.addAttribute("user", user);
		return "register";				// resources/template/register.html
	}

	/*
	 *	Just a dummy home page. Nothing interesting
	 * now i see what is happening this is the definition for a TABLE element TR is for ROWS
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
		return modelAndView;
	}

	/*
	 *	Just a dummy home page. Nothing interesting
	 * 		but the only way you can get to it is if you have sign in as an admin user
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin"); // resources/template/admin.html
		return modelAndView;
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		// Check for the validations
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please correct the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if(userService.isUserAlreadyPresent(user)){
			modelAndView.addObject("successMessage", "user already exists!");
		}
		// we will save the user if, no binding errors
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User is registered successfully!");
		}

		//	------------	this is where we send an email
		try {
			notificationService.sendNotification(user);
		} catch (MailException ex) {
			logger.info("Error Send email " + ex.getMessage());
		}

		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}
}
