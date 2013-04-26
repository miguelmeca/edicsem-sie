package com.edicsem.pe.sie.beans;

import java.io.Serializable;


public class ProductoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codproducto;

	private String descripcionproducto;
	
    public ProductoDTO() {
    }

	public String getCodproducto() {
		return codproducto;
	}

	public void setCodproducto(String codproducto) {
		this.codproducto = codproducto;
	}

	public String getDescripcionproducto() {
		return descripcionproducto;
	}

	public void setDescripcionproducto(String descripcionproducto) {
		this.descripcionproducto = descripcionproducto;
	}
	
}