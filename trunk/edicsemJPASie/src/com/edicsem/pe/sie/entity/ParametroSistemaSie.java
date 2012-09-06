package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_parametro_sistema database table.
 * 
 */
@Entity
@Table(name="tb_parametro_sistema")
public class ParametroSistemaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PARAMETRO_SISTEMA_IDPARAMETROSISTEMA_GENERATOR", sequenceName="TB_PARAMETRO_SISTEMA_IDPARAMETROSISTEMA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PARAMETRO_SISTEMA_IDPARAMETROSISTEMA_GENERATOR")
	private Integer idparametrosistema;

	private String areasistema;

	private String descripcion;

	private String valor;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

    public ParametroSistemaSie() {
    }

	public Integer getIdparametrosistema() {
		return this.idparametrosistema;
	}

	public void setIdparametrosistema(Integer idparametrosistema) {
		this.idparametrosistema = idparametrosistema;
	}

	public String getAreasistema() {
		return this.areasistema;
	}

	public void setAreasistema(String areasistema) {
		this.areasistema = areasistema;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}