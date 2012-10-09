package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_Det_Cargo_Empleado database table.
 * 
 */
@Entity
@Table(name="tb_Det_Cargo_Empleado", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DetCargoEmpleadoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DET_CARGO_EMPLEADO_IDDETCARGOEMPL_GENERATOR", sequenceName="SIE.TB_DET_CARGO_EMPLEADO_IDDETCARGOEMPL_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DET_CARGO_EMPLEADO_IDDETCARGOEMPL_GENERATOR")
	private Integer iddetcargoempl;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to CargoEmpleadoSie
    @ManyToOne
	@JoinColumn(name="idcargoempleado")
	private CargoEmpleadoSie tbCargoEmpleado;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
	
    public DetCargoEmpleadoSie() {
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
	
	public CargoEmpleadoSie getTbCargoEmpleado() {
		return this.tbCargoEmpleado;
	}

	public void setTbCargoEmpleado(CargoEmpleadoSie tbCargoEmpleado) {
		this.tbCargoEmpleado = tbCargoEmpleado;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public Integer getIddetcargoempl() {
		return iddetcargoempl;
	}

	public void setIddetcargoempl(Integer iddetcargoempl) {
		this.iddetcargoempl = iddetcargoempl;
	}


	public EmpleadoSie getTbEmpleado() {
		return tbEmpleado;
	}


	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}

}