package com.edicsem.pe.sie.beans;

import java.io.Serializable;

import com.edicsem.pe.sie.entity.EmpleadoSie;


public class EmpleadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EmpleadoSie tbempleado;
	private Integer idempleado;
	private String cargo;
	private Integer metaempleado;
	private Integer entregada;
	private Integer facturada;
	private Integer totalentregada;
	private Integer totalfacturada;
	private Integer faltas;
	private Integer tardanza;
	private Integer puntajeTotal;
	private String stringFalta;
	private boolean lider;
	
	public EmpleadoDTO() {
		
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
	 * @return the puntajeTotal
	 */
	public Integer getPuntajeTotal() {
		return puntajeTotal;
	}

	/**
	 * @param puntajeTotal the puntajeTotal to set
	 */
	public void setPuntajeTotal(Integer puntajeTotal) {
		this.puntajeTotal = puntajeTotal;
	}

	/**
	 * @return the stringFalta
	 */
	public String getStringFalta() {
		if(getFaltas()!=null){
			stringFalta+= getFaltas()+" Faltas";
		}
		if(getFaltas()!=null&&getTardanza()!=null){
			stringFalta+=" y ";
		}
		if(getTardanza()!=null){
		stringFalta+=   getTardanza()+" Tardanzas";
		}
		return stringFalta;
	}

	/**
	 * @param stringFalta the stringFalta to set
	 */
	public void setStringFalta(String stringFalta) {
		this.stringFalta = stringFalta;
	}

	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the idempleado
	 */
	public Integer getIdempleado() {
		return idempleado;
	}

	/**
	 * @param idempleado the idempleado to set
	 */
	public void setIdempleado(Integer idempleado) {
		this.idempleado = idempleado;
	}

	/**
	 * @return the totalfacturada
	 */
	public Integer getTotalfacturada() {
		return totalfacturada;
	}

	/**
	 * @param totalfacturada the totalfacturada to set
	 */
	public void setTotalfacturada(Integer totalfacturada) {
		this.totalfacturada = totalfacturada;
	}

	/**
	 * @return the lider
	 */
	public boolean isLider() {
		return lider;
	}

	/**
	 * @param lider the lider to set
	 */
	public void setLider(boolean lider) {
		this.lider = lider;
	}
}