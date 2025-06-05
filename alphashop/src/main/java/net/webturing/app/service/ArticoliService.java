package net.webturing.app.service;

import java.util.List;

import net.webturing.app.entities.Articoli;

public interface ArticoliService {

	public List<Articoli> SelAll();
	
	public Articoli SelByCodArt(String codart);
	
	public List<Articoli> SelByDescrizione(String filter, int page, int numrec);
	
	public Articoli SelByBarcode(String barcode);
}
