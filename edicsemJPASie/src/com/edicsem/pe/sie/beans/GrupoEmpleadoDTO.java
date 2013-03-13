package com.edicsem.pe.sie.beans;

import java.io.Serializable;
import java.util.List;

import com.edicsem.pe.sie.entity.EmpleadoSie;
import com.edicsem.pe.sie.entity.GrupoVentaSie;


public class GrupoEmpleadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EmpleadoSie tbempleado;
	private GrupoVentaSie tbGrupoVenta;
	private Integer metaempleado;
	private Integer entregada;
	private Integer facturada;
	private Integer totalentregada;
	private Integer toalfacturada;
	private Integer faltas;
	private Integer tardanza;
	private String stringFalta;
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

	/**
	 * @return the entregada
	 */
	public Integer getEntregada() {
		return entregada;
	}

	/**
	 * @param entregada the entregada to set
	 */
	public void setEntregada(Integer entregada) {
		this.entregada = entregada;
	}

	/**
	 * @return the facturada
	 */
	public Integer getFacturada() {
		return facturada;
	}

	/**
	 * @param facturada the facturada to set
	 */
	public void setFacturada(Integer facturada) {
		this.facturada = facturada;
	}

	/**
	 * @return the totalentregada
	 */
	public Integer getTotalentregada() {
		return totalentregada;
	}

	/**
	 * @param totalentregada the totalentregada to set
	 */
	public void setTotalentregada(Integer totalentregada) {
		this.totalentregada = totalentregada;
	}

	/**
	 * @return the toalfacturada
	 */
	public Integer getToalfacturada() {
		return toalfacturada;
	}

	/**
	 * @param toalfacturada the toalfacturada to set
	 */
	public void setToalfacturada(Integer toalfacturada) {
		this.toalfacturada = toalfacturada;
	}

	/**
	 * @return the faltas
	 */
	public Integer getFaltas() {
		return faltas;
	}

	/**
	 * @param faltas the faltas to set
	 */
	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}

	/**
	 * @return the tardanza
	 */
	public Integer getTardanza() {
		return tardanza;
	}

	/**
	 * @param tardanza the tardanza to set
	 */
	public void setTardanza(Integer tardanza) {
		this.tardanza = tardanza;
	}

	/**
	 * @return the stringFalta
	 */
	public String getStringFalta() {
		return stringFalta;
	}

	/**
	 * @param stringFalta the stringFalta to set
	 */
	public void setStringFalta(String stringFalta) {
		this.stringFalta = stringFalta;
	}
}