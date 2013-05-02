package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the tb_tipo_refinancia database table.
 * 
 */
@Entity
@Table(name="tb_tipo_refinancia", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoRefinanciaSie   implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_TIPO_REFINANCIA_IDTIPOREFIN_GENERATOR", sequenceName="SIE.TB_TIPO_REFINANCIA_IDTIPOREFIN_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_REFINANCIA_IDTIPOREFIN_GENERATOR")
	private Integer idtiporefin;
	
	private Integer cuotaconmora;
	
	private Integer aumentocuotarest;
	
	private BigDecimal minprimeracuota;
	
	private Timestamp fechainicio;
	
	private Timestamp fechafin;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	public TipoRefinanciaSie() {
    	
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

	public String getUsuariocreacion() {
		return usuariocreacion;
	}

	public void setUsuariocreacion(String usuariocreacion) {
		this.usuariocreacion = usuariocreacion;
	}

	public String getUsuariomodifica() {
		return usuariomodifica;
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
	public Integer getIdtiporefin() {
		return idtiporefin;
	}
	public void setIdtiporefin(Integer idtiporefin) {
		this.idtiporefin = idtiporefin;
	}
	public Integer getCuotaconmora() {
		return cuotaconmora;
	}
	public void setCuotaconmora(Integer cuotaconmora) {
		this.cuotaconmora = cuotaconmora;
	}
	public Integer getAumentocuotarest() {
		return aumentocuotarest;
	}
	public void setAumentocuotarest(Integer aumentocuotarest) {
		this.aumentocuotarest = aumentocuotarest;
	}
	public BigDecimal getMinprimeracuota() {
		return minprimeracuota;
	}
	public void setMinprimeracuota(BigDecimal minprimeracuota) {
		this.minprimeracuota = minprimeracuota;
	}
	public Timestamp getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Timestamp fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Timestamp getFechafin() {
		return fechafin;
	}
	public void setFechafin(Timestamp fechafin) {
		this.fechafin = fechafin;
	}
	
}