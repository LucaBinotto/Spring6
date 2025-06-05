package net.webturing.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;
import net.webturing.app.entities.Articoli;
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
	
	
	@GetMapping
	public String getArticoli(ModelMap model) {
		List<Articoli> articoli = artService.SelAll()
				.stream().limit(5).collect(Collectors.toList());
		
		model.addAttribute("articoli", articoli);
		
		return "articoli";
	}
}
