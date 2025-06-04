package net.webturing.app.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="ARTICOLI")
public class Articolo {
	
	@Id
	@Column(name ="codart")
	private String codArt;
	@Column(name ="descrizione")
	private String descrizione;
	@Column(name ="um")
	private String um;
	@Column(name ="codstat")
	private String codStat;
	@Column(name ="pzcart")
	private Integer pzCart;
	@Column(name ="pesonetto")
	private double pesoNetto;
	@Column(name ="prezzo")
	private double prezzo;
	@Column(name ="idstatoart")
	private String idStatoArt;
	
	@SuppressWarnings("deprecation")
	@Temporal(TemporalType.DATE)
	@Column(name ="datacreazione")
	private Date dataCreazione;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "articolo", orphanRemoval = true)
	private Set<Barcode> barcode = new HashSet<>();
	
}
