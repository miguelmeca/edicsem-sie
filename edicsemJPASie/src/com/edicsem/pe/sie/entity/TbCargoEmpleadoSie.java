package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tb_cargo_empleado database table.
 * 
 */
@Entity
@Table(name="tb_cargo_empleado")
public class TbCargoEmpleadoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idcargoempleado;

	private String descipcion;

	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private Integer idestadogeneral;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to TbEmpleadoSie
	@OneToMany(mappedBy="tbCargoEmpleado")
	private Set<TbEmpleadoSie> tbEmpleados;

	//bi-directional many-to-one association to TbPermisoSie
	@OneToMany(mappedBy="tbCargoEmpleado")
	private Set<TbPermisoSie> tbPermisos;

    public TbCargoEmpleadoSie() {
    }

	public Integer getIdcargoempleado() {
		return this.idcargoempleado;
	}

	public void setIdcargoempleado(Integer idcargoempleado) {
		this.idcargoempleado = idcargoempleado;
	}

	public String getDescipcion() {
		return this.descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
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

	public Integer getIdestadogeneral() {
		return this.idestadogeneral;
	}

	public void setIdestadogeneral(Integer idestadogeneral) {
		this.idestadogeneral = idestadogeneral;
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

	public Set<TbEmpleadoSie> getTbEmpleados() {
		return this.tbEmpleados;
	}

	public void setTbEmpleados(Set<TbEmpleadoSie> tbEmpleados) {
		this.tbEmpleados = tbEmpleados;
	}
	
	public Set<TbPermisoSie> getTbPermisos() {
		return this.tbPermisos;
	}

	public void setTbPermisos(Set<TbPermisoSie> tbPermisos) {
		this.tbPermisos = tbPermisos;
	}
	
}