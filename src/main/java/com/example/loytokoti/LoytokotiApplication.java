package com.example.loytokoti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.loytokoti.domain.Animal;
import com.example.loytokoti.domain.AnimalRepository;
import com.example.loytokoti.domain.Kayttaja;
import com.example.loytokoti.domain.KayttajaRepository;
import com.example.loytokoti.domain.Species;
import com.example.loytokoti.domain.SpeciesRepository;


@SpringBootApplication
@ComponentScan({ "com.example.loytokoti", "controller" })
public class LoytokotiApplication {
	private static final Logger log = LoggerFactory.getLogger(LoytokotiApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(LoytokotiApplication.class, args);
	}

	@Bean
	public CommandLineRunner animalDemo(AnimalRepository arepository, SpeciesRepository srepository,
			KayttajaRepository krepository) {
		return (args) -> {
			// Luodaan joitakin lajeja sovellukseen tallentaen ne SpeciesRepositoryn kautta
			log.info("saving most common species");
			srepository.save(new Species("Kissa"));
			srepository.save(new Species("Koira"));
			srepository.save(new Species("Nokkaeläin"));
			srepository.save(new Species("Hevonen"));
			srepository.save(new Species("Pupu"));
			srepository.save(new Species("Lintu"));
			srepository.save(new Species("Muu"));

			// Lisätään löytökodin eläimiä, jotta nähdään sovelluksen toiminta
			log.info("saving couple of animals");
			arepository.save(new Animal("Excelsa", 5,
					"Kurrnau... tai miten nyt tykkääkin sanoa. Olen tällainen ikkunaprinsessa. Pidän siitä, että saan katsella maailman menoa ikkunalasin takaa.",
					"male", srepository.findBySpeciesName("Kissa").get(0)));
			arepository.save(new Animal("Mythos", 3, "Miau! Täällä kuikuilee mystinen kissaherra Mythos. Haluaisitko tutustua hieman salaperäiseen sielunelämääni?", "female", srepository.findBySpeciesName("Kissa").get(0)));
			arepository.save(new Animal("Pepsi", 12, "Huima, persoonallinen hömelö – se on kaikesta niin innoissaan, että hiki tulee jo sen olemusta katsellessa. Huima haluaa osallistua kaikkeen, mitä sen ympärillä tapahtuu", "male", srepository.findBySpeciesName("Koira").get(0)));
			arepository
					.save(new Animal("Perry", 5, "Pidetään hiljaisena ja mitään tekemättömänä. Hän elää kuitenkin kaksoiselämää salaisena agenttina.", "male", srepository.findBySpeciesName("Nokkaeläin").get(0)));
			arepository.save(new Animal("Hilma", 2, "Hilmalla on kuvankaunis, komea suku. Se jää todennäköisesti isäänsä ja emäänsä pienemmäksi, säkäkorkeudeltaan noin 160-senttiseksi.", "male", srepository.findBySpeciesName("Hevonen").get(0)));

			// Lisätään käyttäjät kirjautumiseen
			log.info("saving users");
			Kayttaja kayttaja1 = new Kayttaja("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"USER");
			Kayttaja kayttaja2 = new Kayttaja("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"ADMIN");
			krepository.save(kayttaja1);
			krepository.save(kayttaja2);

			// Haetaan kaikki eläimet
			log.info("fetching all the animals");
			for (Animal animal : arepository.findAll()) {
				log.info(animal.toString());
			}
		};
	};

}
