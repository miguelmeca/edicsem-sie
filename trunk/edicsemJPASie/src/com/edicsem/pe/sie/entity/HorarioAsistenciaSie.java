package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Time;
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
 * The persistent class for the tb_horario_asistencia database table.
 * 
 */
@Entity
@Table(name="tb_horario_asistencia", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class HorarioAsistenciaSie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_HORARIO_ASISTENCIA_IDHORARIOASISTENCIA_GENERATOR", sequenceName="SIE.TB_HORARIO_ASISTENCIA_IDHORARIOASISTENCIA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_HORARIO_ASISTENCIA_IDHORARIOASISTENCIA_GENERATOR")
	private Integer idHorarioAsistencia;
	
	@Temporal( TemporalType.DATE)
	private Date fecha;

	@Column(name="hora_ingreso1")
	private Time horaIngreso1;

	@Column(name="hora_ingreso2")
	private Time horaIngreso2;

	@Column(name="hora_salida1")
	private Time horaSalida1;

	@Column(name="hora_salida2")
	private Time horaSalida2;

	private String observaciones;
    
	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;

    public HorarioAsistenciaSie() {
    }

	public Time getHoraIngreso1() {
		return this.horaIngreso1;
	}

	public void setHoraIngreso1(Time horaIngreso1) {
		this.horaIngreso1 = horaIngreso1;
	}

	public Time getHoraIngreso2() {
		return this.horaIngreso2;
	}

	public void setHoraIngreso2(Time horaIngreso2) {
		this.horaIngreso2 = horaIngreso2;
	}

	public Time getHoraSalida1() {
		return this.horaSalida1;
	}

	public void setHoraSalida1(Time horaSalida1) {
		this.horaSalida1 = horaSalida1;
	}

	public Time getHoraSalida2() {
		return this.horaSalida2;
	}

	public void setHoraSalida2(Time horaSalida2) {
		this.horaSalida2 = horaSalida2;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public EmpleadoSie getTbEmpleado() {
		return this.tbEmpleado;
	}

	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdHorarioAsistencia() {
		return idHorarioAsistencia;
	}
	
	public void setIdHorarioAsistencia(Integer idHorarioAsistencia) {
		this.idHorarioAsistencia = idHorarioAsistencia;
	}
	
}