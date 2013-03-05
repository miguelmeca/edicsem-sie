package com.edicsem.pe.sie.beans;

import java.io.Serializable;
import java.util.List;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.GrupoVentaSie;

/**
 * The persistent class for the tb_empresa database table.
 * 
 */ 
public class GrupoEmpleadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EmpleadoSie tbempleado;
	private GrupoVentaSie tbGrupoVenta;
	private Integer metaempleado;
	private List<GrupoEmpleadoDTO> detalle;
	
	public GrupoEmpleadoDTO() {
		
    }

	/**
	 * @return the tbempleado
	 */
	public EmpleadoSie getTbempleado() {
		return tbempleado;
	}

	/**
	 * @param tbempleado the tbempleado to set
	 */
	public void setTbempleado(EmpleadoSie tbempleado) {
		this.tbempleado = tbempleado;
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
	 * @return the metaempleado
	 */
	public Integer getMetaempleado() {
		return metaempleado;
	}

	/**
	 * @param metaempleado the metaempleado to set
	 */
	public void setMetaempleado(Integer metaempleado) {
		this.metaempleado = metaempleado;
	}

	/**
	 * @return the detalle
	 */
	public List<GrupoEmpleadoDTO> getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(List<GrupoEmpleadoDTO> detalle) {
		this.detalle = detalle;
	}
}