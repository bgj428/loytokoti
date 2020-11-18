package com.example.loytokoti;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.loytokoti.domain.Animal;
import com.example.loytokoti.domain.AnimalRepository;
import com.example.loytokoti.domain.Species;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AnimalRepositoryTest {

	@Autowired
	private AnimalRepository arepository;

	// testataaan, että löytyykö tietokannasta "Mythos" nimistä eläintä
	@Test
	public void findByNameShouldReturnAnimal() {
		List<Animal> animal = arepository.findByName("Mythos");

		assertThat(animal).hasSize(1);
		assertThat(animal.get(0).getName()).isEqualTo("Mythos");
	}

	// testataan eläimen luontia
	@Test
	public void createNewAnimal() {
		Animal animal = new Animal("moi", 2, "moi", "male", new Species("Moi"));
		arepository.save(animal);
		assertThat(animal.getId()).isNotNull();
	}

	// testataan poistoa
	@Test
	public void deleteAnimal() {
		Animal animal = new Animal("moi", 2, "moi", "male", new Species("Moi"));
		arepository.save(animal);
		arepository.deleteById(animal.getId());
	}
}
