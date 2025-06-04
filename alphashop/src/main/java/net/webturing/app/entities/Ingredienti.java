package net.webturing.app.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="INGREDIENTI")
public class Ingredienti {
	
	@Id
	@Column(name="codart")
	@OneToOne
	private String codArt;
	@Column(name="info")
	private String info;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Articolo articolo;

}
