package com.edicsem.pe.sie.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_tipo_asistencia database table.
 * 
 */
@Entity
@Table(name="tb_tipo_asistencia", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoAsistenciaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_ASISTENCIA_IDTIPOASISTENCIA_GENERATOR", sequenceName="SIE.TB_TIPO_ASISTENCIA_IDTIPOASISTENCIA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_ASISTENCIA_IDTIPOASISTENCIA_GENERATOR")
	private Integer idtipoasistencia;
	
	private String descripcion;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    public TipoAsistenciaSie() {
    }

	public Integer getIdtipoasistencia() {
		return idtipoasistencia;
	}

	public void setIdtipoasistencia(Integer idtipoasistencia) {
		this.idtipoasistencia = idtipoasistencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

}