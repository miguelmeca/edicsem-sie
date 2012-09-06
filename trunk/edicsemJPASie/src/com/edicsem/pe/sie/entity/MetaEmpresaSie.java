package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the tb_meta_empresa database table.
 * 
 */
@Entity
@Table(name="tb_meta_empresa")
public class MetaEmpresaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_META_EMPRESA_IDMETAMES_GENERATOR", sequenceName="TB_META_EMPRESA_IDMETAMES_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_META_EMPRESA_IDMETAMES_GENERATOR")
	private Integer idmetames;

	private String descipcion;

	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechafin;

    @Temporal( TemporalType.DATE)
	private Date fechainicio;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;

	private Integer valormeta;

	//bi-directional many-to-one association to EmpresaSie
    @ManyToOne
	@JoinColumn(name="idempresa")
	private EmpresaSie tbEmpresa;

    public MetaEmpresaSie() {
    }

	public Integer getIdmetames() {
		return this.idmetames;
	}

	public void setIdmetames(Integer idmetames) {
		this.idmetames = idmetames;
	}

	public String getDescipcion() {
		return this.descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public Date getFechainicio() {
		return this.fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
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

	public Integer getValormeta() {
		return this.valormeta;
	}

	public void setValormeta(Integer valormeta) {
		this.valormeta = valormeta;
	}

	public EmpresaSie getTbEmpresa() {
		return this.tbEmpresa;
	}

	public void setTbEmpresa(EmpresaSie tbEmpresa) {
		this.tbEmpresa = tbEmpresa;
	}
	
}