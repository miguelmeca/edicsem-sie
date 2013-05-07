package com.edicsem.pe.sie.beans;

import java.io.Serializable;
import java.sql.Time;

public class TrabajoOperadoraDTO   implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idEmpleado;
	private String nombreCompleto;
	private int cantidaTotal;
	private int CantidadRealizada;
	private int cantidadPostergada;
	private int cantPromesaPago;
	private int cantOtraFecha;//Pagará en otra fecha
	private Time ultimaActualizacion;
	
    public TrabajoOperadoraDTO() {
    }

	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return the cantidaTotal
	 */
	public int getCantidaTotal() {
		return cantidaTotal;
	}

	/**
	 * @param cantidaTotal the cantidaTotal to set
	 */
	public void setCantidaTotal(int cantidaTotal) {
		this.cantidaTotal = cantidaTotal;
	}

	/**
	 * @return the cantidadRealizada
	 */
	public int getCantidadRealizada() {
		return CantidadRealizada;
	}

	/**
	 * @param cantidadRealizada the cantidadRealizada to set
	 */
	public void setCantidadRealizada(int cantidadRealizada) {
		CantidadRealizada = cantidadRealizada;
	}

	/**
	 * @return the cantidadPostergada
	 */
	public int getCantidadPostergada() {
		return cantidadPostergada;
	}

	/**
	 * @param cantidadPostergada the cantidadPostergada to set
	 */
	public void setCantidadPostergada(int cantidadPostergada) {
		this.cantidadPostergada = cantidadPostergada;
	}

	/**
	 * @return the cantPromesaPago
	 */
	public int getCantPromesaPago() {
		return cantPromesaPago;
	}

	/**
	 * @param cantPromesaPago the cantPromesaPago to set
	 */
	public void setCantPromesaPago(int cantPromesaPago) {
		this.cantPromesaPago = cantPromesaPago;
	}

	/**
	 * @return the cantOtraFecha
	 */
	public int getCantOtraFecha() {
		return cantOtraFecha;
	}

	/**
	 * @param cantOtraFecha the cantOtraFecha to set
	 */
	public void setCantOtraFecha(int cantOtraFecha) {
		this.cantOtraFecha = cantOtraFecha;
	}

	/**
	 * @return the ultimaActualizacion
	 */
	public Time getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	/**
	 * @param ultimaActualizacion the ultimaActualizacion to set
	 */
	public void setUltimaActualizacion(Time ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}

	/**
	 * @return the idEmpleado
	 */
	public int getIdEmpleado() {
		return idEmpleado;
	}

	/**
	 * @param idEmpleado the idEmpleado to set
	 */
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
    
}