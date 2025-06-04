package net.webturing.app.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.webturing.app.entities.Articolo;

public interface ArticoliRepository extends JpaRepository<Articolo, String>{
	
	//query method
	Articolo findByCodArt(String codArt);
	
	//query method
	List<Articolo> findByDescrizioneLike(String descrizione, Pageable pageable);
	
	//JPQL
	@Query(value = "SELECT a FROM Articoli a JOIN a.barcode b WHERE b.barcode IN (:ean)")
	Articolo selByEan(@Param("ean") String ean);
	
	//SQL standard
	@Query(value = "SELECT COUNT(*) FROM ARTICOLI WHERE DESCRIZIONE LIKE :desArt", nativeQuery = true)
	int countRecords(@Param("desArt") String descrizione);
	
	//query method
	List<Articolo> findByCodStatOrderByDescrizione(String codStat);
}
