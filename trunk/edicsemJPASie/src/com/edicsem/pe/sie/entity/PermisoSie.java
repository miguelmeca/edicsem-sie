package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_permisos database table.
 * 
 */
@Entity
@Table(name="tb_permiso", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class PermisoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PERMISOS_IDPERMISOS_GENERATOR", sequenceName="SIE.TB_PERMISOS_IDPERMISOS_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PERMISOS_IDPERMISOS_GENERATOR")
	private Integer idpermiso;
	
	private String nombrePermiso;
	
	private String descripcion;
	
	private String metodoactionlistener;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
	//bi-directional many-to-one association to ModuloOpcionSie
    @ManyToOne
	@JoinColumn(name="idmoduloopcion")
	private ModuloOpcionSie tbModuloOpcion;
    
    //bi-directional many-to-one association to DetPermisoEmpleadoSie
  	@OneToMany(mappedBy="tbPermisos")
  	private Set<DetPermisoEmpleadoSie> tbDetEmpleadoEmpleados;
  	
    public PermisoSie() {
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
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
	public ModuloOpcionSie getTbModuloOpcion() {
		return this.tbModuloOpcion;
	}

	public void setTbModuloOpcion(ModuloOpcionSie tbModuloOpcion) {
		this.tbModuloOpcion = tbModuloOpcion;
	}

	public Integer getIdpermiso() {
		return idpermiso;
	}

	public void setIdpermiso(Integer idpermiso) {
		this.idpermiso = idpermiso;
	}

	public String getMetodoactionlistener() {
		return metodoactionlistener;
	}

	public void setMetodoactionlistener(String metodoactionlistener) {
		this.metodoactionlistener = metodoactionlistener;
	}

	public Set<DetPermisoEmpleadoSie> getTbDetEmpleadoEmpleados() {
		return tbDetEmpleadoEmpleados;
	}

	public void setTbDetEmpleadoEmpleados(
			Set<DetPermisoEmpleadoSie> tbDetEmpleadoEmpleados) {
		this.tbDetEmpleadoEmpleados = tbDetEmpleadoEmpleados;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombrePermiso() {
		return nombrePermiso;
	}

	public void setNombrePermiso(String nombrePermiso) {
		this.nombrePermiso = nombrePermiso;
	}
	
}