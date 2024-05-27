package com.climateconfort.web_server.domain.gela.reporitory;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.climateconfort.web_server.domain.gela.model.Gela;

public interface GelaRepository extends CrudRepository<Gela, Long> 
{
    Optional<Gela> findByGelaID(Long gelaID);
}
