package net.webturing.app.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="IVA")
public class Iva {
	
	@Id
	@Column(name="idiva")
	private Integer idIva;
	@Column(name="descrizione")
	private String descrizione;
	@Column(name="aliquota")
	private Integer aliquota;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "iva")
	private Set<Articolo> articoli = new HashSet<>();
	
}
