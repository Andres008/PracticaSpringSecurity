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

import com.Practica1.Utils.ModelUtil;
import com.Practica1.models.UsrUsuario;
import com.Practica1.services.UsrUsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsrUsuarioController {

	private static final Logger logger = LoggerFactory.getLogger(UsrUsuarioController.class);
	private UsrUsuarioService usrUsuarioService;

	public UsrUsuarioController(UsrUsuarioService usrUsuarioService) {
		super();
		this.usrUsuarioService = usrUsuarioService;
	}

	@GetMapping(produces = "application/json")
	public List<UsrUsuario> obtenerRoles() throws Exception {
		try {
			return usrUsuarioService.obtenerTodosUsuario();
		} catch (Exception e) {
			logger.info("Error en el consumo del servicio obtener Rol. " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@PostMapping(produces = "application/json")
	public UsrUsuario guardarRol(@RequestBody @Validated UsrUsuario objUsrUsuario) throws Exception {
		try {
			objUsrUsuario.getUsrUsuarioExperiencias().forEach(experiencia -> {
				experiencia.setUsrUsuario(objUsrUsuario);
			});
			ModelUtil.passCheck(objUsrUsuario.getClave());
			objUsrUsuario.setClave(ModelUtil.convMD5(objUsrUsuario.getClave()));
			return usrUsuarioService.ingresarNuevoUsuario(objUsrUsuario);
		} catch (Exception e) {
			logger.info("Error en el consumo del servicio guardar Rol. " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@PutMapping(produces = "application/json")
	public UsrUsuario actualizarRol(@RequestBody @Validated UsrUsuario objUsrUsuario) throws Exception {
		try {
			return usrUsuarioService.actualizarUsuario(objUsrUsuario);
		} catch (Exception e) {
			logger.info("Error en el consumo del servicio guardar Rol. " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public boolean eliminarRol(@RequestBody @Validated UsrUsuario objUsrUsuario) throws Exception {
		try {
			usrUsuarioService.eliminarUsuario(objUsrUsuario);
			return true;
		} catch (Exception e) {
			logger.info("Error en el consumo del servicio guardar Rol. " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

}
