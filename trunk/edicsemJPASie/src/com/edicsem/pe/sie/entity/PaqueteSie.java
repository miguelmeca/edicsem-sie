package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_paquete database table.
 * 
 */
@Entity
@Table(name="tb_paquete", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class PaqueteSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PAQUETE_IDPAQUETE_GENERATOR", sequenceName="SIE.TB_PAQUETE_IDPAQUETE_SEQ", initialValue=1, allocationSize =1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PAQUETE_IDPAQUETE_GENERATOR")
	private Integer idpaquete;

	private String codpaquete;	
	private String descripcionpaquete;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private BigDecimal precioventa;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to DetPaqueteSie
	@OneToMany(mappedBy="tbPaquete")
	private Set<DetPaqueteSie> tbDetPaquetes;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

    public PaqueteSie() {
    }

	public Integer getIdpaquete() {
		return this.idpaquete;
	}

	public void setIdpaquete(Integer idpaquete) {
		this.idpaquete = idpaquete;
	}

	public String getDescripcionpaquete() {
		return this.descripcionpaquete;
	}

	public void setDescripcionpaquete(String descripcionpaquete) {
		this.descripcionpaquete = descripcionpaquete;
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

	public BigDecimal getPrecioventa() {
		return this.precioventa;
	}

	public void setPrecioventa(BigDecimal precioventa) {
		this.precioventa = precioventa;
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

	public Set<DetPaqueteSie> getTbDetPaquetes() {
		return this.tbDetPaquetes;
	}

	public void setTbDetPaquetes(Set<DetPaqueteSie> tbDetPaquetes) {
		this.tbDetPaquetes = tbDetPaquetes;
	}
	
	public String getCodpaquete() {
		return codpaquete;
	}
	
	public void setCodpaquete(String codpaquete) {
		this.codpaquete = codpaquete;
	}
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}
	
	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}