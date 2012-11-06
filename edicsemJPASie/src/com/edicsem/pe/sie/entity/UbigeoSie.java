package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_ubigeo database table.
 * 
 */
@Entity
@Table(name="tb_ubigeo", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class UbigeoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_UBIGEO_IDUBIGEO_GENERATOR", sequenceName="SIE.TB_UBIGEO_IDUBIGEO_SEQ", initialValue=1, allocationSize =1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_UBIGEO_IDUBIGEO_GENERATOR")
	private Integer idubigeo;

	private String coddepartamento;

	private String coddistrito;

	private String codprovincia;

	private String nombre;

	//bi-directional many-to-one association to DomicilioPersonaSie
	@OneToMany(mappedBy="tbUbigeo")
	private Set<DomicilioPersonaSie> tbDomicilioPersonas;

	//bi-directional many-to-one association to PuntoVentaSie
	@OneToMany(mappedBy="tbUbigeo")
	private Set<PuntoVentaSie> tbPuntoVentas;
	

	public UbigeoSie() {
    }

	public Integer getIdubigeo() {
		return this.idubigeo;
	}

	public void setIdubigeo(Integer idubigeo) {
		this.idubigeo = idubigeo;
	}

	public String getCoddepartamento() {
		return this.coddepartamento;
	}

	public void setCoddepartamento(String coddepartamento) {
		this.coddepartamento = coddepartamento;
	}

	public String getCoddistrito() {
		return this.coddistrito;
	}

	public void setCoddistrito(String coddistrito) {
		this.coddistrito = coddistrito;
	}

	public String getCodprovincia() {
		return this.codprovincia;
	}

	public void setCodprovincia(String codprovincia) {
		this.codprovincia = codprovincia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<DomicilioPersonaSie> getTbDomicilioPersonas() {
		return this.tbDomicilioPersonas;
	}

	public void setTbDomicilioPersonas(Set<DomicilioPersonaSie> tbDomicilioPersonas) {
		this.tbDomicilioPersonas = tbDomicilioPersonas;
	}
	
	public Set<PuntoVentaSie> getTbPuntoVentas() {
		return this.tbPuntoVentas;
	}

	public void setTbPuntoVentas(Set<PuntoVentaSie> tbPuntoVentas) {
		this.tbPuntoVentas = tbPuntoVentas;
	}
	
}