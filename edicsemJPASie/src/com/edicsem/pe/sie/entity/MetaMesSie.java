package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_meta_empresa database table.
 * 
 */
@Entity
@Table(name="tb_meta_mes", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class MetaMesSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id  
	@SequenceGenerator(name="TB_META_MES_IDMETAMES_GENERATOR", sequenceName="SIE.TB_META_MES_IDMETAMES_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_META_MES_IDMETAMES_GENERATOR")
	private Integer idmetames;
	
	private Integer codmes;
	
	private String mes;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private String fechafin;

	private String fechainicio;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;
    
	//bi-directional many-to-one association to MetaEmpresaSie
	@OneToMany(mappedBy="tbMetaMes")
	private Set<MetaEmpresaSie> tbMetaEmpresas;
	
	//bi-directional many-to-one association to MetaEmpleadoSie
	@OneToMany(mappedBy="tbMetaMes")
	private Set<MetaEmpleadoSie> tbMetaEmpleados;

    public MetaMesSie() {
    }
    
	public Integer getIdmetames() {
		return idmetames;
	}

	public void setIdmetames(Integer idmetames) {
		this.idmetames = idmetames;
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

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getCodmes() {
		return codmes;
	}

	public void setCodmes(Integer codmes) {
		this.codmes = codmes;
	}

	public String getFechafin() {
		return fechafin;
	}

	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}

	public String getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}

	public Set<MetaEmpresaSie> getTbMetaEmpresas() {
		return tbMetaEmpresas;
	}

	public void setTbMetaEmpresas(Set<MetaEmpresaSie> tbMetaEmpresas) {
		this.tbMetaEmpresas = tbMetaEmpresas;
	}

	public Set<MetaEmpleadoSie> getTbMetaEmpleados() {
		return tbMetaEmpleados;
	}

	public void setTbMetaEmpleados(Set<MetaEmpleadoSie> tbMetaEmpleados) {
		this.tbMetaEmpleados = tbMetaEmpleados;
	}
	
}