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
 * The persistent class for the tb_det_contrato_empleado database table.
 * 
 */
@Entity
@Table(name="tb_det_contrato_empleado" , schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DetContratoEmpleadoSie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_DET_CONTRATO_EMPLEADO_IDDETCONTRATOEMPL_GENERATOR", sequenceName="SIE.TB_DET_CONTRATO_EMPLEADO_IDDETCONTRATOEMPL_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DET_CONTRATO_EMPLEADO_IDDETCONTRATOEMPL_GENERATOR")
	private Integer idDetContratoEmpl;
	
	private Integer idcontrato;

	private Integer idempleado;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
	
	//bi-directional many-to-one association to ContratoSie
    @ManyToOne
	@JoinColumn(name="idcontrato",insertable = false, updatable = false)
	private ContratoSie tbContrato;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado",insertable = false, updatable = false)
	private EmpleadoSie tbEmpleado;

    public DetContratoEmpleadoSie() {
    }
	
	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public ContratoSie getContrato() {
		return this.tbContrato;
	}

	public void setTbContrato(ContratoSie tbContrato) {
		this.tbContrato = tbContrato;
	}
	
	public EmpleadoSie getTbEmpleado() {
		return tbEmpleado;
	}
	
	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}
	
	public ContratoSie getTbContrato() {
		return tbContrato;
	}
	
	/**
	 * @return the tbEstadoGeneral
	 */
	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	/**
	 * @param tbEstadoGeneral the tbEstadoGeneral to set
	 */
	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public Integer getIdcontrato() {
		return this.idcontrato;
	}
	public void setIdcontrato(Integer idcontrato) {
		this.idcontrato = idcontrato;
	}
	public Integer getIdempleado() {
		return this.idempleado;
	}
	public void setIdempleado(Integer idempleado) {
		this.idempleado = idempleado;
	}

	/**
	 * @return the idDetContratoEmpl
	 */
	public Integer getIdDetContratoEmpl() {
		return idDetContratoEmpl;
	}

	/**
	 * @param idDetContratoEmpl the idDetContratoEmpl to set
	 */
	public void setIdDetContratoEmpl(Integer idDetContratoEmpl) {
		this.idDetContratoEmpl = idDetContratoEmpl;
	}
}