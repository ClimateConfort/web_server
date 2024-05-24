package com.climateconfort.web_server.domain.user.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.climateconfort.web_server.domain.role.model.Role;
import com.climateconfort.web_server.domain.role.repository.RoleRepository;
import com.climateconfort.web_server.domain.user.dto.UserDto;
import com.climateconfort.web_server.domain.user.model.User;
import com.climateconfort.web_server.domain.user.repository.UserRepository;
import com.climateconfort.web_server.domain.user.service.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder; 		

	@Override
	public void saveUser(UserDto userDto) {
		User user = new User();
		// user.setIzena(userDto.getName());
		// user.setAbizena(userDto.getSurname());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRole(roleRepository.findByType(userDto.getRoleType()).orElseGet(() -> {
			Role newRole = new Role();
			newRole.setType(userDto.getRoleType());
			return roleRepository.save(newRole);
		}));
		userRepository.save(user);
	}

	@Override
	public Optional<UserDto> findUserDtoByEmail(String email) {
		return userRepository
				.findByEmail(email)
				.map(user -> {
					UserDto userDto = new UserDto();
					// userDto.setName(user.getIzena());
					// userDto.setSurname(user.getAbizena());
					userDto.setEmail(user.getEmail());
					userDto.setPassword(user.getPassword());
					userDto.setRoleType(user.getRole().getType());
					userDto.setId(user.getUserID());
					return userDto;
				});
	}

	@Override
	public List<UserDto> findAllUsers() {
		Iterable<User> users = userRepository.findAll();
		return StreamSupport
				.stream(users.spliterator(), false)
				.map(user -> {
					UserDto userDto = new UserDto();
					// userDto.setName(user.getIzena());
					// userDto.setSurname(user.getAbizena());
					userDto.setEmail(user.getEmail());
					userDto.setRoleType(user.getRole().getType());
					return userDto;
				})
				.collect(Collectors.toList());
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}