package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.math.BigDecimal;


/**
 * The persistent class for the tb_comision_venta database table.
 * 
 */
@Entity
@Table(name="tb_comision_venta", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ComisionVentaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_COMISION_VENTA_IDCOMISION_GENERATOR", sequenceName="SIE.TB_COMISION_VENTA_IDCOMISION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_COMISION_VENTA_IDCOMISION_GENERATOR")
	private Integer idcomision;

	private String base;

	//bi-directional many-to-one association to CargoEmpleadoSie
    @ManyToOne
	@JoinColumn(name="idcargoempleado")
	private CargoEmpleadoSie tbCargoempleado;

	private BigDecimal pagoinmediato;

	private String porcentaje;

	private Integer rangofinal;

	private Integer rangoinicial;

	//bi-directional many-to-one association to CriterioComisionSie
    @ManyToOne
	@JoinColumn(name="idcriterio")
	private CriterioComisionSie tbCriterioComision;

    public ComisionVentaSie() {
    }

	public Integer getIdcomision() {
		return this.idcomision;
	}

	public void setIdcomision(Integer idcomision) {
		this.idcomision = idcomision;
	}

	public String getBase() {
		return this.base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public BigDecimal getPagoinmediato() {
		return this.pagoinmediato;
	}

	public void setPagoinmediato(BigDecimal pagoinmediato) {
		this.pagoinmediato = pagoinmediato;
	}

	public String getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Integer getRangofinal() {
		return this.rangofinal;
	}

	public void setRangofinal(Integer rangofinal) {
		this.rangofinal = rangofinal;
	}

	public Integer getRangoinicial() {
		return this.rangoinicial;
	}

	public void setRangoinicial(Integer rangoinicial) {
		this.rangoinicial = rangoinicial;
	}

	public CriterioComisionSie getTbCriterioComision() {
		return this.tbCriterioComision;
	}

	public void setTbCriterioComision(CriterioComisionSie tbCriterioComision) {
		this.tbCriterioComision = tbCriterioComision;
	}

	public CargoEmpleadoSie getTbCargoempleado() {
		return tbCargoempleado;
	}

	public void setTbCargoempleado(CargoEmpleadoSie tbCargoempleado) {
		this.tbCargoempleado = tbCargoempleado;
	}
	
}