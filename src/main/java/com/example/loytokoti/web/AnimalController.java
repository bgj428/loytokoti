package com.example.loytokoti.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.loytokoti.domain.Animal;
import com.example.loytokoti.domain.AnimalRepository;
import com.example.loytokoti.domain.SpeciesRepository;

@Controller
public class AnimalController {
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	@Autowired
	private AnimalRepository arepository;

	@Autowired
	private SpeciesRepository srepository;

	// KIRJAUTUMINEN ENNEN KÄYTTÖÄ
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// KAIKKIEN ELÄIMIEN LISTAUS
	@RequestMapping(value = { "/", "/animallist" })
	public String animals(Model model) {
		model.addAttribute("animals", arepository.findAll());
		return "animallist";
	}

	// RESTIN JSON MUODOSSA ELÄIMET
	@RequestMapping(value = "/animals", method = RequestMethod.GET)
	public @ResponseBody List<Animal> animals() {
		return (List<Animal>) arepository.findAll();
	}

	// RESTIN AVULLA YKSITTÄINEN ELÄIN ID:N PERUSTEELLA
	@RequestMapping(value = "/animals/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Animal> findAnimalRest(@PathVariable("id") Long animalId) {
		return arepository.findById(animalId);
	}

	// UUDEN ELÄIMEN LISÄYS
	@RequestMapping(value = "/add")
	public String addAnimal(Model model) {
		model.addAttribute("animal", new Animal());
		model.addAttribute("allspecies", srepository.findAll());
		return "addanimal";
	}

	// UUDEN ElÄIMEN TALLENNUS
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Animal animal) {
		arepository.save(animal);
		return "redirect:/animallist";
	}

	// ELÄIMEN ADOPTOINTI/POISTO ID:N PERUSTEELLA
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteAnimal(@PathVariable("id") Long animalId, Model model) {
		arepository.deleteById(animalId);
		return "adoptointi";
	}

	// ELÄIMEN TIETOJEN MUOKKAUS
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAnimal(@PathVariable("id") Long animalId, Model model) {
		model.addAttribute("animal", arepository.findById(animalId));
		model.addAttribute("allspecies", srepository.findAll());
		return "editanimal";
	}

}
