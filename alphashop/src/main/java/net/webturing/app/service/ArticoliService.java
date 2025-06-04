package net.webturing.app.service;

import java.util.List;

import net.webturing.app.entities.Articolo;

public interface ArticoliService {

	public List<Articolo> selAll();
	
	public Articolo selByCodArt(String codart);
	
	public List<Articolo> selByDescrizione(String filter, int page, int numrec);
	
	public Articolo selByBarcode(String barcode);
}
