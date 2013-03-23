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
 * The persistent class for the tb_cargo_empleado database table.
 * 
 */
@Entity
@Table(name="tb_cargo_empleado", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class CargoEmpleadoSie  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CARGO_EMPLEADO_IDCARGOEMPLEADO_GENERATOR", sequenceName="SIE.TB_CARGO_EMPLEADO_IDCARGOEMPLEADO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CARGO_EMPLEADO_IDCARGOEMPLEADO_GENERATOR")
	private Integer idcargoempleado;
	
	private String descripcion;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
	//bi-directional many-to-one association to DetSancionCargoSie
	@OneToMany(mappedBy="tbCargoempleado")
	private Set<DetSancionCargoSie> tbDetSancionCargo;
	
	//bi-directional many-to-one association to ContratoEmpleadoSie
	@OneToMany(mappedBy="tbCargoempleado")
	private Set<ContratoEmpleadoSie> tbContratoEmpleado;
	
	//bi-directional many-to-one association to ComisionVentaSie
	@OneToMany(mappedBy="tbCargoempleado")
	private Set<ComisionVentaSie> tbComisionVenta;
	
	//bi-directional many-to-one association to DetContratoEmpleadoSie
	@OneToMany(mappedBy="tbCargoempleado")
	private Set<DetContratoEmpleadoSie> tbDetContratoEmpleado;
	
	//bi-directional many-to-one association to DetTurnoEmplSie
	@OneToMany(mappedBy="tbCargoempleado")
	private Set<DetTurnoEmplSie> tbDetTurnoEmpl;

    public CargoEmpleadoSie() {
    }

	public Integer getIdcargoempleado() {
		return this.idcargoempleado;
	}

	public void setIdcargoempleado(Integer idcargoempleado) {
		this.idcargoempleado = idcargoempleado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Set<DetSancionCargoSie> getTbDetSancionCargo() {
		return tbDetSancionCargo;
	}

	public void setTbDetSancionCargo(Set<DetSancionCargoSie> tbDetSancionCargo) {
		this.tbDetSancionCargo = tbDetSancionCargo;
	}

	public Set<ContratoEmpleadoSie> getTbContratoEmpleado() {
		return tbContratoEmpleado;
	}

	public void setTbContratoEmpleado(Set<ContratoEmpleadoSie> tbContratoEmpleado) {
		this.tbContratoEmpleado = tbContratoEmpleado;
	}

	public Set<ComisionVentaSie> getTbComisionVenta() {
		return tbComisionVenta;
	}

	public void setTbComisionVenta(Set<ComisionVentaSie> tbComisionVenta) {
		this.tbComisionVenta = tbComisionVenta;
	}

	/**
	 * @return the tbDetContratoEmpleado
	 */
	public Set<DetContratoEmpleadoSie> getTbDetContratoEmpleado() {
		return tbDetContratoEmpleado;
	}

	/**
	 * @param tbDetContratoEmpleado the tbDetContratoEmpleado to set
	 */
	public void setTbDetContratoEmpleado(Set<DetContratoEmpleadoSie> tbDetContratoEmpleado) {
		this.tbDetContratoEmpleado = tbDetContratoEmpleado;
	}

	public Set<DetTurnoEmplSie> getTbDetTurnoEmpl() {
		return tbDetTurnoEmpl;
	}

	public void setTbDetTurnoEmpl(Set<DetTurnoEmplSie> tbDetTurnoEmpl) {
		this.tbDetTurnoEmpl = tbDetTurnoEmpl;
	}
	
}