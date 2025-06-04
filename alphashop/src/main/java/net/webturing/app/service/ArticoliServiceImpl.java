package net.webturing.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.webturing.app.entities.Articolo;
import net.webturing.app.repository.ArticoliRepository;

@Service
public class ArticoliServiceImpl implements ArticoliService{
	
	@Autowired
	ArticoliRepository artRepo;

	@Override
	public List<Articolo> selAll() {
		
		return artRepo.findAll();
	}

	@Override
	public Articolo selByCodArt(String codart) {
		
		return artRepo.findByCodArt(codart);
	}

	@Override
	public List<Articolo> selByDescrizione(String filter, int page, int numrec) {
		
		filter = "%".concat(filter.toUpperCase().concat("%"));
		Pageable pageAndRecords = PageRequest.of(page, numrec);
		return artRepo.findByDescrizioneLike(filter, pageAndRecords);
	}

	@Override
	public Articolo selByBarcode(String barcode) {
		return artRepo.selByEan(barcode);
	}

}
