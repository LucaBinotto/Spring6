package net.webturing.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import net.webturing.app.entities.Articolo;
import net.webturing.app.service.ArticoliService;

@Controller
public class ArticoliController {
	
	@Autowired
	private ArticoliService artService;
	
	@GetMapping("/articoli")
	public String getArticoli(ModelMap model) {
		List<Articolo> articoli = artService.SelAll();
		
		model.addAttribute("articoli", articoli);
		
		return "articoli";
	}
}
