package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tb_adelanto database table.
 * 
 */
@Entity
@Table(name="tb_adelanto", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class AdelantoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ADELANTO_IDADELANTO_GENERATOR", sequenceName="SIE.TB_ADELANTO_IDADELANTO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ADELANTO_IDADELANTO_GENERATOR")
	private Integer idadelanto;

	private String descripcion;

	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechaentrega;

  //bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie idempleado;

	private BigDecimal monto;

    public AdelantoSie() {
    }

	public Integer getIdadelanto() {
		return this.idadelanto;
	}

	public void setIdadelanto(Integer idadelanto) {
		this.idadelanto = idadelanto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	/**
	 * @param idempleado the idempleado to set
	 */
	public void setIdempleado(EmpleadoSie idempleado) {
		this.idempleado = idempleado;
	}

}