package net.webturing.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="BARCODE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Barcode {

	@Id
	@Column(name="barcode")
	private String barcode;
	@Column(name="idtipoart")
	private String idTipoArt;
	
	
	@ManyToOne
	@JoinColumn(name="codart", referencedColumnName = "codart")
	private Articoli articolo;
	
}
