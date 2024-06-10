package com.climateconfort.web_server;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.climateconfort.web_server.config.SpringSecurity;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class SecirutyTest {
      
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUnauthenticatedAccessToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    @WithMockUser
    void testAuthenticatedAccessToUserMenu() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userMenu"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("menu_user"));
    }

    @Test
    @WithMockUser(roles = "Administrator")
    void testAuthenticatedAccessToAdministrationPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/administration"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("menuAdmin"));
    }

    @Test
    void testLogout() throws Exception {
        mockMvc.perform(SecurityMockMvcRequestBuilders.logout("/logout"))
               .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
               .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

    @Test
    void testPasswordEncoder() {
        PasswordEncoder encoder = SpringSecurity.passwordEncoder();
        assertNotNull(encoder);
        assertTrue(encoder.matches("password", encoder.encode("password")));
    }

}
