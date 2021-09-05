package com.Practica1.controllers;

import java.util.List;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.tool.schema.internal.ExceptionHandlerHaltImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Autowired
	private BCryptPasswordEncoder bcrypt;

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
	public String guardarRol(@RequestBody @Validated UsrUsuario objUsrUsuario) {
		try {
			if (usrUsuarioService.buscarUsuarioExisten(objUsrUsuario) != null)
				throw new Exception("101");
			validacionObjUsrUsuario(objUsrUsuario);
			objUsrUsuario.getUsrUsuarioExperiencias().forEach(experiencia -> {
				experiencia.setUsrUsuario(objUsrUsuario);
			});
			verificarClave(objUsrUsuario);
			usrUsuarioService.ingresarNuevoUsuario(objUsrUsuario);
			return "100";
		} catch (Exception e) {
			logger.info("Error en el consumo del servicio guardar Usuario. " + e.getMessage());
			return e.getMessage();
		}
	}

	public void verificarClave(UsrUsuario objUsrUsuario) throws Exception {
		ModelUtil.passCheck(objUsrUsuario.getClave());
		objUsrUsuario.setClave(bcrypt.encode(objUsrUsuario.getClave()));
	}

	public void validacionObjUsrUsuario(UsrUsuario objUsrUsuario) throws Exception {
		if (ModelUtil.verificarCadenaVacio(objUsrUsuario.getIdUsuario()))
			throw new Exception("102");
		if (ModelUtil.verificarCadenaVacio(objUsrUsuario.getClave()))
			throw new Exception("103");
		if (ModelUtil.verificarObjetoVacio(objUsrUsuario.getUsrRol()))
			throw new Exception("104");
	}

	@PutMapping(produces = "application/json")
	public String actualizarRol(@RequestBody @Validated UsrUsuario objUsrUsuario) throws Exception {
		try {
			validacionObjUsrUsuario(objUsrUsuario);
			verificarClave(objUsrUsuario);
			usrUsuarioService.actualizarUsuario(objUsrUsuario);
			return "105";
		} catch (Exception e) {
			logger.info("Error en el consumo del servicio actualizar Usuario. " + e.getMessage());
			return e.getMessage();
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
