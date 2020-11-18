package com.example.loytokoti.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
	// Repository tuo jokaisen eläimen nimen perusteella <Animal>-listaan
	List<Animal> findByName(String name);

}
