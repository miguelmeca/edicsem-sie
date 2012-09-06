package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_tipo_modulo database table.
 * 
 */
@Entity
@Table(name="tb_tipo_modulo")
public class TbTipoModuloSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idtipo_modulo")
	private Integer idtipoModulo;

	private String descripcion;

	//bi-directional many-to-one association to TbModuloOpcionSie
	@OneToMany(mappedBy="tbTipoModulo")
	private Set<TbModuloOpcionSie> tbModuloOpcions;

    public TbTipoModuloSie() {
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

	public Set<TbModuloOpcionSie> getTbModuloOpcions() {
		return this.tbModuloOpcions;
	}

	public void setTbModuloOpcions(Set<TbModuloOpcionSie> tbModuloOpcions) {
		this.tbModuloOpcions = tbModuloOpcions;
	}
	
}