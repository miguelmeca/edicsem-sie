package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the tb_observacion_incidencia database table.
 * 
 */
@Entity
@Table(name="tb_observacion_incidencia", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ObservacionIncidenciaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_OBSERVACION_INCIDENCIA_IDOBSINCIDENCIA_GENERATOR", sequenceName="SIE.TB_OBSERVACION_INCIDENCIA_IDOBSINCIDENCIA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_OBSERVACION_INCIDENCIA_IDOBSINCIDENCIA_GENERATOR")
	private Integer idobsincidencia;

	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechafin;

	private Timestamp fechamodifica;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
 
	private String observacion;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional one-to-many association to IncidenciaSie
	@ManyToOne
	@JoinColumn(name="idincidencia")
	private IncidenciaSie tbIncidencia;

    public ObservacionIncidenciaSie() {
    }

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public Timestamp getFechamodifica() {
		return this.fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public Integer getIdobsincidencia() {
		return this.idobsincidencia;
	}

	public void setIdobsincidencia(Integer idobsincidencia) {
		this.idobsincidencia = idobsincidencia;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
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

	public IncidenciaSie getTbIncidencia() {
		return this.tbIncidencia;
	}

	public void setTbIncidencia(IncidenciaSie tbIncidencia) {
		this.tbIncidencia = tbIncidencia;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}
	
	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}