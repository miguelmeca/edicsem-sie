package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_permisos database table.
 * 
 */
@Entity
@Table(name="tb_permisos")
public class TbPermisoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idpermisos;

	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private Integer idestadogeneral;

	private Integer orden;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to TbCargoEmpleadoSie
    @ManyToOne
	@JoinColumn(name="idcargoempleado")
	private TbCargoEmpleadoSie tbCargoEmpleado;

	//bi-directional many-to-one association to TbModuloOpcionSie
    @ManyToOne
	@JoinColumn(name="idmoduloopcion")
	private TbModuloOpcionSie tbModuloOpcion;

    public TbPermisoSie() {
    }

	public Integer getIdpermisos() {
		return this.idpermisos;
	}

	public void setIdpermisos(Integer idpermisos) {
		this.idpermisos = idpermisos;
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

	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
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

	public TbCargoEmpleadoSie getTbCargoEmpleado() {
		return this.tbCargoEmpleado;
	}

	public void setTbCargoEmpleado(TbCargoEmpleadoSie tbCargoEmpleado) {
		this.tbCargoEmpleado = tbCargoEmpleado;
	}
	
	public TbModuloOpcionSie getTbModuloOpcion() {
		return this.tbModuloOpcion;
	}

	public void setTbModuloOpcion(TbModuloOpcionSie tbModuloOpcion) {
		this.tbModuloOpcion = tbModuloOpcion;
	}
	
}