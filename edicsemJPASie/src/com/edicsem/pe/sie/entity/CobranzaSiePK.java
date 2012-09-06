package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_cobranza database table.
 * 
 */
@Embeddable
public class CobranzaSiePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer idcobranza;

	private Integer idcliente;

	private Integer idcontrato;

    public CobranzaSiePK() {
    }
	public Integer getIdcobranza() {
		return this.idcobranza;
	}
	public void setIdcobranza(Integer idcobranza) {
		this.idcobranza = idcobranza;
	}
	public Integer getIdcliente() {
		return this.idcliente;
	}
	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}
	public Integer getIdcontrato() {
		return this.idcontrato;
	}
	public void setIdcontrato(Integer idcontrato) {
		this.idcontrato = idcontrato;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CobranzaSiePK)) {
			return false;
		}
		CobranzaSiePK castOther = (CobranzaSiePK)other;
		return 
			this.idcobranza.equals(castOther.idcobranza)
			&& this.idcliente.equals(castOther.idcliente)
			&& this.idcontrato.equals(castOther.idcontrato);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idcobranza.hashCode();
		hash = hash * prime + this.idcliente.hashCode();
		hash = hash * prime + this.idcontrato.hashCode();
		
		return hash;
    }
}