package com.Practica1.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the usr_rol database table.
 * 
 */
@Entity
@Table(name="usr_rol")
@NamedQuery(name="UsrRol.findAll", query="SELECT u FROM UsrRol u")
public class UsrRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USR_ROL_IDROL_GENERATOR", sequenceName="SEQ_USR_ROL", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USR_ROL_IDROL_GENERATOR")
	@Column(name="id_rol")
	private Long idRol;

	private String estado;

	private String nombre;

	private String observacion;

	//bi-directional many-to-one association to UsrUsuario
	@JsonIgnore
	@OneToMany(mappedBy="usrRol")
	private List<UsrUsuario> usrUsuarios;

	public UsrRol() {
	}

	public Long getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
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

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public List<UsrUsuario> getUsrUsuarios() {
		return this.usrUsuarios;
	}

	public void setUsrUsuarios(List<UsrUsuario> usrUsuarios) {
		this.usrUsuarios = usrUsuarios;
	}

	public UsrUsuario addUsrUsuario(UsrUsuario usrUsuario) {
		getUsrUsuarios().add(usrUsuario);
		usrUsuario.setUsrRol(this);

		return usrUsuario;
	}

	public UsrUsuario removeUsrUsuario(UsrUsuario usrUsuario) {
		getUsrUsuarios().remove(usrUsuario);
		usrUsuario.setUsrRol(null);

		return usrUsuario;
	}

}