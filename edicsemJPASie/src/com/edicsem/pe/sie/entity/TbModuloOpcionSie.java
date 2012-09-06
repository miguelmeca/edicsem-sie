package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_modulo_opcion database table.
 * 
 */
@Entity
@Table(name="tb_modulo_opcion")
public class TbModuloOpcionSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idmoduloopcion;

	private String descripcion;

	private String metodoactionlistener;

	private String nombremodulo;

	//bi-directional many-to-one association to TbTipoModuloSie
    @ManyToOne
	@JoinColumn(name="idtipo_modulo")
	private TbTipoModuloSie tbTipoModulo;

	//bi-directional many-to-one association to TbPermisoSie
	@OneToMany(mappedBy="tbModuloOpcion")
	private Set<TbPermisoSie> tbPermisos;

    public TbModuloOpcionSie() {
    }

	public Integer getIdmoduloopcion() {
		return this.idmoduloopcion;
	}

	public void setIdmoduloopcion(Integer idmoduloopcion) {
		this.idmoduloopcion = idmoduloopcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMetodoactionlistener() {
		return this.metodoactionlistener;
	}

	public void setMetodoactionlistener(String metodoactionlistener) {
		this.metodoactionlistener = metodoactionlistener;
	}

	public String getNombremodulo() {
		return this.nombremodulo;
	}

	public void setNombremodulo(String nombremodulo) {
		this.nombremodulo = nombremodulo;
	}

	public TbTipoModuloSie getTbTipoModulo() {
		return this.tbTipoModulo;
	}

	public void setTbTipoModulo(TbTipoModuloSie tbTipoModulo) {
		this.tbTipoModulo = tbTipoModulo;
	}
	
	public Set<TbPermisoSie> getTbPermisos() {
		return this.tbPermisos;
	}

	public void setTbPermisos(Set<TbPermisoSie> tbPermisos) {
		this.tbPermisos = tbPermisos;
	}
	
}