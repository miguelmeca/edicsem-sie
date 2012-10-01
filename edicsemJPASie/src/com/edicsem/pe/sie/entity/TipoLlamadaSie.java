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
 * The persistent class for the tb_tipo_llamada database table.
 * 
 */
@Entity
@Table(name="tb_tipo_llamada", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoLlamadaSie  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_LLAMADA_IDTIPOLLAMADA_GENERATOR", sequenceName="SIE.TB_TIPO_LLAMADA_IDTIPO_LLAMADA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_LLAMADA_IDTIPOLLAMADA_GENERATOR")
	@Column(name="idtipo_llamada")
	private Integer idtipoLlamada;

	private String descripcion;

	private String descripcionabreviado;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to CobranzaOperadoraSie
	@OneToMany(mappedBy="tbTipoLlamada")
	private Set<CobranzaOperadoraSie> tbCobranzaOperadoras;

    public TipoLlamadaSie() {
    }

	public Integer getIdtipoLlamada() {
		return this.idtipoLlamada;
	}

	public void setIdtipoLlamada(Integer idtipoLlamada) {
		this.idtipoLlamada = idtipoLlamada;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionabreviado() {
		return this.descripcionabreviado;
	}

	public void setDescripcionabreviado(String descripcionabreviado) {
		this.descripcionabreviado = descripcionabreviado;
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

	public Set<CobranzaOperadoraSie> getTbCobranzaOperadoras() {
		return this.tbCobranzaOperadoras;
	}

	public void setTbCobranzaOperadoras(Set<CobranzaOperadoraSie> tbCobranzaOperadoras) {
		this.tbCobranzaOperadoras = tbCobranzaOperadoras;
	}
	
}