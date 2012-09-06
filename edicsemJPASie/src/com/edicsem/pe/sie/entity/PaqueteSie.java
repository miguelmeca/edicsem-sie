package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the tb_paquete database table.
 * 
 */
@Entity
@Table(name="tb_paquete", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class PaqueteSie extends BaseMantenimientoForm  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PAQUETE_IDPAQUETE_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PAQUETE_IDPAQUETE_GENERATOR")
	private Integer idpaquete;

	private String descripcionpaquete;

	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private BigDecimal precioventa;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to DetPaqueteSie
	@OneToMany(mappedBy="tbPaquete")
	private Set<DetPaqueteSie> tbDetPaquetes;

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
	
}