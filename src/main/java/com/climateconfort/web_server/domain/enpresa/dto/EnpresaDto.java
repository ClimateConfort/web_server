package com.climateconfort.web_server.domain.enpresa.dto;

import com.climateconfort.web_server.domain.user.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnpresaDto {
    
	private Long enpresaID;

    private String izena;

    private User user;

}
