package com.edicsem.pe.sie.beans;

import java.io.Serializable;
import java.util.List;

import com.edicsem.pe.sie.entity.GrupoVentaSie;


public class GrupoEmpleadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private GrupoVentaSie tbGrupoVenta;
	private List<EmpleadoDTO> detalle;
	
	public GrupoEmpleadoDTO() {
		
    }

	/**
	 * @return the tbGrupoVenta
	 */
	public GrupoVentaSie getTbGrupoVenta() {
		return tbGrupoVenta;
	}

	/**
	 * @param tbGrupoVenta the tbGrupoVenta to set
	 */
	public void setTbGrupoVenta(GrupoVentaSie tbGrupoVenta) {
		this.tbGrupoVenta = tbGrupoVenta;
	}

	/**
	 * @return the detalle
	 */
	public List<EmpleadoDTO> getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(List<EmpleadoDTO> detalle) {
		this.detalle = detalle;
	}
}