package net.webturing.app.service;

import java.util.List;

import net.webturing.app.dto.ArticoliDto;

public interface ArticoliService {

	public List<ArticoliDto> selAll();
	
	public ArticoliDto selByCodArt(String codart);
	
	public List<ArticoliDto> selByDescrizione(String filter, int page, int numrec);
	
	public ArticoliDto selByBarcode(String barcode);
}
