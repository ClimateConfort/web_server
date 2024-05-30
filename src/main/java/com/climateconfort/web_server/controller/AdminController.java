package com.climateconfort.web_server.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.climateconfort.web_server.domain.enpresa.service.EnpresaService;
import com.climateconfort.web_server.domain.eraikina.dto.EraikinaDto;
import com.climateconfort.web_server.domain.eraikina.service.EraikinaService;
import com.climateconfort.web_server.domain.gela.dto.GelaDto;
import com.climateconfort.web_server.domain.gela.service.GelaService;
import com.climateconfort.web_server.domain.user.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AdminController {

	@Autowired
	private EnpresaService enpresaService;

	@Autowired
	private EraikinaService eraikinaService;

	@Autowired
	private UserService userService;

	@Autowired
	private GelaService gelaService;

	@GetMapping("/administration")
	public String adminMenu(Model model, Principal principal) {
		model.addAttribute("enpresaList", enpresaService.findAllEnpresas());
		return "menuAdmin";
	}

	@GetMapping("/administration/ezabatu")
	public String adminEzabatuEnpresa(Model model, Principal principal, @RequestParam("enpresa_id") Long enpresaID) {
		userService.deleteUser(enpresaService.findEnpresaByID(enpresaID).getUser().getUserID());
		enpresaService.deleteEnpresa(enpresaID);
		model.addAttribute("enpresaList", enpresaService.findAllEnpresas());
		return "menuAdmin";
	}

	@GetMapping("/administration/eraikinak")
	public String adminIkusiEraikinak(Model model, Principal principal, @RequestParam("enpresa_id") Long enpresaID) {
		List<EraikinaDto> list = enpresaService.findEraikinakByEnpresaID(enpresaID).get();
		model.addAttribute("eraikinaList", list);
		return "eraikinak";
	}

	@GetMapping("/administration/eraikinak/gelak")
	public String userIkusiGelak(Model model, Principal principal, @RequestParam("eraikina_id") Long eraikinaID) {
		List<GelaDto> list = eraikinaService.findGelakByEraikinaID(eraikinaID).get();
		model.addAttribute("gelaList", list);
		return "gelak_admin";
	}

	@GetMapping("/administration/eraikinak/gela")
	public String userIkusiGelaBat(Model model, Principal principal, @RequestParam("gela_id") Long gelaID) {
		model.addAttribute("gela", gelaService.fingGelaByGelaID(gelaID));
		return "gela_admin";
	}

	@GetMapping("/administration/eraikinak/gela/parametroaAldatu")
	public String administrationParametroaAldatuRequest(Model model, Principal principal) {
		int parametro = 0;
		model.addAttribute("parametro", parametro);
		return "parametro_berria_admin";
	}

	@PostMapping("/administration/eraikinak/gela/parametroaAldatu")
	public String administrationParametroaAldatu(@Valid @ModelAttribute("parametro") int parametro, Model model,
			Principal principal) {
		GelaDto gela = gelaService.fingGelaByGelaID(Long.valueOf(1));
		gela.setParamMin(parametro);
		model.addAttribute("gela", gela);
		return "gela_admin";
	}

}
