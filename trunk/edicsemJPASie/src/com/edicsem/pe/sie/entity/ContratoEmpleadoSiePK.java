package com.edicsem.pe.sie.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the tb_contrato_empleado database table.
 * 
 */
@Embeddable
public class ContratoEmpleadoSiePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	private Integer idcontrato;

	private Integer idempleado;

    public ContratoEmpleadoSiePK() {
    }
	public Integer getIdcontrato() {
		return this.idcontrato;
	}
	public void setIdcontrato(Integer idcontrato) {
		this.idcontrato = idcontrato;
	}
	public Integer getIdempleado() {
		return this.idempleado;
	}
	public void setIdempleado(Integer idempleado) {
		this.idempleado = idempleado;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ContratoEmpleadoSiePK)) {
			return false;
		}
		ContratoEmpleadoSiePK castOther = (ContratoEmpleadoSiePK)other;
		return 
			this.idcontrato.equals(castOther.idcontrato)
			&& this.idempleado.equals(castOther.idempleado);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idcontrato.hashCode();
		hash = hash * prime + this.idempleado.hashCode();
		
		return hash;
    }
}