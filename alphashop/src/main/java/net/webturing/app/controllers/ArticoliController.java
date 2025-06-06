package net.webturing.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;
import net.webturing.app.dto.ArticoliDto;
import net.webturing.app.service.ArticoliService;

@Log
@Controller
@RequestMapping("/articoli")
public class ArticoliController {
	
	//private ArticoliService artService;
	
	//@Autowired
		private ArticoliService artService;
		
		public ArticoliController(ArticoliService artService)
		{
			this.artService = artService;
		}
	
	
	@GetMapping(value="/cerca/all")
	public String getArticoli(ModelMap model) {
		List<ArticoliDto> articoli = artService.selAll()
				.stream().limit(5).collect(Collectors.toList());
		
		model.addAttribute("articoli", articoli);
		
		return "articoli";
	}
	
	@GetMapping(value="/cerca/descrizione/{filter}")
	public String getArticoli(@PathVariable("filter") String filter, ModelMap model) {
		List<ArticoliDto> articoli = artService.selByDescrizione(filter, 0, 10);
		
		model.addAttribute("articoli", articoli);
		return "articoli";
	}
}
