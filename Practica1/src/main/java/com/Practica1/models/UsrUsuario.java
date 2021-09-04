package com.Practica1.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the usr_usuarios database table.
 * 
 */
@Entity
@Table(name="usr_usuarios")
@NamedQuery(name="UsrUsuario.findAll", query="SELECT u FROM UsrUsuario u")
public class UsrUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_usuario")
	private String idUsuario;

	private String apellido;

	private String clave;

	private String direccion;

	private String email;

	private String estado;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to UsrRol
	@ManyToOne
	@JoinColumn(name="idrol")
	private UsrRol usrRol;

	//bi-directional many-to-one association to UsrUsuarioExperiencia
	@OneToMany(mappedBy="usrUsuario", cascade = CascadeType.ALL)
	private List<UsrUsuarioExperiencia> usrUsuarioExperiencias;

	public UsrUsuario() {
	}

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public UsrRol getUsrRol() {
		return this.usrRol;
	}

	public void setUsrRol(UsrRol usrRol) {
		this.usrRol = usrRol;
	}

	public List<UsrUsuarioExperiencia> getUsrUsuarioExperiencias() {
		return this.usrUsuarioExperiencias;
	}

	public void setUsrUsuarioExperiencias(List<UsrUsuarioExperiencia> usrUsuarioExperiencias) {
		this.usrUsuarioExperiencias = usrUsuarioExperiencias;
	}

	public UsrUsuarioExperiencia addUsrUsuarioExperiencia(UsrUsuarioExperiencia usrUsuarioExperiencia) {
		getUsrUsuarioExperiencias().add(usrUsuarioExperiencia);
		usrUsuarioExperiencia.setUsrUsuario(this);

		return usrUsuarioExperiencia;
	}

	public UsrUsuarioExperiencia removeUsrUsuarioExperiencia(UsrUsuarioExperiencia usrUsuarioExperiencia) {
		getUsrUsuarioExperiencias().remove(usrUsuarioExperiencia);
		usrUsuarioExperiencia.setUsrUsuario(null);

		return usrUsuarioExperiencia;
	}

}