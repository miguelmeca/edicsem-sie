package com.edicsem.pe.sie.entity;

import java.io.Serializable;
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
 * The persistent class for the tb_tipo_modulo database table.
 * 
 */
@Entity
@Table(name="tb_tipo_modulo", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TipoModuloSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_MODULO_IDTIPOMODULO_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_MODULO_IDTIPOMODULO_GENERATOR")
	@Column(name="idtipo_modulo")
	private Integer idtipoModulo;

	private String descripcion;

	//bi-directional many-to-one association to ModuloOpcionSie
	@OneToMany(mappedBy="tbTipoModulo")
	private Set<ModuloOpcionSie> tbModuloOpcions;

    public TipoModuloSie() {
    }

	public Integer getIdtipoModulo() {
		return this.idtipoModulo;
	}

	public void setIdtipoModulo(Integer idtipoModulo) {
		this.idtipoModulo = idtipoModulo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<ModuloOpcionSie> getTbModuloOpcions() {
		return this.tbModuloOpcions;
	}

	public void setTbModuloOpcions(Set<ModuloOpcionSie> tbModuloOpcions) {
		this.tbModuloOpcions = tbModuloOpcions;
	}
	
}