package com.edicsem.pe.sie.beans;

import java.io.Serializable;

public class CobranzaDTO   implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idcobranza;
	

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
	
}