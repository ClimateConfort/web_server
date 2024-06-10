package com.climateconfort.web_server.domain.enpresa.service;

import java.util.List;
import java.util.Optional;

import com.climateconfort.web_server.domain.enpresa.dto.EnpresaDto;
import com.climateconfort.web_server.domain.enpresa.model.Enpresa;
import com.climateconfort.web_server.domain.eraikina.dto.EraikinaDto;

public interface EnpresaService 
{
    void saveEnpresa(EnpresaDto enpresaDto);
    void deleteEnpresa(Long id);
    Enpresa findEnpresaByID(Long id);
    Enpresa findEnpresaByIzena(String izena);
    List<EnpresaDto> findAllEnpresas();
    Optional<EnpresaDto> findEnpresaDtoByName(String izena);
    Optional<List<EraikinaDto>> findEraikinakByEnpresaID(Long enpresaID);
}