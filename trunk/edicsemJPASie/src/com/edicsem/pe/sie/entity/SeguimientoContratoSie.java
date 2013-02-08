package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;


/**
 * The persistent class for the tb_seguimiento_contrato database table.
 * 
 */
@Entity
@Table(name="tb_seguimiento_contrato", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class SeguimientoContratoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_SEGUIMIENTO_CONTRATO_IDSEGCONTRATO_GENERATOR", sequenceName="TB_SEGUIMIENTO_CONTRATO_IDSEGCONTRATO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_SEGUIMIENTO_CONTRATO_IDSEGCONTRATO_GENERATOR")
	private Integer idsegcontrato;

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

	//bi-directional many-to-one association to MotivoSie
    @ManyToOne
	@JoinColumn(name="idmotivo")
	private MotivoSie tbMotivo;

    public SeguimientoContratoSie() {
    }

	public Integer getIdsegcontrato() {
		return this.idsegcontrato;
	}

	public void setIdsegcontrato(Integer idsegcontrato) {
		this.idsegcontrato = idsegcontrato;
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

	public MotivoSie getTbMotivo() {
		return this.tbMotivo;
	}

	public void setTbMotivo(MotivoSie tbMotivo) {
		this.tbMotivo = tbMotivo;
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
	
}