package com.climateconfort.web_server;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.climateconfort.web_server.controller.AdminController;
import com.climateconfort.web_server.domain.enpresa.dto.EnpresaDto;
import com.climateconfort.web_server.domain.enpresa.service.EnpresaService;

// @WebMvcTest(AdminController.class)
// class AdminControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private EnpresaService enpresaService;

//     @Test
//     @WithMockUser(username = "admin", roles = {"ADMIN"})
//     void testAdminMenu() throws Exception {
//         List<EnpresaDto> enpresaList = new ArrayList<>();
//         when(enpresaService.findAllEnpresas()).thenReturn(enpresaList);

//         mockMvc.perform(get("/administration"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("menuAdmin"))
//                .andExpect(model().attributeExists("enpresaList"))
//                .andExpect(model().attribute("enpresaList", enpresaList));
//     }
    
// }
