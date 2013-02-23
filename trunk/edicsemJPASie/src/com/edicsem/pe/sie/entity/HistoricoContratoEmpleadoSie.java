package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the tb_historico_contrato_empleado database table.
 * 
 */
@Entity
@Table(name="tb_historico_contrato_empleado")
public class HistoricoContratoEmpleadoSie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_HISTORICO_CONTRATO_EMPLEADO_IDHISTCONTRATOEMPL_GENERATOR", sequenceName="TB_HISTORICO_CONTRATO_EMPLEADO_IDHISTCONTRATOEMPL_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_HISTORICO_CONTRATO_EMPLEADO_IDHISTCONTRATOEMPL_GENERATOR")
	private Integer idhistcontratoempl;

	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechafinpatrocinio;

    @Temporal( TemporalType.DATE)
	private Date fechainiciocontrato;

    @Temporal( TemporalType.DATE)
	private Date fechainiciopatrocinio;
    
	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado", insertable = false, updatable = false)
	private EmpleadoSie tbEmpleado1;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="patrocinador", insertable = false, updatable = false)
	private EmpleadoSie tbEmpleado2;
    
    public HistoricoContratoEmpleadoSie() {
    }

	public Integer getIdhistcontratoempl() {
		return this.idhistcontratoempl;
	}

	public void setIdhistcontratoempl(Integer idhistcontratoempl) {
		this.idhistcontratoempl = idhistcontratoempl;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechafinpatrocinio() {
		return this.fechafinpatrocinio;
	}

	public void setFechafinpatrocinio(Date fechafinpatrocinio) {
		this.fechafinpatrocinio = fechafinpatrocinio;
	}

	public Date getFechainiciocontrato() {
		return this.fechainiciocontrato;
	}

	public void setFechainiciocontrato(Date fechainiciocontrato) {
		this.fechainiciocontrato = fechainiciocontrato;
	}

	public Date getFechainiciopatrocinio() {
		return this.fechainiciopatrocinio;
	}

	public void setFechainiciopatrocinio(Date fechainiciopatrocinio) {
		this.fechainiciopatrocinio = fechainiciopatrocinio;
	}
	public EmpleadoSie getTbEmpleado1() {
		return tbEmpleado1;
	}

	public void setTbEmpleado1(EmpleadoSie tbEmpleado1) {
		this.tbEmpleado1 = tbEmpleado1;
	}

	public EmpleadoSie getTbEmpleado2() {
		return tbEmpleado2;
	}

	public void setTbEmpleado2(EmpleadoSie tbEmpleado2) {
		this.tbEmpleado2 = tbEmpleado2;
	}

}