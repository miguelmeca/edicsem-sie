package com.edicsem.pe.sie.beans;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import com.edicsem.pe.sie.util.constants.DateUtil;


public class RecaudacionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigoDepositante;
	
	private String infoRetorno;
	
	private Date fecpago;
	
	private String fecpagoString;
	
	private Date fechaVencimiento;
	
	private String fechaVencimientoString;
	
	private double montoPagado;
	
	private double moraPagada;
	
	private double cargoFijoPagado;
	
	private double montoTotalPagado;
	
	private String agencia;
	
	private String numOperacion;
	
	private String referencia;
	
	private String terminal;
	
	private String medioAtencion;
	
	private Time horaAtencion;
	
	private String nrocheque;
	
	private String bancoCheque;

    public RecaudacionDTO() {
    	
    }

	/**
	 * @return the codigoDepositante
	 */
	public String getCodigoDepositante() {
		return codigoDepositante;
	}

	/**
	 * @param codigoDepositante the codigoDepositante to set
	 */
	public void setCodigoDepositante(String codigoDepositante) {
		this.codigoDepositante = codigoDepositante;
	}

	/**
	 * @return the infoRetorno
	 */
	public String getInfoRetorno() {
		return infoRetorno;
	}

	/**
	 * @param infoRetorno the infoRetorno to set
	 */
	public void setInfoRetorno(String infoRetorno) {
		this.infoRetorno = infoRetorno;
	}

	/**
	 * @return the fecpago
	 */
	public Date getFecpago() {
		return fecpago;
	}

	/**
	 * @param fecpago the fecpago to set
	 */
	public void setFecpago(Date fecpago) {
		this.fecpago = fecpago;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the montoPagado
	 */
	public double getMontoPagado() {
		return montoPagado;
	}

	/**
	 * @param montoPagado the montoPagado to set
	 */
	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}

	/**
	 * @return the moraPagada
	 */
	public double getMoraPagada() {
		return moraPagada;
	}

	/**
	 * @param moraPagada the moraPagada to set
	 */
	public void setMoraPagada(double moraPagada) {
		this.moraPagada = moraPagada;
	}

	/**
	 * @return the cargoFijoPagado
	 */
	public double getCargoFijoPagado() {
		return cargoFijoPagado;
	}

	/**
	 * @param cargoFijoPagado the cargoFijoPagado to set
	 */
	public void setCargoFijoPagado(double cargoFijoPagado) {
		this.cargoFijoPagado = cargoFijoPagado;
	}

	/**
	 * @return the montoTotalPagado
	 */
	public double getMontoTotalPagado() {
		return montoTotalPagado;
	}

	/**
	 * @param montoTotalPagado the montoTotalPagado to set
	 */
	public void setMontoTotalPagado(double montoTotalPagado) {
		this.montoTotalPagado = montoTotalPagado;
	}

	/**
	 * @return the agencia
	 */
	public String getAgencia() {
		return agencia;
	}

	/**
	 * @param agencia the agencia to set
	 */
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	/**
	 * @return the numOperacion
	 */
	public String getNumOperacion() {
		return numOperacion;
	}

	/**
	 * @param numOperacion the numOperacion to set
	 */
	public void setNumOperacion(String numOperacion) {
		this.numOperacion = numOperacion;
	}

	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return the terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * @param terminal the terminal to set
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * @return the medioAtencion
	 */
	public String getMedioAtencion() {
		return medioAtencion;
	}

	/**
	 * @param medioAtencion the medioAtencion to set
	 */
	public void setMedioAtencion(String medioAtencion) {
		this.medioAtencion = medioAtencion;
	}

	/**
	 * @return the nrocheque
	 */
	public String getNrocheque() {
		return nrocheque;
	}

	/**
	 * @param nrocheque the nrocheque to set
	 */
	public void setNrocheque(String nrocheque) {
		this.nrocheque = nrocheque;
	}

	/**
	 * @return the bancoCheque
	 */
	public String getBancoCheque() {
		return bancoCheque;
	}

	/**
	 * @param bancoCheque the bancoCheque to set
	 */
	public void setBancoCheque(String bancoCheque) {
		this.bancoCheque = bancoCheque;
	}

	/**
	 * @return the fecpagoString
	 */
	public String getFecpagoString() {
		fecpagoString = DateUtil.convertDateToString(fecpago);
		return fecpagoString;
	}

	/**
	 * @param fecpagoString the fecpagoString to set
	 */
	public void setFecpagoString(String fecpagoString) {
		this.fecpagoString = fecpagoString;
	}

	/**
	 * @return the fechaVencimientoString
	 */
	public String getFechaVencimientoString() {
		fechaVencimientoString = DateUtil.convertDateToString(fechaVencimiento);
		return fechaVencimientoString;
	}

	/**
	 * @param fechaVencimientoString the fechaVencimientoString to set
	 */
	public void setFechaVencimientoString(String fechaVencimientoString) {
		this.fechaVencimientoString = fechaVencimientoString;
	}

	/**
	 * @return the horaAtencion
	 */
	public Time getHoraAtencion() {
		return horaAtencion;
	}

	/**
	 * @param horaAtencion the horaAtencion to set
	 */
	public void setHoraAtencion(Time horaAtencion) {
		this.horaAtencion = horaAtencion;
	}
    
}