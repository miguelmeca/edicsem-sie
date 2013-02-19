package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_tipo_cliente database table.
 * 
 */
@Entity
@Table(name="tb_tipo_cliente", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoClienteSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_CLIENTE_IDTIPOCLIENTE_GENERATOR", sequenceName="SIE.TB_TIPO_CLIENTE_IDTIPOCLIENTE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_CLIENTE_IDTIPOCLIENTE_GENERATOR")
	private Integer idtipocliente;

	private String descripcion;

    public TipoClienteSie() {
    }

	public Integer getIdtipocliente() {
		return this.idtipocliente;
	}

	public void setIdtipocliente(Integer idtipocliente) {
		this.idtipocliente = idtipocliente;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}