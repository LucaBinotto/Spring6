package net.webturing.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="INGREDIENTI")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredienti {
	
	@Id
	@Column(name="codart")
	private String codArt;
	@Column(name="info", length = 300)
	private String info;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Articoli articolo;

}
