package com.climateconfort.web_server.domain.enpresa.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.climateconfort.web_server.domain.enpresa.model.Enpresa;

public interface EnpresaRespository extends CrudRepository<Enpresa, Long> 
{
      Optional<Enpresa> findByIzena(String izena);
}
