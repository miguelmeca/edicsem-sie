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
 * The persistent class for the tb_meta_empresa database table.
 * 
 */
@Entity
@Table(name="tb_meta_empresa", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class MetaEmpresaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_META_EMPRESA_IDMETAMES_GENERATOR", sequenceName="SIE.TB_META_EMPRESA_IDMETAMES_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_META_EMPRESA_IDMETAMES_GENERATOR")
	private Integer idmetaempresa;

	private String descripcion;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;

	private Integer valormeta;

	//bi-directional many-to-one association to EmpresaSie
    @ManyToOne
	@JoinColumn(name="idempresa")
	private EmpresaSie tbEmpresa;
    
    //bi-directional many-to-one association to MetaMesSie
    @ManyToOne
	@JoinColumn(name="idmetames")
	private MetaMesSie tbMetaMes;
    
    public MetaEmpresaSie() {
    }

	public Integer getIdmetaempresa() {
		return idmetaempresa;
	}

	public void setIdmetaempresa(Integer idmetaempresa) {
		this.idmetaempresa = idmetaempresa;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	public MetaMesSie getTbMetaMes() {
		return tbMetaMes;
	}
	
	public void setTbMetaMes(MetaMesSie tbMetaMes) {
		this.tbMetaMes = tbMetaMes;
	}

	
}