package com.Practica1.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the usr_usuario_experiencia database table.
 * 
 */
@Entity
@Table(name="usr_usuario_experiencia")
@NamedQuery(name="UsrUsuarioExperiencia.findAll", query="SELECT u FROM UsrUsuarioExperiencia u")
public class UsrUsuarioExperiencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USR_USUARIO_EXPERIENCIA_IDEXPERIENCIA_GENERATOR", sequenceName="SEQ_USR_USUARIO_EXPERIENCIA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USR_USUARIO_EXPERIENCIA_IDEXPERIENCIA_GENERATOR")
	@Column(name="id_experiencia")
	private Long idExperiencia;

	private String experiencia;

	//bi-directional many-to-one association to UsrUsuario
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private UsrUsuario usrUsuario;

	public UsrUsuarioExperiencia() {
	}

	public Long getIdExperiencia() {
		return this.idExperiencia;
	}

	public void setIdExperiencia(Long idExperiencia) {
		this.idExperiencia = idExperiencia;
	}

	public String getExperiencia() {
		return this.experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public UsrUsuario getUsrUsuario() {
		return this.usrUsuario;
	}

	public void setUsrUsuario(UsrUsuario usrUsuario) {
		this.usrUsuario = usrUsuario;
	}

}