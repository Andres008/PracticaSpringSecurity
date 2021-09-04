package com.Practica1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Practica1.models.UsrRol;
import com.Practica1.repository.UsrRolRepository;

@Service
public class UsrRolService {

	@Autowired
	private UsrRolRepository usrRolRepository;

	public UsrRolService(UsrRolRepository usrRolRepository) {
		super();
		this.usrRolRepository = usrRolRepository;
	}
	
	public List<UsrRol> obtenerTodosRol(){
		return (List<UsrRol>) usrRolRepository.findAll();
	}
	
	public UsrRol ingresarNuevoRol(UsrRol objUsrRol) {
		return usrRolRepository.save(objUsrRol);		
	}
	
	public UsrRol actualizarRol(UsrRol objUsrRol) {
		return usrRolRepository.save(objUsrRol);
	}
	
	public void eliminarRol (UsrRol objUsrRol) {
		usrRolRepository.delete(objUsrRol);
	}
	
}
