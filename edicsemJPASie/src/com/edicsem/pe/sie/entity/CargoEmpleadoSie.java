package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

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
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;


/**
 * The persistent class for the tb_cargo_empleado database table.
 * 
 */
@Entity
@Table(name="tb_cargo_empleado", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class CargoEmpleadoSie  extends BaseMantenimientoForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CARGO_EMPLEADO_IDCARGOEMPLEADO_GENERATOR", sequenceName="SIE.TB_CARGO_EMPLEADO_IDCARGOEMPLEADO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CARGO_EMPLEADO_IDCARGOEMPLEADO_GENERATOR")
	
	private Integer idcargoempleado;

	private String descipcion;

	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	//bi-directional many-to-one association to EmpleadoSie
	@OneToMany(mappedBy="tbCargoEmpleado")
	private Set<EmpleadoSie> tbEmpleados;

	//bi-directional many-to-one association to PermisoSie
	@OneToMany(mappedBy="tbCargoEmpleado")
	private Set<PermisoSie> tbPermisos;

    public CargoEmpleadoSie() {
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
	
	public Set<EmpleadoSie> getTbEmpleados() {
		return this.tbEmpleados;
	}

	public void setTbEmpleados(Set<EmpleadoSie> tbEmpleados) {
		this.tbEmpleados = tbEmpleados;
	}
	
	public Set<PermisoSie> getTbPermisos() {
		return this.tbPermisos;
	}

	public void setTbPermisos(Set<PermisoSie> tbPermisos) {
		this.tbPermisos = tbPermisos;
	}
	
}