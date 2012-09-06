package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_empleado database table.
 * 
 */
@Entity
@Table(name="tb_empleado")
public class TbEmpleadoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idempleado;
	
	private String apematemp;

	private String apepatemp;

	private String contrasena;

	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String fechanacimiento;

	private Integer iddomicilioempleado;

	private Integer idtelefonoempleado;

	private Integer idtipodocumentoidentidad;

	private String nombreemp;

	private String numdocumento;

	private String usuario;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to TbCargoEmpleadoSie
    @ManyToOne
	@JoinColumn(name="idcargoempleado")
	private TbCargoEmpleadoSie tbCargoEmpleado;

    public TbEmpleadoSie() {
    }

	public Integer getIdempleado() {
		return this.idempleado;
	}

	public void setIdempleado(Integer idempleado) {
		this.idempleado = idempleado;
	}

	public String getApematemp() {
		return this.apematemp;
	}

	public void setApematemp(String apematemp) {
		this.apematemp = apematemp;
	}

	public String getApepatemp() {
		return this.apepatemp;
	}

	public void setApepatemp(String apepatemp) {
		this.apepatemp = apepatemp;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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

	public String getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(String fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public Integer getIddomicilioempleado() {
		return this.iddomicilioempleado;
	}

	public void setIddomicilioempleado(Integer iddomicilioempleado) {
		this.iddomicilioempleado = iddomicilioempleado;
	}

	public Integer getIdtelefonoempleado() {
		return this.idtelefonoempleado;
	}

	public void setIdtelefonoempleado(Integer idtelefonoempleado) {
		this.idtelefonoempleado = idtelefonoempleado;
	}

	public Integer getIdtipodocumentoidentidad() {
		return this.idtipodocumentoidentidad;
	}

	public void setIdtipodocumentoidentidad(Integer idtipodocumentoidentidad) {
		this.idtipodocumentoidentidad = idtipodocumentoidentidad;
	}

	public String getNombreemp() {
		return this.nombreemp;
	}

	public void setNombreemp(String nombreemp) {
		this.nombreemp = nombreemp;
	}

	public String getNumdocumento() {
		return this.numdocumento;
	}

	public void setNumdocumento(String numdocumento) {
		this.numdocumento = numdocumento;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	
}