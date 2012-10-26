package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_det_contrato_empleado database table.
 * 
 */
@Embeddable
public class DetContratoEmpleadoSiePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer idcontrato;

	private Integer idempleado;

    public DetContratoEmpleadoSiePK() {
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
		if (!(other instanceof DetContratoEmpleadoSiePK)) {
			return false;
		}
		DetContratoEmpleadoSiePK castOther = (DetContratoEmpleadoSiePK)other;
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