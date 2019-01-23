package com.example.demo.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Fastighet {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	private String kommun;
	private String fastighetsnamn;
	
	@OneToMany(mappedBy="fastighet")
	private List<Geometri> geometrier;
	
	@OneToMany(mappedBy="fastighet")
	private List<Agande> agarna;
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getKommun() {
		return kommun;
	}
	public void setKommun(String kommun) {
		this.kommun = kommun;
	}
	
	public String getFastighetsnamn() {
		return fastighetsnamn;
	}
	public void setFastighetsnamn(String fastighetsnamn) {
		this.fastighetsnamn = fastighetsnamn;
		
	}
	public List<Geometri> getGeometrier() {
		return geometrier;
	}
	public void setGeometrier(List<Geometri> geometrier) {
		this.geometrier = geometrier;
	}
	public void addGeometri(Geometri geometri) {
		this.geometrier.add(geometri);
		if(geometri.getFastighet()!=this) {
			geometri.setFastighet(this);
		}
	
	}
	@Override
	public String toString() {
		return "Fastighet [id=" + id + ", kommun=" + kommun + ", fastighetsnamn=" + fastighetsnamn + ", geometrier="
				+ geometrier + "]";
	}
	public List<Agande> getAgarna() {
		return agarna;
	}
	public void setAgarna(List<Agande> agarna) {
		this.agarna = agarna;
	}
	public void addAgare(Agande agare) {
		this.agarna.add(agare);
		if(agare.getFastighet()!=this) {
			agare.setFastighet(this);
		}
	}
	
}
