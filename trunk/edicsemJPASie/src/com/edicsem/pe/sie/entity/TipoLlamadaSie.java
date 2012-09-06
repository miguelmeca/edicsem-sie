package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tb_tipo_llamada database table.
 * 
 */
@Entity
@Table(name="tb_tipo_llamada")
public class TipoLlamadaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_LLAMADA_IDTIPOLLAMADA_GENERATOR", sequenceName="TB_TIPO_LLAMADA_IDTIPO_LLAMADA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_LLAMADA_IDTIPOLLAMADA_GENERATOR")
	@Column(name="idtipo_llamada")
	private Integer idtipoLlamada;

	private String descripcion;

	private String descripcionabreviado;

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