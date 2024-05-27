package com.climateconfort.web_server.domain.eraikina.reporitory;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.climateconfort.web_server.domain.eraikina.model.Eraikina;

public interface EraikinaRepository extends CrudRepository<Eraikina, Long> 
{
    Optional<Eraikina> findByEraikinaID(Long eraikinaID);
    Optional<Eraikina> findByizena(String izena);
}
