package net.webturing.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="FAMASSORT")
public class FamAssort {
	
	@Id
	@Column(name="id")
	private String id;
	@Column(name="descrizione")
	private String descrizione;
}
