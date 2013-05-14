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
import javax.persistence.Transient;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;


/**
 * The persistent class for the tb_caja database table.
 * 
 */
@Entity
@Table(name="tb_caja", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class CajaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CAJA_IDCAJA_GENERATOR", sequenceName="SIE.TB_CAJA_IDCAJA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CAJA_IDCAJA_GENERATOR")
	private Integer idcaja;

	private String descripcion;
	
	private String detalle;
	
	private String numcomprobante;
	
	private Integer idtipo;
	
	private BigDecimal monto;
	
	private BigDecimal saldo;

    @Temporal( TemporalType.DATE)
	private Date fechapago;
    
    @Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;
	
	private String usuariocreacion;
	
	private String usuariomodifica;
	
	 //bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;
	
	@Transient
	private String fechaCreacionString;
	
	@Transient
	private String tipoPago;
	
	@Transient
	private String empleadocreacion;
	
    public CajaSie() {
    }

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodifica() {
		return this.fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public String getUsuariocreacion() {
		return this.usuariocreacion;
	}

	public void setUsuariocreacion(String usuariocreacion) {
		this.usuariocreacion = usuariocreacion;
	}

	public String getUsuariomodifica() {
		return this.usuariomodifica;
	}

	public void setUsuariomodifica(String usuariomodifica) {
		this.usuariomodifica = usuariomodifica;
	}
	
	public String getFechaCreacionString() {
		fechaCreacionString = DateUtil.formatoString(getFechacreacion(), "dd/MM/yyyy");
		return fechaCreacionString;
	}

	public void setFechaCreacionString(String fechaCreacionString) {
		this.fechaCreacionString = fechaCreacionString;
	}

	public Integer getIdcaja() {
		return idcaja;
	}

	public void setIdcaja(Integer idcaja) {
		this.idcaja = idcaja;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getNumcomprobante() {
		return numcomprobante;
	}

	public void setNumcomprobante(String numcomprobante) {
		this.numcomprobante = numcomprobante;
	}

	public Date getFechapago() {
		return fechapago;
	}

	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getTipoPago() {
		if(idtipo==1)
			tipoPago="Entrada";
		else
			tipoPago="Gasto";
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Integer getIdtipo() {
		return idtipo;
	}

	public void setIdtipo(Integer idtipo) {
		this.idtipo = idtipo;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public EmpleadoSie getTbEmpleado() {
		return tbEmpleado;
	}

	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}

	public String getEmpleadocreacion() {
		return empleadocreacion;
	}

	public void setEmpleadocreacion(String empleadocreacion) {
		this.empleadocreacion = empleadocreacion;
	}
	
}