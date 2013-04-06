package com.edicsem.pe.sie.entity;

import java.io.Serializable;

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
 * The persistent class for the tb_historial_parametro_sistema database table.
 * 
 */
@Entity
@Table(name="tb_historial_parametro_sistema", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class HistorialParametroSistemaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_HISTORIAL_PARAMETRO_SISTEMA_IDHISTORIALPARAMETRO_GENERATOR", sequenceName="SIE.TB_HISTORIAL_PARAMETRO_SISTEMA_IDHISTORIALPARAMETRO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_HISTORIAL_PARAMETRO_SISTEMA_IDHISTORIALPARAMETRO_GENERATOR")
	private Integer idhistorialparametro;

	private String descripcion;
	
	//bi-directional many-to-one association to ParametroSistemaSie
    @ManyToOne
	@JoinColumn(name="idparametrosistema")
	private ParametroSistemaSie tbparametroSistemas;

    public HistorialParametroSistemaSie() {
    }

	public ParametroSistemaSie getTbparametroSistemas() {
		return tbparametroSistemas;
	}

	public void setTbparametroSistemas(ParametroSistemaSie tbparametroSistemas) {
		this.tbparametroSistemas = tbparametroSistemas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Integer getIdhistorialparametro() {
		return idhistorialparametro;
	}
	
	public void setIdhistorialparametro(Integer idhistorialparametro) {
		this.idhistorialparametro = idhistorialparametro;
	}
    
}