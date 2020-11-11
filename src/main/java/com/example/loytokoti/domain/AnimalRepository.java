package com.example.loytokoti.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface AnimalRepository extends MongoRepository<Animal, Long> {
	List<Animal> findByName(String name);
	public Optional<Animal> findById(Long id);
	

}
