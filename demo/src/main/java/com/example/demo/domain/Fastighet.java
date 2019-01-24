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
public class Fastighet {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	private String kommun;
	private String fastighetsnamn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="geometri_id")
	private Geometri geometri;
	
	
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
	
	public Geometri getGeometri() {
		return geometri;
	}
	public void setGeometri(Geometri geometri) {
		this.geometri = geometri;
		if(!geometri.getFastighets().contains(this)) {
			geometri.getFastighets().add(this);
		}
	}
	public String getFastighetsnamn() {
		return fastighetsnamn;
	}
	public void setFastighetsnamn(String fastighetsnamn) {
		this.fastighetsnamn = fastighetsnamn;
		
	}
	@Override
	public String toString() {
		return "Fastighet [id=" + id + ", kommun=" + kommun + ", fastighetsnamn=" + fastighetsnamn + ", geometri="
				+ geometri + "]";
	}
}
