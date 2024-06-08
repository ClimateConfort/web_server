package com.climateconfort.web_server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.climateconfort.web_server.controller.LoginController;
import com.climateconfort.web_server.domain.enpresa.dto.EnpresaDto;
import com.climateconfort.web_server.domain.enpresa.model.Enpresa;
import com.climateconfort.web_server.domain.enpresa.service.EnpresaService;
import com.climateconfort.web_server.domain.eraikina.service.EraikinaService;
import com.climateconfort.web_server.domain.user.dto.UserDto;
import com.climateconfort.web_server.domain.user.model.User;
import com.climateconfort.web_server.domain.user.service.UserService;

class RegisterTest {

    @Mock
    UserService mockedUserService;
    @Mock
    EnpresaService mockedEnpresaService;
    @Mock
    EraikinaService mockedEraikinaService;

    @InjectMocks
    private LoginController loginController;

    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        model = new ExtendedModelMap();
    }

    @Test
    void testEnpresaRegistration() {
        UserDto userDto = new UserDto(1L, "email", "pass", null, "enpresa", "eraikina", "lokalizazio", "gela");

        User user = new User();
        user.setEmail("email");
        Enpresa enpresa = new Enpresa();
        enpresa.setIzena("enpresa");
        EnpresaDto enpresaDto = new EnpresaDto();

        doNothing().when(mockedUserService).saveUser(any());
        doNothing().when(mockedEnpresaService).saveEnpresa(any());
        doNothing().when(mockedEraikinaService).saveEraikina(any());
        when(mockedEnpresaService.findEnpresaByIzena(anyString())).thenReturn(enpresa);
        when(mockedEnpresaService.findAllEnpresas()).thenReturn(Collections.singletonList(enpresaDto));
        when(mockedUserService.findUserByEmail(anyString())).thenReturn(Optional.of(user));
        String result = loginController.enpresaRegistration(userDto, null, model, null);
        assertEquals("menuAdmin", result);
    }
}
