package net.webturing.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.java.Log;
import net.webturing.app.dto.ArticoliDto;
import net.webturing.app.service.ArticoliService;

@Log
@Controller
@RequestMapping("/articoli")
public class ArticoliController {

	// private ArticoliService artService;

	// @Autowired
	private ArticoliService artService;

	public ArticoliController(ArticoliService artService) {
		this.artService = artService;
	}

	@GetMapping()
	public String getGestioneArticoli() {
		return "articoli";
	}

	@GetMapping(value = "/cerca/all")
	public String getArticoli(ModelMap model) {
		List<ArticoliDto> articoli = artService.selAll().stream().limit(5).collect(Collectors.toList());

		model.addAttribute("articoli", articoli);

		return "articoli";
	}

	@GetMapping(value = "/cerca/descrizione/{filter}")
	public String getArticoli(@PathVariable("filter") String filter, ModelMap model) {
		List<ArticoliDto> articoli = artService.selByDescrizione(filter, 0, 10);

		model.addAttribute("articoli", articoli);
		return "articoli";
	}

	@GetMapping(value = "/search")
	public String searchArticoli(@RequestParam(name = "filtro") String filtro,
			@RequestParam(name="selected", required = false, defaultValue = "10") String selected, ModelMap model) {

		log.info(String.format("Ricerca articoli con filtro %s", filtro));
		int pageNum = 0;
		int recForPage = Integer.parseInt(selected);
		int numArt = 0;
		boolean notFound = false;

		ArticoliDto articolo = null;
		List<ArticoliDto> articoli = new ArrayList<ArticoliDto>();

		articolo = artService.selByCodArt(filtro);
		if (articolo == null) {

			articolo = artService.selByBarcode(filtro);
			if (articolo == null) {
				articoli = artService.selByDescrizione(filtro, pageNum, recForPage);
				numArt = artService.numRecords(filtro);
			} else {
				numArt = 1;
				articoli.add(articolo);
			}
		} else {
			numArt = 1;
			articoli.add(articolo);
		}
		
		log.info(String.format("Trovati %s articoli", numArt));
		
		if (articoli.isEmpty()) {
			notFound = true;
		}

		model.addAttribute("articoli", articoli);
		model.addAttribute("filtro", filtro);
		model.addAttribute("notFound", notFound);
		model.addAttribute("numArt", numArt);
		return "articoli";
	}
	
	
}
