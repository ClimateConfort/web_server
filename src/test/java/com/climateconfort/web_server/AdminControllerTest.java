package com.climateconfort.web_server;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Optional;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import com.climateconfort.web_server.controller.AdminController;
import com.climateconfort.web_server.domain.enpresa.model.Enpresa;
import com.climateconfort.web_server.domain.enpresa.service.EnpresaService;
import com.climateconfort.web_server.domain.eraikina.dto.EraikinaDto;
import com.climateconfort.web_server.domain.eraikina.model.Eraikina;
import com.climateconfort.web_server.domain.eraikina.service.EraikinaService;
import com.climateconfort.web_server.domain.gela.dto.GelaDto;
import com.climateconfort.web_server.domain.gela.service.GelaService;
import com.climateconfort.web_server.domain.user.service.UserService;

class AdminControllerTest {

    @InjectMocks
    AdminController adminController;

    @Mock
    UserService userService;

    @Mock
    EnpresaService enpresaService;

    @Mock
    EraikinaService eraikinaService;

    @Mock
    GelaService gelaService;

    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        model = new ExtendedModelMap();
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = {"ADMIN"})
    void testAdminIkusiEraikinak() {
        Enpresa enpresa = new Enpresa();
        enpresa.setEnpresaID(1L);
        EraikinaDto eraikinaFake = new EraikinaDto();
        eraikinaFake.setEnpresa(enpresa);
        eraikinaFake.setEraikinaID(1L);
        eraikinaFake.setIzena("izena");
        eraikinaFake.setLokalizazioa("lokalizazio");

        when(enpresaService.findEraikinakByEnpresaID(anyLong())).thenReturn(Optional.of(Collections.singletonList(eraikinaFake)));
        String result = adminController.adminIkusiEraikinak(model, null, 1L);
        assertEquals("eraikinak", result);
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = {"ADMIN"})
    void testAdminIkusiGelak() {
        Eraikina eraikina = new Eraikina();
        eraikina.setEraikinaID(1L);
        GelaDto gelaFake = new GelaDto();
        gelaFake.setEraikina(eraikina);

        when(eraikinaService.findGelakByEraikinaID(anyLong())).thenReturn(Optional.of(Collections.singletonList(gelaFake)));
        String result = adminController.adminIkusiGelak(model, null, 1L);
        assertEquals("gelak_admin", result);
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = {"ADMIN"})
    void testAdminIkusiGelaBat() {
        GelaDto gelaFake = new GelaDto();
        gelaFake.setGelaID(1L);

        when(gelaService.fingGelaByGelaID(anyLong())).thenReturn(gelaFake);
        String result = adminController.adminIkusiGelaBat(model, null, 1L);
        assertEquals("gela_admin", result);
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = {"ADMIN"})
    void testAdministrationParametroaAldatuRequest() {
        String result = adminController.administrationParametroaAldatuRequest(model, null);
        assertEquals("parametro_berria_admin", result);
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = {"ADMIN"})
    void testAdministrationParametroaAldatut() {
        GelaDto gelaFake = new GelaDto();
        gelaFake.setGelaID(1L);

        when(gelaService.fingGelaByGelaID(anyLong())).thenReturn(gelaFake);
        String result = adminController.administrationParametroaAldatu(1, model, null);
        assertEquals("gela_admin", result);
    }

}
