package net.webturing.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.webturing.app.entities.Articoli;
import net.webturing.app.repository.ArticoliRepository;

@Service
public class ArticoliServiceImpl implements ArticoliService{
	
	@Autowired
	ArticoliRepository artRepo;

	@Override
	public List<Articoli> SelAll() {
		
		return artRepo.findAll();
	}

	@Override
	public Articoli SelByCodArt(String codart) {
		
		return artRepo.findByCodArt(codart);
	}

	@Override
	public List<Articoli> SelByDescrizione(String filter, int page, int numrec) {
		
		filter = "%".concat(filter.toUpperCase().concat("%"));
		Pageable pageAndRecords = PageRequest.of(page, numrec);
		return artRepo.findByDescrizioneLike(filter, pageAndRecords);
	}

	@Override
	public Articoli SelByBarcode(String barcode) {
		return artRepo.selByEan(barcode);
	}

}
