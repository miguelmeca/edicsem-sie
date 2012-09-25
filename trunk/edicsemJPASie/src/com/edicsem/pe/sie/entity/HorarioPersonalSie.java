package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the tb_horario_personal database table.
 * 
 */
@Entity
@Table(name="tb_horario_personal", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class HorarioPersonalSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_HORARIO_PERSONAL_IDHORARIOPERSONAL_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_HORARIO_PERSONAL_IDHORARIOPERSONAL_GENERATOR")
	@Column(name="id_horario_personal")
	private Integer idHorarioPersonal;

    @Temporal( TemporalType.DATE)
	private Date dia;

	@Column(name="hora_ingreso")
	private Time horaIngreso;

	@Column(name="hora_salida")
	private Time horaSalida;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;

    public HorarioPersonalSie() {
    }

	public Integer getIdHorarioPersonal() {
		return this.idHorarioPersonal;
	}

	public void setIdHorarioPersonal(Integer idHorarioPersonal) {
		this.idHorarioPersonal = idHorarioPersonal;
	}

	public Date getDia() {
		return this.dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Time getHoraIngreso() {
		return this.horaIngreso;
	}

	public void setHoraIngreso(Time horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public Time getHoraSalida() {
		return this.horaSalida;
	}

	public void setHoraSalida(Time horaSalida) {
		this.horaSalida = horaSalida;
	}

	public EmpleadoSie getTbEmpleado() {
		return this.tbEmpleado;
	}

	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}
	
}