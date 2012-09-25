package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.util.Set;


/**
 * The persistent class for the tb_tipo_casa database table.
 * 
 */
@Entity
@Table(name="tb_tipo_casa", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoCasaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_CASA_IDTIPOCASA_GENERATOR", sequenceName="SIE.TB_TIPO_CASA_IDTIPOCASA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_CASA_IDTIPOCASA_GENERATOR")
	private Integer idtipocasa;

	private String descripcion;

	//bi-directional many-to-one association to DomicilioPersonaSie
	@OneToMany(mappedBy="tbTipoCasa")
	private Set<DomicilioPersonaSie> tbDomicilioPersonas;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

    public TipoCasaSie() {
    }

	public Integer getIdtipocasa() {
		return this.idtipocasa;
	}

	public void setIdtipocasa(Integer idtipocasa) {
		this.idtipocasa = idtipocasa;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<DomicilioPersonaSie> getTbDomicilioPersonas() {
		return this.tbDomicilioPersonas;
	}

	public void setTbDomicilioPersonas(Set<DomicilioPersonaSie> tbDomicilioPersonas) {
		this.tbDomicilioPersonas = tbDomicilioPersonas;
	}
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}