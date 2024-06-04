package com.climateconfort.web_server.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.climateconfort.web_server.domain.enpresa.dto.EnpresaDto;
import com.climateconfort.web_server.domain.enpresa.service.EnpresaService;
import com.climateconfort.web_server.domain.eraikina.dto.EraikinaDto;
import com.climateconfort.web_server.domain.eraikina.service.EraikinaService;
import com.climateconfort.web_server.domain.gela.dto.GelaDto;
import com.climateconfort.web_server.domain.gela.service.GelaService;

import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private EnpresaService enpresaService;

    @Autowired
    private EraikinaService eraikinaService;

    @Autowired
    private GelaService gelaService;

    @GetMapping("/userMenu")
    public String userMenu(Model model, Principal principal) {
        return "menu_user";
    }

    @GetMapping("/userMenu/eraikinak")
    public String userIkusiEraikinak(Model model, Principal principal,
            @AuthenticationPrincipal UserDetails userDetails) {
        List<EraikinaDto> list = null;
        for (EnpresaDto iterator : enpresaService.findAllEnpresas()) {
            if (iterator.getUser().getEmail().equals(userDetails.getUsername())) {
                list = enpresaService.findEraikinakByEnpresaID(iterator.getEnpresaID()).get();
            }
        }

        model.addAttribute("eraikinaList", list);
        return "eraikinak_user";
    }

    @GetMapping("/userMenu/eraikinak/gelak")
    public String userIkusiGelak(Model model, Principal principal, @RequestParam("eraikina_id") Long eraikinaID) {

        Optional<List<GelaDto>> optionalGelaList = eraikinaService.findGelakByEraikinaID(eraikinaID);
        if (optionalGelaList.isPresent()) {
            List<GelaDto> gelaList = optionalGelaList.get();
            model.addAttribute("gelaList", gelaList);
        } else {
            model.addAttribute("errorMessage", "No se encontraron gelas para el ID de eraikina proporcionado.");
        }
        // List<GelaDto> list = eraikinaService.findGelakByEraikinaID(eraikinaID).get();
        // model.addAttribute("gelaList", list);
        return "gelak";
    }

    @GetMapping("/userMenu/eraikinak/gela")
    public String userIkusiGelaBat(Model model, Principal principal, @RequestParam("gela_id") Long gelaID) {
        model.addAttribute("gela", gelaService.fingGelaByGelaID(gelaID));
        return "gela_interfazea";
    }

    @GetMapping("/userMenu/eraikinak/gela/parametroaAldatu")
    public String userParametroaAldatuRequest(Model model, Principal principal) {
        int parametro = 0;
        model.addAttribute("parametro", parametro);
        return "parametro_berria";
    }

    @PostMapping("/userMenu/eraikinak/gela/parametroaAldatu")
    public String userParametroaAldatu(@Valid @ModelAttribute("parametro") int parametro, Model model,
            Principal principal) {
        GelaDto gela = gelaService.fingGelaByGelaID(Long.valueOf(1));
        gela.setParamMin(parametro);
        model.addAttribute("gela", gela);
        return "gela_interfazea";
    }

}
