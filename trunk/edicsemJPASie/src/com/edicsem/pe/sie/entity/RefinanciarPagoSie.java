package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_refinancia_pago database table.
 * 
 */
@Entity
@Table(name="tb_refinancia_pago", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class RefinanciarPagoSie   implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_REFINANCIA_PAGO_IDREFINAPAGO_GENERATOR", sequenceName="SIE.TB_REFINANCIA_PAGO_IDREFINAPAGO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_REFINANCIA_PAGO_IDREFINAPAGO_GENERATOR")
	private Integer idrefinapago;
	
	private String antiguoPago;
	
	private String refinanciadoPago;

	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private BigDecimal impapagar;
	
    @Temporal( TemporalType.DATE)
	private Date fechaprogramada;

	private String usuariocreacion;

	private String usuariomodifica;
	
	//bi-directional many-to-one association to ContratoSie
    @ManyToOne
	@JoinColumn(name="idcontrato", insertable = false, updatable = false)
	private ContratoSie tbContrato;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

    public Integer getIdrefinapago() {
		return idrefinapago;
	}

	public void setIdrefinapago(Integer idrefinapago) {
		this.idrefinapago = idrefinapago;
	}

	public String getAntiguoPago() {
		return antiguoPago;
	}

	public void setAntiguoPago(String antiguoPago) {
		this.antiguoPago = antiguoPago;
	}

	public String getRefinanciadoPago() {
		return refinanciadoPago;
	}

	public void setRefinanciadoPago(String refinanciadoPago) {
		this.refinanciadoPago = refinanciadoPago;
	}

	public Timestamp getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodifica() {
		return fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public BigDecimal getImpapagar() {
		return impapagar;
	}

	public void setImpapagar(BigDecimal impapagar) {
		this.impapagar = impapagar;
	}

	public Date getFechaprogramada() {
		return fechaprogramada;
	}

	public void setFechaprogramada(Date fechaprogramada) {
		this.fechaprogramada = fechaprogramada;
	}

	public String getUsuariocreacion() {
		return usuariocreacion;
	}

	public void setUsuariocreacion(String usuariocreacion) {
		this.usuariocreacion = usuariocreacion;
	}

	public String getUsuariomodifica() {
		return usuariomodifica;
	}

	public void setUsuariomodifica(String usuariomodifica) {
		this.usuariomodifica = usuariomodifica;
	}

	public ContratoSie getTbContrato() {
		return tbContrato;
	}

	public void setTbContrato(ContratoSie tbContrato) {
		this.tbContrato = tbContrato;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public RefinanciarPagoSie() {
    	
    }
	
}