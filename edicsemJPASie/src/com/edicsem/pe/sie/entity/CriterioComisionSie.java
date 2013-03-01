package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.util.Set;


/**
 * The persistent class for the tb_criterio_comision database table.
 * 
 */
@Entity
@Table(name="tb_criterio_comision", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class CriterioComisionSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CRITERIO_COMISION_IDCRITERIO_GENERATOR", sequenceName="SIE.TB_CRITERIO_COMISION_IDCRITERIO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CRITERIO_COMISION_IDCRITERIO_GENERATOR")
	private Integer idcriterio;

	private String descripcion;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	//bi-directional many-to-one association to ComisionVentaSie
	@OneToMany(mappedBy="tbCriterioComision")
	private Set<ComisionVentaSie> tbComisionVentas;

    public CriterioComisionSie() {
    }

	public Integer getIdcriterio() {
		return this.idcriterio;
	}

	public void setIdcriterio(Integer idcriterio) {
		this.idcriterio = idcriterio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<ComisionVentaSie> getTbComisionVentas() {
		return this.tbComisionVentas;
	}

	public void setTbComisionVentas(Set<ComisionVentaSie> tbComisionVentas) {
		this.tbComisionVentas = tbComisionVentas;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}