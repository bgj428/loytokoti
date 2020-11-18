package com.example.loytokoti.domain;

import org.springframework.data.repository.CrudRepository;

public interface KayttajaRepository extends CrudRepository<Kayttaja, Long> {
	// Repository hakee käyttäjän usernamen perusteella
	Kayttaja findByUsername(String username);
}
