package com.Practica1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Practica1.models.UsrUsuario;
import com.Practica1.repository.UsrUsuarioRepository;

@Service
public class UsrUsuarioService {

	
	@Autowired
	private UsrUsuarioRepository usrUsuarioRepository;

	
	
	public UsrUsuarioService(UsrUsuarioRepository usrUsuarioRepository) {
		super();
		this.usrUsuarioRepository = usrUsuarioRepository;
	}

	public List<UsrUsuario> obtenerTodosUsuario(){
		return (List<UsrUsuario>) usrUsuarioRepository.findAll();
	}
	
	public UsrUsuario ingresarNuevoUsuario(UsrUsuario usrUsuario) {
		return usrUsuarioRepository.save(usrUsuario);		
	}
	
	public UsrUsuario actualizarUsuario(UsrUsuario usrUsuario) {
		return usrUsuarioRepository.save(usrUsuario);
	}
	
	public void eliminarUsuario (UsrUsuario usrUsuario) {
		usrUsuarioRepository.delete(usrUsuario);
	}
	
}
