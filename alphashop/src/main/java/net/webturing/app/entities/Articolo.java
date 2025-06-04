package net.webturing.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Articolo {

	private String codArt;
	private String descrizione;
	private String um;
	private int pzCart;
	private double pesoNetto;
	private double prezzo;
	
}
