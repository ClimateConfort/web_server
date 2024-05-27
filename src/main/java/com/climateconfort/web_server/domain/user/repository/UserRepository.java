package com.climateconfort.web_server.domain.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.climateconfort.web_server.domain.user.model.User;


public interface UserRepository extends CrudRepository<User, Long> {
	
    Optional<User> findByEmail(String email);

}