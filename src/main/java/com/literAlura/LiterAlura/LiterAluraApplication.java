package com.literAlura.LiterAlura;

import com.literAlura.LiterAlura.model.entity.libro.LibroRepository;
import com.literAlura.LiterAlura.model.entity.persona.PersonaRepository;
import com.literAlura.LiterAlura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	LibroRepository libroRepository;
	@Autowired
	PersonaRepository personaRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository, personaRepository);
		principal.muestraElMenu();
	}

}
