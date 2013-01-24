package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;


/**
 * The persistent class for the tb_auditoria_usuario database table.
 * 
 */
@Entity
@Table(name="tb_auditoria_usuario", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class AuditoriaUsuarioSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_AUDITORIA_USUARIO_IDAUDITORIA_GENERATOR", sequenceName="SIE.TB_AUDITORIA_USUARIO_IDAUDITORIA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_AUDITORIA_USUARIO_IDAUDITORIA_GENERATOR")
	private Integer idauditoria;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private String usuario;

    public AuditoriaUsuarioSie() {
    }

	public Integer getIdauditoria() {
		return this.idauditoria;
	}

	public void setIdauditoria(Integer idauditoria) {
		this.idauditoria = idauditoria;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}