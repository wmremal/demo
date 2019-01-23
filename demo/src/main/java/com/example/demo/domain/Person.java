package com.example.demo.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	private String fornamn;
	private String efternamn;
	
	@OneToMany(mappedBy="agare")
	private List<Agande> fastigheter;
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getFornamn() {
		return fornamn;
	}
	public void setFornamn(String fornamn) {
		this.fornamn = fornamn;
	}
	public String getEfternamn() {
		return efternamn;
	}
	public void setEfternamn(String efternamn) {
		this.efternamn = efternamn;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", fornamn=" + fornamn + ", efternamn=" + efternamn + "]";
	}
	public List<Agande> getFastigheter() {
		return fastigheter;
	}
	public void setFastigheter(List<Agande> fastigheter) {
		this.fastigheter = fastigheter;
	}
}
