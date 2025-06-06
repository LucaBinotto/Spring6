package net.webturing.app.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.webturing.app.dto.ArticoliDto;
import net.webturing.app.dto.BarcodeDto;
import net.webturing.app.dto.CategoriaDto;
import net.webturing.app.dto.IngredientiDto;
import net.webturing.app.dto.IvaDto;
import net.webturing.app.entities.Articoli;
import net.webturing.app.entities.Barcode;
import net.webturing.app.entities.FamAssort;
import net.webturing.app.entities.Ingredienti;
import net.webturing.app.entities.Iva;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.addMappings(articoliMapping);
		modelMapper.addMappings(ivaMapping);
		modelMapper.addMappings(categoriaMapping);
		modelMapper.addMappings(ingredientiMapping);
		modelMapper.addMappings(new PropertyMap<Barcode, BarcodeDto>() {
			@Override
			protected void configure() {
				map().setIdtipoart(source.getIdTipoArt());
				map().setBarcode(source.getBarcode());
			}
		});
		modelMapper.addConverter(articoliConverter);
		
		return modelMapper;
	}
	
	PropertyMap<Iva, IvaDto> ivaMapping = new PropertyMap<Iva, IvaDto>() {
		
		@Override
		protected void configure() {
			map().setAliquota(source.getAliquota());
			map().setDescrizione(source.getDescrizione());
			//map().getIdiva(source.getIdIva());
		}
	};
	
PropertyMap<Articoli, ArticoliDto> articoliMapping = new PropertyMap<Articoli, ArticoliDto>() {
		
		@Override
		protected void configure() {
			map().setCodart(source.getCodArt());
			map().setDescrizione(source.getDescrizione());
			map().setUm(source.getUm());
			map().setCodStat(source.getCodStat());
			map().setPzcart(source.getPzCart());
			map().setPesonetto(source.getPesoNetto());
			map().setIdstatoart(source.getIdStatoArt());
			map().setDatacreazione(source.getDataCreazione());
		}
	};
	
PropertyMap<FamAssort, CategoriaDto> categoriaMapping = new PropertyMap<FamAssort, CategoriaDto>() {
		
		@Override
		protected void configure() {
			map().setId(source.getId());
			map().setDescrizione(source.getDescrizione());
		}
	};
	
PropertyMap<Ingredienti, IngredientiDto> ingredientiMapping = new PropertyMap<Ingredienti, IngredientiDto>() {
		
		@Override
		protected void configure() {
			map().setCodart(source.getCodArt());
			map().setInfo(source.getInfo());
		}
	};
	
	
	
	Converter<String, String> articoliConverter = new Converter<String, String>() {
		
		@Override
		public String convert(MappingContext<String, String> context) {
			
			return context.getSource() == null ? "" : context.getSource().trim();
		}
	};
}
