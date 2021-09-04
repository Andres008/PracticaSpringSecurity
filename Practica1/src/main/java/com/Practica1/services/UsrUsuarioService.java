package com.Practica1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Practica1.models.UsrUsuario;
import com.Practica1.repository.UsrUsuarioRepository;

@Service
public class UsrUsuarioService implements UserDetailsService {

	
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
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		UsrUsuario usuario = usrUsuarioRepository.findByIdUsuario(username);
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		UserDetails userDet = new User(usuario.getIdUsuario(), usuario.getClave(), roles);
		return userDet;
	}
	
}
