package com.climateconfort.web_server.domain.enpresa.service.impl;

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
import com.climateconfort.web_server.domain.user.repository.UserRepository;

@Service
public class EnpresaServiceImpl implements EnpresaService 
{    

    @Autowired
    UserRepository userRepository;

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
                    enpresaDto.setUser(enpresa.getUser());
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
        enpresa.setUser(enpresaDto.getUser());
        enpresa.setIzena(enpresaDto.getIzena());
        enpresaRespository.save(enpresa);
    }

    @Override
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

    @Override
    public void deleteEnpresa(Long id) {
        enpresaRespository.delete(enpresaRespository.findById(id).get());
    }

    @Override
    public Enpresa findEnpresaByIzena(String izena) {
        return enpresaRespository.findByIzena(izena).get();
    }

    @Override
    public Enpresa findEnpresaByID(Long id) {
        return enpresaRespository.findById(id).get();
    }

}
