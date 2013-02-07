package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.util.Set;


/**
 * The persistent class for the tb_motivo database table.
 * 
 */
@Entity
@Table(name="tb_motivo", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class MotivoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MOTIVO_IDMOTIVO_GENERATOR", sequenceName="TB_MOTIVO_IDMOTIVO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MOTIVO_IDMOTIVO_GENERATOR")
	private Integer idmotivo;

	private String descripcion;

	@ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	//bi-directional many-to-one association to SeguimientoContratoSie
	@OneToMany(mappedBy="tbMotivo")
	private Set<SeguimientoContratoSie> tbSeguimientoContratos;

    public MotivoSie() {
    }

	public Integer getIdmotivo() {
		return this.idmotivo;
	}

	public void setIdmotivo(Integer idmotivo) {
		this.idmotivo = idmotivo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<SeguimientoContratoSie> getTbSeguimientoContratos() {
		return this.tbSeguimientoContratos;
	}

	public void setTbSeguimientoContratos(Set<SeguimientoContratoSie> tbSeguimientoContratos) {
		this.tbSeguimientoContratos = tbSeguimientoContratos;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}