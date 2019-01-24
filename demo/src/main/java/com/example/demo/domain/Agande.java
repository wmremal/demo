package com.example.demo.domain;

import java.util.List;
import java.util.UUID;

public class Agande {

	private UUID id;
	private List<Person> personer;
	private List<Fastighet> fastigheter;
	private float andel;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public List<Person> getPersoner() {
		return personer;
	}
	public void setPersoner(List<Person> personer) {
		this.personer = personer;
	}
	public List<Fastighet> getFastigheter() {
		return fastigheter;
	}
	public void setFastigheter(List<Fastighet> fastigheter) {
		this.fastigheter = fastigheter;
	}
	public float getAndel() {
		return andel;
	}
	public void setAndel(float andel) {
		this.andel = andel;
	}
	
}
