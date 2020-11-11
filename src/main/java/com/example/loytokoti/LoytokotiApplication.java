package com.example.loytokoti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.loytokoti.domain.Animal;
import com.example.loytokoti.domain.AnimalRepository;
import com.example.loytokoti.domain.Kayttaja;
import com.example.loytokoti.domain.KayttajaRepository;
import com.example.loytokoti.domain.Species;
import com.example.loytokoti.domain.SpeciesRepository;

@SpringBootApplication
@EnableMongoRepositories
public class LoytokotiApplication{
	private static final Logger log = LoggerFactory.getLogger(LoytokotiApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LoytokotiApplication.class, args);
	}

	@Bean
	public CommandLineRunner animalDemo(AnimalRepository arepository, SpeciesRepository srepository, 
			KayttajaRepository krepository) {
		return (args) -> {
			log.info("saving most common species");
			srepository.save(new Species("Kissa"));
			srepository.save(new Species("Koira"));
			srepository.save(new Species("Nokkaeläin"));
			srepository.save(new Species("Hevonen"));
			srepository.save(new Species("Pupu"));
			srepository.save(new Species("Lintu"));
			srepository.save(new Species("Muu"));
			
			log.info("saving couple of animals");
			arepository.save(new Animal("Mytty", 2, "Kissa", "male", srepository.findBySpeciesName("Kissa").get(0)));
			arepository.save(new Animal("Max", 3, "Kissa", "female", srepository.findBySpeciesName("Kissa").get(0)));
			arepository.save(new Animal("Pepsi", 12, "Koira", "male", srepository.findBySpeciesName("Koira").get(0)));
			arepository.save(new Animal("Perry", 5, "Nokkaeläin", "male", srepository.findBySpeciesName("Kissa").get(0)));
			arepository.save(new Animal("Hilma", 2, "Hevonen", "male", srepository.findBySpeciesName("Kissa").get(0)));
			
			log.info("saving users");
			Kayttaja kayttaja1 = new Kayttaja("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			Kayttaja kayttaja2 = new Kayttaja("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			krepository.save(kayttaja1);
			krepository.save(kayttaja2);
			
			log.info("fetching all the animals");
			for ( Animal animal : arepository.findAll()) {
				log.info(animal.toString());
			}
		};
	};
	
}
