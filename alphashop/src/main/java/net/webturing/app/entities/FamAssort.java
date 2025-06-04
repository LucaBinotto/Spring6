package net.webturing.app.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="FAMASSORT")
public class FamAssort {
	
	@Id
	@Column(name="id")
	private String id;
	@Column(name="descrizione")
	private String descrizione;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "famAssort")
	private Set<Articolo> articoli = new HashSet<>();
}
