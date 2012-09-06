package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the tb_contrato_empleado database table.
 * 
 */
@Entity
@Table(name="tb_contrato_empleado")
public class ContratoEmpleadoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ContratoEmpleadoSiePK id;

	private BigDecimal comision;

	private Timestamp fechacreacion;

	private String pago;

	//bi-directional many-to-one association to ContratoSie
    @ManyToOne
	@JoinColumn(name="idcontrato", insertable = false, updatable = false)
	private ContratoSie tbContrato;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado", insertable = false, updatable = false)
	private EmpleadoSie tbEmpleado1;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="patrocinador", insertable = false, updatable = false)
	private EmpleadoSie tbEmpleado2;

	//bi-directional many-to-one association to ProductoSie
    @ManyToOne
	@JoinColumn(name="idproducto")
	private ProductoSie tbProducto;

    public ContratoEmpleadoSie() {
    }

	public ContratoEmpleadoSiePK getId() {
		return this.id;
	}

	public void setId(ContratoEmpleadoSiePK id) {
		this.id = id;
	}
	
	public BigDecimal getComision() {
		return this.comision;
	}

	public void setComision(BigDecimal comision) {
		this.comision = comision;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getPago() {
		return this.pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}

	public ContratoSie getTbContrato() {
		return this.tbContrato;
	}

	public void setTbContrato(ContratoSie tbContrato) {
		this.tbContrato = tbContrato;
	}
	
	public EmpleadoSie getTbEmpleado1() {
		return this.tbEmpleado1;
	}

	public void setTbEmpleado1(EmpleadoSie tbEmpleado1) {
		this.tbEmpleado1 = tbEmpleado1;
	}
	
	public EmpleadoSie getTbEmpleado2() {
		return this.tbEmpleado2;
	}

	public void setTbEmpleado2(EmpleadoSie tbEmpleado2) {
		this.tbEmpleado2 = tbEmpleado2;
	}
	
	public ProductoSie getTbProducto() {
		return this.tbProducto;
	}

	public void setTbProducto(ProductoSie tbProducto) {
		this.tbProducto = tbProducto;
	}
	
}