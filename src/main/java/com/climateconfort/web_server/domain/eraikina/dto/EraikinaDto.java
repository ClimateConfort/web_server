package com.climateconfort.web_server.domain.eraikina.dto;

import com.climateconfort.web_server.domain.enpresa.model.Enpresa;
import com.climateconfort.web_server.domain.gela.model.Gela;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EraikinaDto {

	private Long eraikinaID;

    @NotEmpty
	private String izena;
 
    @NotEmpty
	private String lokalizazioa;

	private Enpresa enpresa;

	private Gela gela;

}
