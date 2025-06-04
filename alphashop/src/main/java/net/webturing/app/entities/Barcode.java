package net.webturing.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="BARCODE")
public class Barcode {

	@Id
	@Column(name="barcode")
	private String barcode;
	@Column(name="idtipoart")
	private String idTipoArt;
	
	
	@ManyToOne
	@JoinColumn(name="codart", referencedColumnName = "codArt")
	private Articolo articolo;
	
}
