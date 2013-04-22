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
 * The persistent class for the tb_historico_calificacion_equifax database table.
 * 
 */
@Entity
@Table(name="tb_historico_calificacion_equifax", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class HistoricoCalificacionEquifaxSie  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_HISTORICO_CALIFICACION_EQUIFAX_IDHISTORICOCALI_GENERATOR", sequenceName="SIE.TB_HISTORICO_CALIFICACION_EQUIFAX_IDHISTORICOCALI_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_HISTORICO_CALIFICACION_EQUIFAX_IDHISTORICOCALI_GENERATOR")
	private Integer idhitoricocali;
	
	private String calificacion;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    //bi-directional many-to-one association to ClienteSie
    @ManyToOne
	@JoinColumn(name="idcliente")
	private ClienteSie tbCliente;
	
    public HistoricoCalificacionEquifaxSie() {
    }

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public Integer getIdhitoricocali() {
		return idhitoricocali;
	}

	public void setIdhitoricocali(Integer idhitoricocali) {
		this.idhitoricocali = idhitoricocali;
	}

	public ClienteSie getTbCliente() {
		return tbCliente;
	}

	public void setTbCliente(ClienteSie tbCliente) {
		this.tbCliente = tbCliente;
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
	
}