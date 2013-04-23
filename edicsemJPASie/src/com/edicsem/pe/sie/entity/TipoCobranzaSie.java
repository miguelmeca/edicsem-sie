package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_tipo_cobranza database table.
 * 
 */
@Entity
@Table(name="tb_tipo_cobranza", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoCobranzaSie   implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_TIPO_COBRANZA_IDTIPOCOBRANZA_GENERATOR", sequenceName="SIE.TB_TIPO_COBRANZA_IDTIPOCOBRANZA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_COBRANZA_IDTIPOCOBRANZA_GENERATOR")
	private Integer idtipocobranza;

	private String descripcion;
    
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
	//bi-directional many-to-one association to ConfigCobranzaOperaSie
	@OneToMany(mappedBy="tbTipoCobranza")
	private Set<ConfigCobranzaOperaSie> tbConfigCobranza;
  	
    public TipoCobranzaSie() {
    }

	public Integer getIdtipocobranza() {
		return idtipocobranza;
	}

	public void setIdtipocobranza(Integer idtipocobranza) {
		this.idtipocobranza = idtipocobranza;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Set<ConfigCobranzaOperaSie> getTbConfigCobranza() {
		return tbConfigCobranza;
	}

	public void setTbConfigCobranza(Set<ConfigCobranzaOperaSie> tbConfigCobranza) {
		this.tbConfigCobranza = tbConfigCobranza;
	}
	
}