package com.Practica1.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Practica1.models.UsrRol;
import com.Practica1.services.UsrRolService;

@RestController
@RequestMapping("/rol")
public class UsrRolController {

	private static final Logger logger = LoggerFactory.getLogger(UsrRolController.class);
	private UsrRolService usrRolService;

	public UsrRolController(UsrRolService usrRolService) {
		super();
		this.usrRolService = usrRolService;
	}
	
	@GetMapping(produces = "application/json")
	public List<UsrRol> obtenerRoles() throws Exception{
		try {
			return usrRolService.obtenerTodosRol();
		}catch (Exception e) {
			logger.info("Error en el consumo del servicio obtener Rol. " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	
	@PostMapping(produces = "application/json")
	public UsrRol guardarRol(@RequestBody @Validated UsrRol objUsrRol ) throws Exception {
		try {
			return usrRolService.ingresarNuevoRol(objUsrRol);
		}catch (Exception e) {
			logger.info("Error en el consumo del servicio guardar Rol. " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	
	@PutMapping(produces = "application/json")
	public UsrRol actualizarRol(@RequestBody @Validated UsrRol objUsrRol) throws Exception {
		try {
			return usrRolService.actualizarRol(objUsrRol);
		}catch (Exception e) {
			logger.info("Error en el consumo del servicio guardar Rol. " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public boolean eliminarRol (@RequestBody @Validated UsrRol objUsrRol) throws Exception {
		try {
			usrRolService.eliminarRol(objUsrRol);
			return true;
		} catch (Exception e) {
			logger.info("Error en el consumo del servicio guardar Rol. " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	
}
