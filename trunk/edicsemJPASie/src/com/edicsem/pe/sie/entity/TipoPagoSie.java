package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_tipo_pago database table.
 * 
 */
@Entity
@Table(name="tb_tipo_pago", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoPagoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_PAGO_IDTIPOPAGO_GENERATOR", sequenceName="SIE.TB_TIPO_PAGO_IDTIPOPAGO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_PAGO_IDTIPOPAGO_GENERATOR")
	private Integer idtipopago;

	private String descripcion;
	
	//bi-directional many-to-one association to ContratoEmpleadoSie
	@OneToMany(mappedBy="tbTipoPago")
	private Set<ContratoEmpleadoSie> tbContratoEmpleados;

    public TipoPagoSie() {
    }

	public Integer getIdtipopago() {
		return this.idtipopago;
	}

	public void setIdtipopago(Integer idtipopago) {
		this.idtipopago = idtipopago;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<ContratoEmpleadoSie> getTbContratoEmpleados() {
		return tbContratoEmpleados;
	}

	public void setTbContratoEmpleados(Set<ContratoEmpleadoSie> tbContratoEmpleados) {
		this.tbContratoEmpleados = tbContratoEmpleados;
	}

}