package com.climateconfort.web_server.domain.eraikina.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EraikinaDto {

	private Long eraikinaID;

    @NotEmpty
	private String izena;
 
    @NotEmpty
	private String lokalizazioa;
}
