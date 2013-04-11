package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;


/**
 * The persistent class for the tb_historico_observaciones database table.
 * 
 */
@Entity
@Table(name="tb_historico_observaciones", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class HistoricoObservacionesSie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_HISTORICO_OBSERVACIONES_IDHISTORICOOBS_GENERATOR", sequenceName="SIE.TB_HISTORICO_OBSERVACIONES_IDHISTORICOOBS_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_HISTORICO_OBSERVACIONES_IDHISTORICOOBS_GENERATOR")
	private Integer idhistoricoobs;

	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;
	
	//bi-directional many-to-one association to ContratoSie
    @ManyToOne
	@JoinColumn(name="idcontrato")
	private ContratoSie tbContrato;

	@ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	private String observacion;

	private String rutaarchivo;

	private String usuariocreacion;

	private String usuariomodifica;

    public HistoricoObservacionesSie() {
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

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getRutaarchivo() {
		return this.rutaarchivo;
	}

	public void setRutaarchivo(String rutaarchivo) {
		this.rutaarchivo = rutaarchivo;
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
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public ContratoSie getTbContrato() {
		return tbContrato;
	}

	public void setTbContrato(ContratoSie tbContrato) {
		this.tbContrato = tbContrato;
	}

	public Integer getIdhistoricoobs() {
		return idhistoricoobs;
	}

	public void setIdhistoricoobs(Integer idhistoricoobs) {
		this.idhistoricoobs = idhistoricoobs;
	}
	
}