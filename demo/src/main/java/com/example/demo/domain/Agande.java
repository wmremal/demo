package com.example.demo.domain;

import java.util.UUID;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Agande {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="person_id")
	private Person agare;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fastighet_id")
	private Fastighet fastighet;
	
	private float andel;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public float getAndel() {
		return andel;
	}
	public void setAndel(float andel) {
		this.andel = andel;
	}
	public Person getAgare() {
		return agare;
	}
	public void setAgare(Person agare) {
		this.agare = agare;
		if(!agare.getFastigheter().contains(this)) {
			agare.getFastigheter().add(this);
		}
	}
	public Fastighet getFastighet() {
		return fastighet;
	}
	public void setFastighet(Fastighet fastighet) {
		this.fastighet = fastighet;
		
	}
	
}
