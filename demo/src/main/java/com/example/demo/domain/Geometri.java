package com.example.demo.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Geometri {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	private String granslinje;
	private float yta;
	
	@OneToMany(mappedBy="geometri")
	private List<Fastighet> fastighets;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getGranslinje() {
		return granslinje;
	}
	public void setGranslinje(String granslinje) {
		this.granslinje = granslinje;
	}
	public float getYta() {
		return yta;
	}
	public void setYta(float yta) {
		this.yta = yta;
	}
	public List<Fastighet> getFastighets() {
		return fastighets;
	}
	public void setFastighets(List<Fastighet> fastighets) {
		this.fastighets = fastighets;
	}
	public void addFastighet(Fastighet fastighet) {
		this.fastighets.add(fastighet);
		if(fastighet.getGeometri()!=this) {
			fastighet.setGeometri(this);
		}
	}
	@Override
	public String toString() {
		return "Geometri [id=" + id + ", granslinje=" + granslinje + ", yta=" + yta + "]";
	}
	
}
