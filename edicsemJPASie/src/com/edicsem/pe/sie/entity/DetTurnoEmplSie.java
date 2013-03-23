package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_det_turno_empl database table.
 * 
 */
@Entity
@Table(name="tb_det_turno_empl", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DetTurnoEmplSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DET_TURNO_EMPL_IDDETTURNOEMPL_GENERATOR", sequenceName="SIE.TB_DET_TURNO_EMPL_IDDETTURNOEMPL_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DET_TURNO_EMPL_IDDETTURNOEMPL_GENERATOR")
	private Integer idDetTurnoEmpl;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;
	
	//bi-directional many-to-one association to TurnoSie
    @ManyToOne
	@JoinColumn(name="idturno")
	private TurnoSie tbTurno;
	
    //bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idEmpleado")
	private EmpleadoSie tbEmpleado;
    
    //bi-directional many-to-one association to CargoEmpleadoSie
    @ManyToOne
	@JoinColumn(name="tbCargoempleado")
	private CargoEmpleadoSie tbCargoempleado;
    
    //bi-directional many-to-one association to CarroSie
    @ManyToOne
	@JoinColumn(name="idCarro")
	private CarroSie tbCarro;
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    public DetTurnoEmplSie() {
    }

	public Integer getIdDetTurnoEmpl() {
		return idDetTurnoEmpl;
	}

	public void setIdDetTurnoEmpl(Integer idDetTurnoEmpl) {
		this.idDetTurnoEmpl = idDetTurnoEmpl;
	}

	public Timestamp getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechamodifica() {
		return fechamodifica;
	}

	public void setFechamodifica(Date fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public String getUsuariocreacion() {
		return usuariocreacion;
	}

	public void setUsuariocreacion(String usuariocreacion) {
		this.usuariocreacion = usuariocreacion;
	}

	public String getUsuariomodifica() {
		return usuariomodifica;
	}

	public void setUsuariomodifica(String usuariomodifica) {
		this.usuariomodifica = usuariomodifica;
	}

	public TurnoSie getTbTurno() {
		return tbTurno;
	}

	public void setTbTurno(TurnoSie tbTurno) {
		this.tbTurno = tbTurno;
	}

	public EmpleadoSie getTbEmpleado() {
		return tbEmpleado;
	}

	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}

	public CarroSie getTbCarro() {
		return tbCarro;
	}

	public void setTbCarro(CarroSie tbCarro) {
		this.tbCarro = tbCarro;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public CargoEmpleadoSie getTbCargoempleado() {
		return tbCargoempleado;
	}

	public void setTbCargoempleado(CargoEmpleadoSie tbCargoempleado) {
		this.tbCargoempleado = tbCargoempleado;
	}
    
}