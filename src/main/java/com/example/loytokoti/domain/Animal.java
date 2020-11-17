package com.example.loytokoti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Animal {
	// Autogeneroidaan ID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private int age;
	private String info;
	private String sex;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "species")
	@JsonManagedReference
	private Species species;

	// Konstruktorit:
	public Animal() {

	}

	public Animal(String name, int age, String info, String sex, Species species) {
		super();
		this.name = name;
		this.age = age;
		this.info = info;
		this.sex = sex;
		this.species = species;
	}

	// Getterit ja setterit

	public Species getSpecies() {
		return species;
	}

	

	public void setSpecies(Species species) {
		this.species = species;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	// Alustava mongodb-kanta yhteyden toString()
	// if-lause, jos ei lajia(species) ole määritelty
	@Override
	public String toString() {
		if (this.species != null)
			return String.format("Animal[id=%s, name='%s', age='%s', info='%s', sex='%s']", id, name, age, info, sex);
		else

			return String.format("Animal[id=%s, name='%s', age='%s', info='%s', sex='%s']", id, name, age, info, sex);
	}

}
