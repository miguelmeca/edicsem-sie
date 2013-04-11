package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;


/**
 * The persistent class for the tb_config_correos_incidente database table.
 * 
 */
@Entity
@Table(name="tb_config_correos_incidente", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ConfigCorrreosIncidenteSie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_CONFIG_CORREOS_INCIDENTE_IDCONFIGOBS_GENERATOR", sequenceName="SIE.TB_CONFIG_CORREOS_INCIDENTE_IDCONFIGOBS_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CONFIG_CORREOS_INCIDENTE_IDCONFIGOBS_GENERATOR")
	private Integer idconfigobs;

	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;
	
	private String asunto;
	
	private String usuariosEnvioMsj;
	
	//bi-directional many-to-one association to IncidenciaSie
    @ManyToOne
	@JoinColumn(name="idincidencia")
	private IncidenciaSie tbIncidencia;
    
	@ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	private String observacion;

	private String usuariocreacion;

	private String usuariomodifica;

    public ConfigCorrreosIncidenteSie() {
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

	/**
	 * @return the idconfigobs
	 */
	public Integer getIdconfigobs() {
		return idconfigobs;
	}

	/**
	 * @param idconfigobs the idconfigobs to set
	 */
	public void setIdconfigobs(Integer idconfigobs) {
		this.idconfigobs = idconfigobs;
	}

	/**
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the usuariosEnvioMsj
	 */
	public String getUsuariosEnvioMsj() {
		return usuariosEnvioMsj;
	}

	/**
	 * @param usuariosEnvioMsj the usuariosEnvioMsj to set
	 */
	public void setUsuariosEnvioMsj(String usuariosEnvioMsj) {
		this.usuariosEnvioMsj = usuariosEnvioMsj;
	}

	/**
	 * @return the tbIncidencia
	 */
	public IncidenciaSie getTbIncidencia() {
		return tbIncidencia;
	}

	/**
	 * @param tbIncidencia the tbIncidencia to set
	 */
	public void setTbIncidencia(IncidenciaSie tbIncidencia) {
		this.tbIncidencia = tbIncidencia;
	}
	
}