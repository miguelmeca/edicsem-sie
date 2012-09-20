package com.edicsem.pe.sie.beans;

import java.io.Serializable;

/**
 * The persistent class for the tb_empresa database table.
 * 
 */ 
public class EmpresaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private Integer idempresa;

	private String descripcion;

	private String email;

	private String numruc;

	private String numtelefono;

	private String razonsocial;
	
	public EmpresaDTO() {
    }

	/**
	 * @return the idempresa
	 */
	public Integer getIdempresa() {
		return idempresa;
	}

	/**
	 * @param idempresa the idempresa to set
	 */
	public void setIdempresa(Integer idempresa) {
		this.idempresa = idempresa;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the numruc
	 */
	public String getNumruc() {
		return numruc;
	}

	/**
	 * @param numruc the numruc to set
	 */
	public void setNumruc(String numruc) {
		this.numruc = numruc;
	}

	/**
	 * @return the numtelefono
	 */
	public String getNumtelefono() {
		return numtelefono;
	}

	/**
	 * @param numtelefono the numtelefono to set
	 */
	public void setNumtelefono(String numtelefono) {
		this.numtelefono = numtelefono;
	}

	/**
	 * @return the razonsocial
	 */
	public String getRazonsocial() {
		return razonsocial;
	}

	/**
	 * @param razonsocial the razonsocial to set
	 */
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}
 
}