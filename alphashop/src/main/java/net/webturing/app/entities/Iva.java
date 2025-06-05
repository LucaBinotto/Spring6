package net.webturing.app.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="IVA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Iva {
	
	@Id
	@Column(name="idiva")
	private Integer idIva;
	@Column(name="descrizione")
	private String descrizione;
	@Column(name="aliquota")
	private Integer aliquota;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "iva")
	private Set<Articoli> articoli = new HashSet<>();
	
}
