package com.climateconfort.web_server.domain.eraikina.service;

import java.util.List;
import java.util.Optional;

import com.climateconfort.web_server.domain.eraikina.dto.EraikinaDto;
import com.climateconfort.web_server.domain.eraikina.model.Eraikina;
import com.climateconfort.web_server.domain.gela.dto.GelaDto;

public interface EraikinaService 
{
    Optional<List<GelaDto>>findGelakByEraikinaID(Long eraikinaID);
    void saveEraikina(EraikinaDto eraikinaDto);
    Eraikina findEraikinaByIzena(String izena);
}