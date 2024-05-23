package com.climateconfort.web_server.domain.enpresa.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnpresaDto {
    
	private Long enpresaID;

    @NotEmpty
    private String izena;

}
