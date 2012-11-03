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
 * The persistent class for the tb_horario_personal database table.
 * 
 */
@Entity
@Table(name="tb_horario_personal", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class HorarioPersonalSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_HORARIO_PERSONAL_ID_HORARIO_PERSONAL_GENERATOR", sequenceName="SIE.TB_HORARIO_PERSONAL_ID_HORARIO_PERSONAL_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_HORARIO_PERSONAL_ID_HORARIO_PERSONAL_GENERATOR")
	@Column(name="id_horario_personal")
	private Integer idHorarioPersonal;

	@Column(name="hora_ingreso")
	private Time horaIngreso;

	@Column(name="hora_salida")
	private Time horaSalida;
	
	@Temporal( TemporalType.DATE)
	private Date diafin;

    @Temporal( TemporalType.DATE)
	private Date diainicio;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;
    
    private String descripcion;
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    //bi-directional many-to-one association to FechaSie
    @ManyToOne
	@JoinColumn(name="idfecha")
	private FechaSie tbFecha;

    public HorarioPersonalSie() {
    }

	public Integer getIdHorarioPersonal() {
		return this.idHorarioPersonal;
	}

	public void setIdHorarioPersonal(Integer idHorarioPersonal) {
		this.idHorarioPersonal = idHorarioPersonal;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public Date getDiafin() {
		return diafin;
	}

	public void setDiafin(Date diafin) {
		this.diafin = diafin;
	}

	public Date getDiainicio() {
		return diainicio;
	}

	public void setDiainicio(Date diainicio) {
		this.diainicio = diainicio;
	}

	public FechaSie getTbFecha() {
		return tbFecha;
	}

	public void setTbFecha(FechaSie tbFecha) {
		this.tbFecha = tbFecha;
	}
	
}