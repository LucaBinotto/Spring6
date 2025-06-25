package net.webturing.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.java.Log;
import net.webturing.app.dto.ArticoliDto;
import net.webturing.app.dto.PagingData;
import net.webturing.app.service.ArticoliService;

@Log
@Controller
@RequestMapping("/articoli")
public class ArticoliController {

	// private ArticoliService artService;

	// @Autowired
	private ArticoliService artService;

	List<PagingData> pages = new ArrayList<PagingData>();
	
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
	public String getArticoli(@PathVariable("filter") String filter,
			@MatrixVariable(name="page", defaultValue="0")String page,
			@MatrixVariable(name="record")String record,
			ModelMap model) {
		
		log.info(String.format("page:  %s", page));
		log.info(String.format("record:  %s", record));
		
		int pageNum = Integer.parseInt(page);
		int recForPage = Integer.parseInt(record);
		
		List<ArticoliDto> articoli = artService.selByDescrizione(filter, pageNum, recForPage);

		model.addAttribute("articoli", articoli);
		return "articoli";
	}

	@GetMapping(value = "/search")
	public String searchArticoli(@RequestParam(name = "filtro") String filtro,
			@RequestParam(name="selected", required = false, defaultValue = "10") String selected,  ModelMap model) {

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
		
		setPages(pageNum, numArt);
		
		model.addAttribute("articoli", articoli);
		model.addAttribute("filtro", filtro);
		model.addAttribute("notFound", notFound);
		model.addAttribute("numArt", numArt);
		return "articoli";
	}
	
	
	// articoli/cerca/parametri;paging=0,10;exFilter=1,15?filter=Barilla
	@GetMapping(value="/cerca/{parametri}")
	public String getArticoliWithPar(
			@MatrixVariable(pathVar="parametri") Map<String, List<String>> parametri,
			@RequestParam("filter") String filter,
			ModelMap model) {
		
		int numArt = 0;
		int pageNum = 0;
		int recForPage = 10;
		
		
		//Parametri paging
		List<String> paramPaging = parametri.get("paging");
		if(paramPaging != null) {
			try {
				pageNum = Integer.parseInt(paramPaging.get(0));
				recForPage = Integer.parseInt(paramPaging.get(1));
			}catch (NumberFormatException e) {
				pageNum = 0;
				recForPage = 0;
			}
		}
		
		log.info(String.format("pagina:  %s, records: %s", pageNum, recForPage));
		
		//parametri filtri aggiuntivi
		List<String> exFilter = parametri.get("exFilter");
		if(exFilter != null) {
			try {
				log.info(String.format("status:  %s", exFilter.get(0)));
				log.info(String.format("categoria:  %s", exFilter.get(1)));
			}catch (Exception e) {
				
			}
		}
		
		log.info(String.format("Cerco articoli con descrizione  %s", filter));
		
		List<ArticoliDto> articoli = artService.selByDescrizione(filter, pageNum, recForPage);
		
		numArt = artService.numRecords(filter);
		model.addAttribute("articoli", articoli);
		
		return "articoli";
	}
	
	//Metodo di creazione classi Pages
		private void setPages(int page, long numRecords)
		{
			int RecForPage = 10;
			int Min = 1;
			int ValMin = 1;
			int Max = 5;
			
			page = (page == 0) ? 1 : page;
			
			if (pages != null)
				pages.clear();
			
			int Group = (int) Math.ceil((double)page / 5);
			
			Max = Group * 5;
			Min = (Max - 5 == 0) ? 1 : Max - 4;
			
			ValMin = Min;
			
			int MaxPages = (numRecords > 0) ? (int) Math.ceil((double)numRecords / (double)RecForPage) : 5;
			
			while (Min <= Max)
			{
				if (Min > MaxPages)
					break;
				
				pages.add(new PagingData(Min,false));
				
				Min++;
			}
			
			try
			{
				if (page - ValMin > 0)
					pages.get(page - ValMin).setIsSelected(true);
				else
					pages.get(0).setIsSelected(true);
			}
			catch (Exception ex)
			{
				pages.get(0).setIsSelected(true);
			}
		}
	
}
