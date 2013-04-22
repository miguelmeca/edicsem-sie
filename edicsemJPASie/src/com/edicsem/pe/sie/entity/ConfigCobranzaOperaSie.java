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
import javax.persistence.Transient;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_config_cobranza_opera database table.
 * 
 */
@Entity
@Table(name="tb_config_cobranza_opera", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ConfigCobranzaOperaSie   implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_CONFIG_COBRANZA_IDCONFIGCOB_GENERATOR", sequenceName="SIE.B_CONFIG_COBRANZA_IDCONFIGCOB_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CONFIG_COBRANZA_IDCONFIGCOB_GENERATOR")
	private Integer idconfigcob;

	private Integer diasprogramados;

	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	//bi-directional many-to-one association to TipoClienteSie
    @ManyToOne
	@JoinColumn(name="idtipocliente")
	private TipoClienteSie tbTipoCliente;
    
    //bi-directional many-to-one association to TipoCobranzaSie
    @ManyToOne
	@JoinColumn(name="idtipocobranza")
	private TipoCobranzaSie tbTipoCobranza;
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    @Transient
    private String fechaString;
    
    public ConfigCobranzaOperaSie() {
    }

	public Integer getIdconfigcob() {
		return idconfigcob;
	}

	public void setIdconfigcob(Integer idconfigcob) {
		this.idconfigcob = idconfigcob;
	}

	public Integer getDiasprogramados() {
		return diasprogramados;
	}

	public void setDiasprogramados(Integer diasprogramados) {
		this.diasprogramados = diasprogramados;
	}

	public Timestamp getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodifica() {
		return fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public TipoClienteSie getTbTipoCliente() {
		return tbTipoCliente;
	}

	public void setTbTipoCliente(TipoClienteSie tbTipoCliente) {
		this.tbTipoCliente = tbTipoCliente;
	}

	public TipoCobranzaSie getTbTipoCobranza() {
		return tbTipoCobranza;
	}

	public void setTbTipoCobranza(TipoCobranzaSie tbTipoCobranza) {
		this.tbTipoCobranza = tbTipoCobranza;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}
	
}