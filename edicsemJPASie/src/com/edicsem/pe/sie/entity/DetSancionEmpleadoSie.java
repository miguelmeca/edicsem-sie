package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;


/**
 * The persistent class for the tb_det_sancion_empleado database table.
 * 
 */
@Entity
@Table(name="tb_det_sancion_empleado", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DetSancionEmpleadoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DET_SANCION_EMPLEADO_IDDETSANCIONEMPLEADO_GENERATOR", sequenceName="SIE.TB_DET_SANCION_EMPLEADO_IDDETSANCIONEMPLEADO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DET_SANCION_EMPLEADO_IDDETSANCIONEMPLEADO_GENERATOR")
	private Integer iddetsancionempleado;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;
	
	//bi-directional many-to-one association to DetSancionCargoSie
	@ManyToOne
	@JoinColumn(name="iddetsancioncargo")
	private DetSancionCargoSie tbDetsancioncargo;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	private String usuariocreacion;

	private String usuariomodifica;

    public DetSancionEmpleadoSie() {
    }

	public Integer getIddetsancionempleado() {
		return this.iddetsancionempleado;
	}

	public void setIddetsancionempleado(Integer iddetsancionempleado) {
		this.iddetsancionempleado = iddetsancionempleado;
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

	public DetSancionCargoSie getTbDetsancioncargo() {
		return tbDetsancioncargo;
	}

	public void setTbDetsancioncargo(DetSancionCargoSie tbDetsancioncargo) {
		this.tbDetsancioncargo = tbDetsancioncargo;
	}

	public EmpleadoSie getTbEmpleado() {
		return tbEmpleado;
	}

	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
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

}