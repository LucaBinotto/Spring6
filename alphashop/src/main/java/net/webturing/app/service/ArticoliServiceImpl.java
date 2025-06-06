package net.webturing.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.webturing.app.dto.ArticoliDto;
import net.webturing.app.entities.Articoli;
import net.webturing.app.repository.ArticoliRepository;

@Service
public class ArticoliServiceImpl implements ArticoliService{
	
	@Autowired
	ArticoliRepository artRepo;
	@Autowired
	ModelMapper modelMap;

	@Override
	public List<ArticoliDto> selAll() {
		List<Articoli> articoli = artRepo.findAll();
		List<ArticoliDto> artDto = new ArrayList<>();
		for (Articoli art: articoli) {
			artDto.add(convertToDto(art));
		}
		return artDto;
	}

	@Override
	public ArticoliDto selByCodArt(String codart) {
		
		return convertToDto(artRepo.findByCodArt(codart));
	}

	@Override
	public List<ArticoliDto> selByDescrizione(String filter, int page, int numrec) {
		
		filter = "%".concat(filter.toUpperCase().concat("%"));
		Pageable pageAndRecords = PageRequest.of(page, numrec);
		/*
		List<Articoli> articoli = artRepo.findByDescrizioneLike(filter, pageAndRecords);
		List<ArticoliDto> artDto = new ArrayList<>();
		for (Articoli art: articoli) {
			artDto.add(convertToDto(art));
		}
		*/
		return convertToDto(artRepo.findByDescrizioneLike(filter, pageAndRecords));
	}

	@Override
	public ArticoliDto selByBarcode(String barcode) {
		return convertToDto(artRepo.selByEan(barcode));
	}
	
	private ArticoliDto convertToDto(Articoli articolo) {
		ArticoliDto artDto = null;
		if(articolo != null) {
			artDto = modelMap.map(articolo, ArticoliDto.class);
		}
		
		return artDto;
	}
	
	private List<ArticoliDto> convertToDto(List<Articoli> articoli) {
		List<ArticoliDto> artDto = articoli
				.stream()
				.map(source -> modelMap.map(source, ArticoliDto.class))
				.collect(Collectors.toList());
		
		return artDto;
	}

}
