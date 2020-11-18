package com.example.loytokoti.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SpeciesRepository extends CrudRepository<Species, Long> {
	// Repository hakee listana jokaisen eläimen nimen perusteella
	List<Species> findBySpeciesName(String speciesname);
}
