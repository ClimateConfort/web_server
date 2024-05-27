package com.climateconfort.web_server.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.climateconfort.web_server.domain.enpresa.dto.EnpresaDto;
import com.climateconfort.web_server.domain.enpresa.service.EnpresaService;
import com.climateconfort.web_server.domain.eraikina.dto.EraikinaDto;
import com.climateconfort.web_server.domain.eraikina.service.EraikinaService;
import com.climateconfort.web_server.domain.gela.dto.GelaDto;
import com.climateconfort.web_server.domain.role.model.Role.RoleType;
import com.climateconfort.web_server.domain.user.dto.UserDto;
import com.climateconfort.web_server.domain.user.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private EnpresaService enpresaService;

	@Autowired
	private EraikinaService eraikinaService;

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
		EnpresaDto enpresa = new EnpresaDto();
		EraikinaDto eraikina = new EraikinaDto();
		GelaDto gela = new GelaDto();

		user.setRoleType(RoleType.User);
		userService.saveUser(user);

		enpresa.setIzena(user.getEnpresaIzena());
		enpresa.setUser(userService.findUserByEmail(user.getEmail()).get());
		enpresaService.saveEnpresa(enpresa);

		eraikina.setIzena(user.getEnpresaIzena());
		eraikina.setEnpresa(enpresaService.findEnpresaByIzena(enpresa.getIzena()));
		eraikina.setLokalizazioa(user.getEraikinaLokalizazioa());
		eraikinaService.saveEraikina(eraikina);
		
		gela.setIzena(user.getGelaIzena());
 
		model.addAttribute("enpresaList", enpresaService.findAllEnpresas());
		return "menuAdmin";
	}

}
