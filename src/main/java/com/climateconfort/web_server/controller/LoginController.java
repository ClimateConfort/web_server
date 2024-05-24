package com.climateconfort.web_server.controller;

import java.security.Principal;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.climateconfort.web_server.domain.enpresa.dto.EnpresaDto;
import com.climateconfort.web_server.domain.enpresa.service.EnpresaService;
import com.climateconfort.web_server.domain.eraikina.dto.EraikinaDto;
import com.climateconfort.web_server.domain.role.model.Role.RoleType;
import com.climateconfort.web_server.domain.user.dto.UserDto;
import com.climateconfort.web_server.domain.user.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private EnpresaService enpresaService;

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) 
	{
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "register_enpresa";
	}

	@PostMapping("/register/save")
	public String enpresaRegistration(@Valid @ModelAttribute("user") UserDto user,
			BindingResult result, Model model, HttpServletRequest request)
	{

		user.setRoleType(RoleType.User);
		EnpresaDto enpresa = new EnpresaDto();
		enpresa.setIzena(user.getEnpresa());
		userService.saveUser(user);
		enpresa.setUser(userService.findUserByEmail(user.getEmail()).get());
		enpresaService.saveEnpresa(enpresa);

		// TODO: Si eso añadirle strings al user para crear un edificio y una gela  :)
 
		model.addAttribute("enpresaList", enpresaService.findAllEnpresas());
		return "menuAdmin";
	}

	@GetMapping("/administration")
	public String adminMenu(Model model, Principal principal) 
	{
		model.addAttribute("enpresaList", enpresaService.findAllEnpresas());
		return "menuAdmin";
	}

	@GetMapping("/administration/eraikinak")
	public String adminIkusiEraikinak(Model model, Principal principal, @RequestParam("enpresa_id") Long enpresaID) 
	{
		List<EraikinaDto> list = enpresaService.findEraikinakByEnpresaID(enpresaID).get();
		model.addAttribute("eraikinaList", list);
		return "eraikinak";
	}


	@GetMapping("/userMenu")
	public String userMenu(Model model, Principal principal) 
	{
		return "menu_user";
	}

}
