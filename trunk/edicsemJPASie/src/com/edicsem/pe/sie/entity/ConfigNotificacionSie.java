package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
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
 * The persistent class for the tb_config_notificacion database table.
 * 
 */
@Entity
@Table(name="tb_config_notificacion", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ConfigNotificacionSie  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CONFIG_NOTIFICACION_IDCONFIGNOTIFICA_GENERATOR", sequenceName="SIE.TB_CONFIG_NOTIFICACION_IDCONFIGNOTIFICA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CONFIG_NOTIFICACION_IDCONFIGNOTIFICA_GENERATOR")
	private Integer idconfignotifica;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;
	
	//bi-directional many-to-one association to TipoClienteSie
    @ManyToOne
	@JoinColumn(name="idtipocliente")
	private TipoClienteSie tbTipoCliente;
    
    //bi-directional many-to-one association to CalificacionEquifaxSie
    @ManyToOne
	@JoinColumn(name="idcalificacion")
	private CalificacionEquifaxSie tbCalificacion;
    
    //bi-directional many-to-one association to NotificacionSie
    @ManyToOne
	@JoinColumn(name="idtipocobranza")
	private NotificacionSie tbNotifica;
    
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
  	
    public ConfigNotificacionSie() {
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

	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public Integer getIdconfignotifica() {
		return idconfignotifica;
	}

	public void setIdconfignotifica(Integer idconfignotifica) {
		this.idconfignotifica = idconfignotifica;
	}

	public TipoClienteSie getTbTipoCliente() {
		return tbTipoCliente;
	}

	public void setTbTipoCliente(TipoClienteSie tbTipoCliente) {
		this.tbTipoCliente = tbTipoCliente;
	}

	public NotificacionSie getTbNotifica() {
		return tbNotifica;
	}

	public void setTbNotifica(NotificacionSie tbNotifica) {
		this.tbNotifica = tbNotifica;
	}

	/**
	 * @return the tbCalificacion
	 */
	public CalificacionEquifaxSie getTbCalificacion() {
		return tbCalificacion;
	}

	/**
	 * @param tbCalificacion the tbCalificacion to set
	 */
	public void setTbCalificacion(CalificacionEquifaxSie tbCalificacion) {
		this.tbCalificacion = tbCalificacion;
	}
}