package com.example.loytokoti;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.loytokoti.web.AnimalController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
	@Autowired
	private AnimalController controller;

	// Testi tarkistaa, ettei controlleri jää tyhjäksi eli latautuu kuten pitää
	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
