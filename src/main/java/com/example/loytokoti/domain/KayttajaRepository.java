package com.example.loytokoti.domain;


import org.springframework.data.mongodb.repository.MongoRepository;


public interface KayttajaRepository extends MongoRepository<Kayttaja, Long> {
	Kayttaja findByUsername(String username);
}
