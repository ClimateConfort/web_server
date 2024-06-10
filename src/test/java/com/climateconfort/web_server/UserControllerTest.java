package com.climateconfort.web_server;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.climateconfort.web_server.controller.UserController;
import com.climateconfort.web_server.domain.enpresa.service.EnpresaService;
import com.climateconfort.web_server.domain.eraikina.service.EraikinaService;
import com.climateconfort.web_server.domain.gela.dto.GelaDto;
import com.climateconfort.web_server.domain.gela.service.GelaService;

@WebMvcTest(UserController.class)
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnpresaService enpresaService;

    @MockBean
    private EraikinaService eraikinaService;

    @MockBean
    private GelaService gelaService;

    @Test
    @WithMockUser(username = "test@example.com", roles = {"USER"})
    void testUserMenu() throws Exception {
        mockMvc.perform(get("/userMenu"))
               .andExpect(status().isOk())
               .andExpect(view().name("menu_user"));
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = {"USER"})
    void testUserIkusiEraikinak() throws Exception {
        mockMvc.perform(get("/userMenu/eraikinak")
        .with(user("test@example.com").roles("USER")))
        .andExpect(status().isOk())
        .andExpect(view().name("eraikinak_user"));
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = {"USER"})
    void testUserIkusiGelak() throws Exception {
        Long eraikinaId = 1L;

        mockMvc.perform(get("/userMenu/eraikinak/gelak")
                            .param("eraikina_id", eraikinaId.toString())
                            .with(user("test@example.com").roles("USER")))
               .andExpect(status().isOk())
               .andExpect(view().name("gelak"));
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = {"USER"})
    void testUserIkusiGelaBat() throws Exception {
        Long gelaId = 1L;

        GelaDto gelaDto = new GelaDto();
        gelaDto.setGelaID(gelaId);
        when(gelaService.fingGelaByGelaID(1L)).thenReturn(gelaDto);

        mockMvc.perform(get("/userMenu/eraikinak/gela").param("gela_id", gelaId.toString())
                .with(user("test@example.com").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("gela_interfazea"))
                .andExpect(model().attributeExists("gela"))
                .andExpect(model().attribute("gela", gelaDto));
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = {"USER"})
    void testUserParametroaAldatuRequest() throws Exception {
        mockMvc.perform(get("/userMenu/eraikinak/gela/parametroaAldatu")
                .with(user("test@example.com").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("parametro_berria"))
                .andExpect(model().attributeExists("parametro"))
                .andExpect(model().attribute("parametro", 0));
    }

}
