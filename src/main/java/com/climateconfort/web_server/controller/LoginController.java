package com.climateconfort.web_server.controller;

import java.security.Principal;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.climateconfort.web_server.domain.user.dto.UserDto;
import com.climateconfort.web_server.domain.user.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") UserDto user,
			BindingResult result,
			Model model, HttpServletRequest request) {
		if (userService.findUserDtoByEmail(user.getEmail()).isPresent()) {
			result.rejectValue("email", null, "There is already an account registered with that email");
			model.addAttribute("user", user);
			return "register";
		}
		userService.saveUser(user);
		return "redirect:" + request.getHeader("Referer") + "?zuzen";
	}

	@GetMapping("/administration")
	public String adminMenu(Model model, Principal principal) {

		model.addAttribute("userList", userService.findAllUsers());
		model.addAttribute("", principal);
		model.addAttribute("user", userService.findUserDtoByEmail(principal.getName()));
		return "menuAdmin";
	}

}
