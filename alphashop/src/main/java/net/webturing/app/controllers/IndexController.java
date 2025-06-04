package net.webturing.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
	
	private String saluti = "Saluti, dall'applicazione in Spring 6";
	
	@GetMapping(value="/")
	public String getWelcome(Model model) {
		model.addAttribute("intestazione", "Benvenuti nella root page della webapp AlphaShop");
		model.addAttribute("saluti", saluti);
		
		return "index";
	}
	
	@RequestMapping(value="index")
	public String getWelcome2(Model model, @RequestParam(name="name", required=false, defaultValue="anonimo") String name)
	{
		model.addAttribute("intestazione", "Benvenuto "+name+" nella index page della webapp AlphaShop");
		model.addAttribute("saluti", saluti);
		
		return "index";
	}
}
