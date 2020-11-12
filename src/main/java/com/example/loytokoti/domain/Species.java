package com.example.loytokoti.domain;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Species {
	// Autogeneroidaan ID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long speciesId;
	private String speciesName;

	// Luodaan tietokanta yhteys species- ja animal-luokkien välille
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "species")
	private List<Animal> animals;

	// Konstruktorien luominen
	public Species() {

	}

	public Species(String speciesName) {
		super();
		this.speciesName = speciesName;
	}
	// Getterit ja setterit

	public Long getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(Long speciesId) {
		this.speciesId = speciesId;
	}

	public String getSpeciesName() {
		return speciesName;
	}

	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	// Alustava mongodb-kanta yhteyden toString()
	@Override
	public String toString() {
		return String.format("Species[speciesId=%s, speciesName='%s']", speciesId, speciesName);
	}

}
