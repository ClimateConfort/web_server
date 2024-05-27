package com.climateconfort.web_server.domain.gela.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climateconfort.web_server.domain.gela.dto.GelaDto;
import com.climateconfort.web_server.domain.gela.model.Gela;
import com.climateconfort.web_server.domain.gela.reporitory.GelaRepository;
import com.climateconfort.web_server.domain.gela.service.GelaService;

@Service
public class GelaServiceImpl implements GelaService 
{    
    @Autowired
    GelaRepository gelaRespository;

    @Override
    public GelaDto fingGelaByGelaID(Long gelaID) 
    {
        Gela gela = gelaRespository.findById(gelaID).get();
        GelaDto gelaDto = new GelaDto();
        gelaDto.setIzena(gela.getIzena());
        gelaDto.setGelaID(gela.getGelaID());
        gelaDto.setParamMax(gela.getParamMax());
        gelaDto.setParamMin(gela.getParamMin());
        gelaDto.setEraikina(gela.getEraikina());
        return gelaDto;    
    }
    
}
