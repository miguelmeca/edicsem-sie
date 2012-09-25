package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.util.Set;


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

	private String metodoactionlistener;

	private String nombremodulo;

	//bi-directional many-to-one association to TipoModuloSie
    @ManyToOne
	@JoinColumn(name="idtipo_modulo")
	private TipoModuloSie tbTipoModulo;

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

	public TipoModuloSie getTbTipoModulo() {
		return this.tbTipoModulo;
	}

	public void setTbTipoModulo(TipoModuloSie tbTipoModulo) {
		this.tbTipoModulo = tbTipoModulo;
	}
	
	public Set<PermisoSie> getTbPermisos() {
		return this.tbPermisos;
	}

	public void setTbPermisos(Set<PermisoSie> tbPermisos) {
		this.tbPermisos = tbPermisos;
	}
	
}