package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.sql.Timestamp;


/**
 * The persistent class for the tb_horarios_pv database table.
 * 
 */
@Entity
@Table(name="tb_horarios_pv", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class HorariosPvSie extends BaseMantenimientoForm  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_HORARIOS_PV_IDHORARIOSPV_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_HORARIOS_PV_IDHORARIOSPV_GENERATOR")
	@Column(name="id_horarios_pv")
	private Integer idHorariosPv;

	private Timestamp fecha;

	@Column(name="hora_entrada")
	private String horaEntrada;

	@Column(name="hora_salida")
	private String horaSalida;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="encargado1")
	private EmpleadoSie tbEmpleado1;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="encargado2")
	private EmpleadoSie tbEmpleado2;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="encargado3")
	private EmpleadoSie tbEmpleado3;

	//bi-directional many-to-one association to PuntoVentaSie
    @ManyToOne
	@JoinColumn(name="idpuntoventa")
	private PuntoVentaSie tbPuntoVenta;

    public HorariosPvSie() {
    }

	public Integer getIdHorariosPv() {
		return this.idHorariosPv;
	}

	public void setIdHorariosPv(Integer idHorariosPv) {
		this.idHorariosPv = idHorariosPv;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getHoraEntrada() {
		return this.horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSalida() {
		return this.horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public EmpleadoSie getTbEmpleado1() {
		return this.tbEmpleado1;
	}

	public void setTbEmpleado1(EmpleadoSie tbEmpleado1) {
		this.tbEmpleado1 = tbEmpleado1;
	}
	
	public EmpleadoSie getTbEmpleado2() {
		return this.tbEmpleado2;
	}

	public void setTbEmpleado2(EmpleadoSie tbEmpleado2) {
		this.tbEmpleado2 = tbEmpleado2;
	}
	
	public EmpleadoSie getTbEmpleado3() {
		return this.tbEmpleado3;
	}

	public void setTbEmpleado3(EmpleadoSie tbEmpleado3) {
		this.tbEmpleado3 = tbEmpleado3;
	}
	
	public PuntoVentaSie getTbPuntoVenta() {
		return this.tbPuntoVenta;
	}

	public void setTbPuntoVenta(PuntoVentaSie tbPuntoVenta) {
		this.tbPuntoVenta = tbPuntoVenta;
	}
	
}