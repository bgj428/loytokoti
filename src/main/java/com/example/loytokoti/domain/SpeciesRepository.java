package com.example.loytokoti.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface SpeciesRepository extends MongoRepository<Species, Long> {
	List<Species> findBySpeciesName(String speciesname);
}
