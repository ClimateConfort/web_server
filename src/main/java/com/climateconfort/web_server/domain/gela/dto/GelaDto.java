package com.climateconfort.web_server.domain.gela.dto;

import com.climateconfort.web_server.domain.eraikina.model.Eraikina;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GelaDto {

	private Long gelaID;
 
	private String izena;

	private double paramMin;

	private double paramMax;

    private Eraikina eraikina;

}
