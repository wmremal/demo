package com.example.demo.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Geometri {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	private String granslinje;
	private float yta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fastighet_id")
	private Fastighet fastighet;
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
	
	
	@Override
	public String toString() {
		return "Geometri [id=" + id + ", granslinje=" + granslinje + ", yta=" + yta + "]";
	}
	public Fastighet getFastighet() {
		return fastighet;
	}
	public void setFastighet(Fastighet fastighet) {
		this.fastighet = fastighet;
		if(!fastighet.getGeometrier().contains(this)) {
			fastighet.getGeometrier().add(this);
		}
	}
	
}
