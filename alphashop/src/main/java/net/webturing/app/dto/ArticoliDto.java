package net.webturing.app.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticoliDto {
	
	private String codart;
	private String descrizione;
	private String um;
	private String codStat;
	private Integer pzcart;
	private Double pesonetto;
	
	private String idstatoart;
	
	private Date datacreazione;
	private Double prezzo = 0.0;
	
	private Set<BarcodeDto> barcode = new HashSet<>();
	
	private IngredientiDto ingredienti;
	
	private IvaDto iva;
	
	private CategoriaDto famAssort;
	
	
}
