package com.climateconfort.web_server.domain.user.dto;

import com.climateconfort.web_server.domain.role.model.Role.RoleType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private Long id;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String password;

	//@NotEmpty
	private RoleType roleType;

	/* Bariable hauek sorkuntza mocketuarentzat */
	private String enpresaIzena;
	private String eraikinaIzena;
	private String eraikinaLokalizazioa;
	private String gelaIzena;
}