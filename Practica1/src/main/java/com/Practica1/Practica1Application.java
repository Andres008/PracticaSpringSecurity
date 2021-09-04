package com.Practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Practica1.services.UsrRolService;

@SpringBootApplication
public class Practica1Application implements CommandLineRunner {
	@Autowired
	private UsrRolService usrRolService;

	public static void main(String[] args) {
		SpringApplication.run(Practica1Application.class, args);
	}

	public void run(String... args) throws Exception {

		usrRolService.obtenerTodosRol().forEach(rol -> {

			System.out.println("Rol existente..:" + rol);
		});

	}

}
