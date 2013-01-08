package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_modulo_opcion database table.
 * 
 */
@Entity
@Table(name="tb_modulo_opcion", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ModuloOpcionSie  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_MODULO_OPCION_IDMODULOOPCION_GENERATOR", sequenceName="SIE.TB_MODULO_OPCION_IDMODULOOPCION_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_MODULO_OPCION_IDMODULOOPCION_GENERATOR")
	private Integer idmoduloopcion;

	private String descripcion;

	private String nombremodulo;

	//bi-directional many-to-one association to PermisoSie
	@OneToMany(mappedBy="tbModuloOpcion")
	private Set<PermisoSie> tbPermisos;

    public ModuloOpcionSie() {
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

	public String getNombremodulo() {
		return this.nombremodulo;
	}

	public void setNombremodulo(String nombremodulo) {
		this.nombremodulo = nombremodulo;
	}
	
	public Set<PermisoSie> getTbPermisos() {
		return this.tbPermisos;
	}

	public void setTbPermisos(Set<PermisoSie> tbPermisos) {
		this.tbPermisos = tbPermisos;
	}
	
}