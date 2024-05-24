package com.climateconfort.web_server.domain.gela.model;

import com.climateconfort.web_server.domain.eraikina.model.Eraikina;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Gela")
@Table(name = "gela")
public class Gela {
    
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gelaID;
 
	@Column(nullable = false)
	private String izena;

	@Column(nullable = true)
	private double paramMin;

	@Column(nullable = true)
	private double paramMax;

    @ManyToOne
    private Eraikina eraikina;

}