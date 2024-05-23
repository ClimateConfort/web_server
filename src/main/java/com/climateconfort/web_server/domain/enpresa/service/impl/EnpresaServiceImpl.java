package com.climateconfort.web_server.domain.enpresa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climateconfort.web_server.domain.enpresa.dto.EnpresaDto;
import com.climateconfort.web_server.domain.enpresa.model.Enpresa;
import com.climateconfort.web_server.domain.enpresa.repository.EnpresaRespository;
import com.climateconfort.web_server.domain.enpresa.service.EnpresaService;
import com.climateconfort.web_server.domain.eraikina.dto.EraikinaDto;
import com.climateconfort.web_server.domain.eraikina.model.Eraikina;

@Service
public class EnpresaServiceImpl implements EnpresaService 
{    
    @Autowired
    EnpresaRespository enpresaRespository;

    @Override
    public List<EnpresaDto> findAllEnpresas()
    {
        Iterable<Enpresa> enpresas = enpresaRespository.findAll();
        return StreamSupport
                .stream(enpresas.spliterator(), false)
                .map(enpresa -> {
                    EnpresaDto enpresaDto = new EnpresaDto();
                    enpresaDto.setIzena(enpresa.getIzena());
                    enpresaDto.setEnpresaID(enpresa.getEnpresaID());
                    return enpresaDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EnpresaDto> findEnpresaDtoByName(String izena) 
    {
        return enpresaRespository
                .findByIzena(izena)
                .map(enpresa -> {
                    EnpresaDto enpresaDto = new EnpresaDto();
                    enpresaDto.setIzena(enpresa.getIzena());
                    enpresaDto.setEnpresaID(enpresa.getEnpresaID());
                    return enpresaDto;
                });
    }

    @Override
    public void saveEnpresa(EnpresaDto enpresaDto) 
    {
        Enpresa enpresa = new Enpresa();
        enpresa.setIzena(enpresaDto.getIzena());
        enpresaRespository.save(enpresa);
    }

    // @Override
    // public Optional<List<EraikinaDto>> findEreaikinakByEnpresaID(Long enpresaID) {
        
    //     List<EraikinaDto> eraikin_guztiak = new ArrayList<>();

    //     for (Eraikina eraikina : enpresaRespository.findById(enpresaID).orElse(null).getEraikinak())
    //     {
    //         EraikinaDto eraikinaDto = new EraikinaDto();
    //         eraikinaDto.setEraikinaID(eraikina.getEraikinaID());
    //         eraikinaDto.setIzena(eraikina.getIzena());
    //         eraikinaDto.setLokalizazioa(eraikina.getLokalizazioa());
    //         eraikin_guztiak.add(eraikinaDto);
    //     }
    //     throw new UnsupportedOperationException("Unimplemented method 'findEreaikinakByEnpresaID'");
    // }

    public Optional<List<EraikinaDto>> findEraikinakByEnpresaID(Long enpresaID) {
        return enpresaRespository.findById(enpresaID)
            .map(enpresa -> enpresa.getEraikinak().stream()
                .map(eraikina -> {
                    EraikinaDto eraikinaDto = new EraikinaDto();
                    eraikinaDto.setEraikinaID(eraikina.getEraikinaID());
                    eraikinaDto.setIzena(eraikina.getIzena());
                    eraikinaDto.setLokalizazioa(eraikina.getLokalizazioa());
                    return eraikinaDto;
                })
                .collect(Collectors.toList())
            );
    }
    

}
