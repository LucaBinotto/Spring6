package net.webturing.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.webturing.app.entities.Articolo;

@Service
public class ArticoliServiceImpl implements ArticoliService{
	
	private static List<Articolo> getArticoli(){
		List<Articolo> articoli = new ArrayList<>();
		
		articoli.add(new Articolo("014600301","BARILLA FARINA 1 KG","PZ",24,1,1.09));
		articoli.add(new Articolo("013500121","BARILLA PASTA GR.500 N.70 1/2 PENNE","PZ",30,0.5,1.39));
		articoli.add(new Articolo("007686402","FINDUS FIOR DI NASELLO 300 GR","PZ",8,0.3,4.59));
		articoli.add(new Articolo("057549001","FINDUS CROCCOLE 400 GR","PZ",12,0.4,3.99));
		
		return articoli;
	}
	
	@Override
	public List<Articolo> SelAll() {
		
		return getArticoli();
	}

}
