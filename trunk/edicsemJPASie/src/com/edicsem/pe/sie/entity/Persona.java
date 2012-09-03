package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPersona;

	private int anio;

	private String apemPersona;

	private String apepPersona;

    @Temporal( TemporalType.DATE)
	@Column(name="fec_mod")
	private Date fecMod;

    @Temporal( TemporalType.DATE)
	@Column(name="fec_regi")
	private Date fecRegi;

	private String nombrePersona;

	@Column(name="usu_mod")
	private String usuMod;

	@Column(name="usu_regi")
	private String usuRegi;

    public Persona() {
    }

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getAnio() {
		return this.anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getApemPersona() {
		return this.apemPersona;
	}

	public void setApemPersona(String apemPersona) {
		this.apemPersona = apemPersona;
	}

	public String getApepPersona() {
		return this.apepPersona;
	}

	public void setApepPersona(String apepPersona) {
		this.apepPersona = apepPersona;
	}

	public Date getFecMod() {
		return this.fecMod;
	}

	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}

	public Date getFecRegi() {
		return this.fecRegi;
	}

	public void setFecRegi(Date fecRegi) {
		this.fecRegi = fecRegi;
	}

	public String getNombrePersona() {
		return this.nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getUsuMod() {
		return this.usuMod;
	}

	public void setUsuMod(String usuMod) {
		this.usuMod = usuMod;
	}

	public String getUsuRegi() {
		return this.usuRegi;
	}

	public void setUsuRegi(String usuRegi) {
		this.usuRegi = usuRegi;
	}

}