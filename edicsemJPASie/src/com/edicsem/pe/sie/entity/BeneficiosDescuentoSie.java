package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;


/**
 * The persistent class for the tb_beneficios_descuento database table.
 * 
 */
@Entity
@Table(name="tb_beneficios_descuento", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class BeneficiosDescuentoSie  extends BaseMantenimientoForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_BENEFICIOS_DESCUENTO_IDBENEFICIOSDESCUENTO_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_BENEFICIOS_DESCUENTO_IDBENEFICIOSDESCUENTO_GENERATOR")
	@Column(name="id_beneficios_descuento")
	private Integer idBeneficiosDescuento;

	private Timestamp fecha;

	private BigDecimal importe;

	//bi-directional many-to-one association to ConceptoSie
    @ManyToOne
	@JoinColumn(name="id_concepto")
	private ConceptoSie tbConcepto;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;

    public BeneficiosDescuentoSie() {
    }

	public Integer getIdBeneficiosDescuento() {
		return this.idBeneficiosDescuento;
	}

	public void setIdBeneficiosDescuento(Integer idBeneficiosDescuento) {
		this.idBeneficiosDescuento = idBeneficiosDescuento;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public ConceptoSie getTbConcepto() {
		return this.tbConcepto;
	}

	public void setTbConcepto(ConceptoSie tbConcepto) {
		this.tbConcepto = tbConcepto;
	}
	
	public EmpleadoSie getTbEmpleado() {
		return this.tbEmpleado;
	}

	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}
	
}