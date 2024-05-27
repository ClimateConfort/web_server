package com.climateconfort.web_server.domain.eraikina.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climateconfort.web_server.domain.eraikina.dto.EraikinaDto;
import com.climateconfort.web_server.domain.eraikina.model.Eraikina;
import com.climateconfort.web_server.domain.eraikina.reporitory.EraikinaRepository;
import com.climateconfort.web_server.domain.eraikina.service.EraikinaService;
import com.climateconfort.web_server.domain.gela.dto.GelaDto;

@Service
public class EraikinaServiceImpl implements EraikinaService 
{    
    @Autowired
    EraikinaRepository eraikinaRespository;

    @Override
    public Optional<List<GelaDto>> findGelakByEraikinaID(Long eraikinaID) 
    {
        return eraikinaRespository.findById(eraikinaID)
            .map(eraikina -> eraikina.getGelak().stream()
                .map(gela -> {
                    GelaDto gelaDto = new GelaDto();
                    gelaDto.setGelaID(gela.getGelaID());
                    gelaDto.setIzena(gela.getIzena());
                    gelaDto.setParamMax(gela.getParamMax());
                    gelaDto.setParamMin(gela.getParamMin());
                    return gelaDto;
                }).collect(Collectors.toList())            
            );
    }

    @Override
    public void saveEraikina(EraikinaDto eraikinaDto) 
    {
        Eraikina eraikina = new Eraikina();
        eraikina.setEnpresa(eraikinaDto.getEnpresa());
        eraikina.setIzena(eraikinaDto.getIzena());
        eraikina.setLokalizazioa(eraikinaDto.getLokalizazioa());
        eraikinaRespository.save(eraikina);
    }

    @Override
    public Eraikina findEraikinaByIzena(String izena) {
       return eraikinaRespository.findByizena(izena).get();
    }
    
}
