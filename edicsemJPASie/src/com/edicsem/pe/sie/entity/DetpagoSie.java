package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tb_detpago database table.
 * 
 */
@Entity
@Table(name="tb_detpago", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DetpagoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DETPAGO_IDDETPAGO_GENERATOR", sequenceName="SIE.TB_DETPAGO_IDDETPAGO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DETPAGO_IDDETPAGO_GENERATOR")
	private Integer iddetpago;

	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechaentrega;

    //bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie idempleado;

	private BigDecimal monto;

	//bi-directional many-to-one association to TipoImporteSie
    @ManyToOne
	@JoinColumn(name="idtipoimporte")
	private TipoImporteSie tbTipoImporte;

    public DetpagoSie() {
    }

	public Integer getIddetpago() {
		return this.iddetpago;
	}

	public void setIddetpago(Integer iddetpago) {
		this.iddetpago = iddetpago;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechaentrega() {
		return this.fechaentrega;
	}

	public void setFechaentrega(Date fechaentrega) {
		this.fechaentrega = fechaentrega;
	}

	public BigDecimal getMonto() {
		return this.monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public TipoImporteSie getTbTipoImporte() {
		return this.tbTipoImporte;
	}

	public void setTbTipoImporte(TipoImporteSie tbTipoImporte) {
		this.tbTipoImporte = tbTipoImporte;
	}
	
	public EmpleadoSie getIdempleado() {
		return idempleado;
	}
	
	public void setIdempleado(EmpleadoSie idempleado) {
		this.idempleado = idempleado;
	}
	
}