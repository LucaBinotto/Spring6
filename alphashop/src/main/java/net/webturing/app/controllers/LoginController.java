package net.webturing.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	@Autowired
	private AuthenticationService authService;
	
	/*
	public LoginController(AuthenticationService authService) {
		this.authService = authService;
	}
	*/
	
	private String titolo ="Accesso & Autenticazione";
	private String sottoTitolo ="Procedi a inserire nome utente e password";
	private String errMsg ="Nome utente o password non riconosciuti";
	
	
	@GetMapping(value="/login")
	public String getLogin(ModelMap model) {
		
		model.put("titolo", titolo);
		model.put("sottoTitolo", sottoTitolo);
		model.put("errMsg", errMsg);
		return "login";
	}
	
	@PostMapping(value="/login")
	public String goToWelcomePage(@RequestParam("name") String name, @RequestParam("password") String password, ModelMap model) {
		if(authService.auth(name, password)) {
			model.put("name", name);
			return "welcome";
		}else {
			return "redirect:/login?error=noLogged";
		}
	}
	
}
