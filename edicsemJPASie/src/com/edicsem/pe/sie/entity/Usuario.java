package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
public class Usuario extends BaseMantenimientoForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idusuario;

	@Column(name="nom_usu")
	private String nomUsu;

	@Column(name="pass_usu")
	private String passUsu;

    public Usuario() {
    }

	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNomUsu() {
		return this.nomUsu;
	}

	public void setNomUsu(String nomUsu) {
		this.nomUsu = nomUsu;
	}

	public String getPassUsu() {
		return this.passUsu;
	}

	public void setPassUsu(String passUsu) {
		this.passUsu = passUsu;
	}

}