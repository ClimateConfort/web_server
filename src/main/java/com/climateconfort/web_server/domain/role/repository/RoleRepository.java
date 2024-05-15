package com.climateconfort.web_server.domain.role.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.climateconfort.web_server.domain.role.model.Role;
import com.climateconfort.web_server.domain.role.model.Role.RoleType;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Optional<Role> findByType(RoleType type);
}