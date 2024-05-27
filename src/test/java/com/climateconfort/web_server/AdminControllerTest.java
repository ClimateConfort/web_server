package com.climateconfort.web_server;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.climateconfort.web_server.controller.AdminController;
import com.climateconfort.web_server.domain.enpresa.service.EnpresaService;

public class AdminControllerTest 
{
    @InjectMocks
    AdminController adminController;
    
    @Mock
    private EnpresaService enpresaService;
    
}
