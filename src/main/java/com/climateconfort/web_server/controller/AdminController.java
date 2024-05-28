package com.climateconfort.web_server.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.climateconfort.web_server.domain.enpresa.service.EnpresaService;
import com.climateconfort.web_server.domain.eraikina.dto.EraikinaDto;
import com.climateconfort.web_server.domain.eraikina.service.EraikinaService;
import com.climateconfort.web_server.domain.gela.dto.GelaDto;
import com.climateconfort.web_server.domain.user.service.UserService;

@Controller
public class AdminController {

    @Autowired
	private EnpresaService enpresaService;

    @Autowired
	private EraikinaService eraikinaService;

	@Autowired
	private UserService userService;
    
	@GetMapping("/administration")
	public String adminMenu(Model model, Principal principal) 
	{
		model.addAttribute("enpresaList", enpresaService.findAllEnpresas());
		return "menuAdmin";
	}

    @GetMapping("/administration/ezabatu")
    public String adminEzabatuEnpresa(Model model, Principal principal, @RequestParam("enpresa_id") Long enpresaID)
    {
		userService.deleteUser(enpresaService.findEnpresaByID(enpresaID).getUser().getUserID());
        enpresaService.deleteEnpresa(enpresaID);
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

    @GetMapping("/administration/eraikinak/gelak")
    public String userIkusiGelak(Model model, Principal principal, @RequestParam("eraikina_id") Long eraikinaID) 
	{
        List<GelaDto> list = eraikinaService.findGelakByEraikinaID(eraikinaID).get();
		model.addAttribute("gelaList", list);
		return "gelak";
	}

}
