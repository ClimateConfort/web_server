package com.climateconfort.web_server.domain.user.service;

import java.util.List;
import java.util.Optional;

import com.climateconfort.web_server.domain.user.dto.UserDto;
import com.climateconfort.web_server.domain.user.model.User;


public interface UserService 
{
	void saveUser(UserDto userDto);
	void deleteUser(Long id);
	Optional<UserDto> findUserDtoByEmail(String email);
	Optional<User> findUserByEmail(String email);
	List<UserDto> findAllUsers();
}