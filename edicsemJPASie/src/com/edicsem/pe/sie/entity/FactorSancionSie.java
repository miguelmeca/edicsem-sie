package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tb_factor_sancion database table.
 * 
 */
@Entity
@Table(name="tb_factor_sancion", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class FactorSancionSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_FACTOR_SANCION_IDFACTOR_GENERATOR", sequenceName="SIE.TB_FACTOR_SANCION_IDFACTOR_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_FACTOR_SANCION_IDFACTOR_GENERATOR")
	private Integer idfactor;
	
	private String descripcion;

	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to SancionSie
	@OneToMany(mappedBy="tbFactorSancion")
	private Set<SancionSie> tbSancions;

    public FactorSancionSie() {
    }

	public Integer getIdfactor() {
		return this.idfactor;
	}

	public void setIdfactor(Integer idfactor) {
		this.idfactor = idfactor;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodifica() {
		return this.fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public String getUsuariocreacion() {
		return this.usuariocreacion;
	}

	public void setUsuariocreacion(String usuariocreacion) {
		this.usuariocreacion = usuariocreacion;
	}

	public String getUsuariomodifica() {
		return this.usuariomodifica;
	}

	public void setUsuariomodifica(String usuariomodifica) {
		this.usuariomodifica = usuariomodifica;
	}

	public Set<SancionSie> getTbSancions() {
		return this.tbSancions;
	}

	public void setTbSancions(Set<SancionSie> tbSancions) {
		this.tbSancions = tbSancions;
	}
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}
	
	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}