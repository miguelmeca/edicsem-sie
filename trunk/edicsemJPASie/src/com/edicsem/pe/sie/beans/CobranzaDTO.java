package com.edicsem.pe.sie.beans;

import java.io.Serializable;

public class CobranzaDTO   implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idcobranza;
	private Integer idcliente;
	private Integer idcontrato;

    public CobranzaDTO() {
    }

	/**
	 * @return the idcobranza
	 */
	public Integer getIdcobranza() {
		return idcobranza;
	}

	/**
	 * @param idcobranza the idcobranza to set
	 */
	public void setIdcobranza(Integer idcobranza) {
		this.idcobranza = idcobranza;
	}

	/**
	 * @return the idcliente
	 */
	public Integer getIdcliente() {
		return idcliente;
	}

	/**
	 * @param idcliente the idcliente to set
	 */
	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}

	/**
	 * @return the idcontrato
	 */
	public Integer getIdcontrato() {
		return idcontrato;
	}

	/**
	 * @param idcontrato the idcontrato to set
	 */
	public void setIdcontrato(Integer idcontrato) {
		this.idcontrato = idcontrato;
	}
	
}