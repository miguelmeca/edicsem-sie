package com.edicsem.pe.sie.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the tb_detalle_comprobante database table.
 * 
 */
@Embeddable
public class DetalleComprobanteSiePK  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	private Integer idcomprobante;

	private Integer idkardex;

    public DetalleComprobanteSiePK() {
    }
	public Integer getIdcomprobante() {
		return this.idcomprobante;
	}
	public void setIdcomprobante(Integer idcomprobante) {
		this.idcomprobante = idcomprobante;
	}
	public Integer getIdkardex() {
		return this.idkardex;
	}
	public void setIdkardex(Integer idkardex) {
		this.idkardex = idkardex;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetalleComprobanteSiePK)) {
			return false;
		}
		DetalleComprobanteSiePK castOther = (DetalleComprobanteSiePK)other;
		return 
			this.idcomprobante.equals(castOther.idcomprobante)
			&& this.idkardex.equals(castOther.idkardex);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idcomprobante.hashCode();
		hash = hash * prime + this.idkardex.hashCode();
		
		return hash;
    }
}