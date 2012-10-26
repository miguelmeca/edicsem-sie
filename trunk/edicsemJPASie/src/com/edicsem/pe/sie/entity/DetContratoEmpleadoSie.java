package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_det_contrato_empleado database table.
 * 
 */
@Entity
@Table(name="tb_det_contrato_empleado")
public class DetContratoEmpleadoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetContratoEmpleadoSiePK id;

	private Timestamp fechacreacion;

	private Integer idestadogeneral;

	//bi-directional many-to-one association to ContratoSie
    @ManyToOne
	@JoinColumn(name="idcontrato")
	private ContratoSie tbContrato;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;

    public DetContratoEmpleadoSie() {
    }

	public DetContratoEmpleadoSiePK getId() {
		return this.id;
	}

	public void setId(DetContratoEmpleadoSiePK id) {
		this.id = id;
	}
	
	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Integer getIdestadogeneral() {
		return this.idestadogeneral;
	}

	public void setIdestadogeneral(Integer idestadogeneral) {
		this.idestadogeneral = idestadogeneral;
	}

	public ContratoSie getContrato() {
		return this.tbContrato;
	}

	public void setTbContrato(ContratoSie tbContrato) {
		this.tbContrato = tbContrato;
	}
	
	public EmpleadoSie getTbEmpleado() {
		return tbEmpleado;
	}
	
	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}
	
	public ContratoSie getTbContrato() {
		return tbContrato;
	}
	
}