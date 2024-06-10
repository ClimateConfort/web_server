package com.climateconfort.web_server.domain.enpresa.model;

import java.util.List;

import com.climateconfort.web_server.domain.eraikina.model.Eraikina;
import com.climateconfort.web_server.domain.user.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Enpresa")
@Table(name = "enpresa")
public class Enpresa {
    
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long enpresaID;
 
	@Column(nullable = false)
	private String izena;

	@OneToMany(mappedBy = "enpresa", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Eraikina> eraikinak;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private User user;

}
