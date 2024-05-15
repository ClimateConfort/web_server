package com.climateconfort.web_server.domain.sentsorea.model;

import com.climateconfort.web_server.domain.gela.model.Gela;

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
@Entity(name = "Sentsorea")
@Table(name = "sentsorea")
public class Sentsorea {
    
    @SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sentsoreID;
 
	@Column(nullable = false)
	private String izena;

    @ManyToOne
    private Gela gela;

}
