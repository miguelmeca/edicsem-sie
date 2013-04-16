package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;


/**
 * The persistent class for the tb_horario_personal database table.
 * 
 */
@Entity
@Table(name="tb_horario_personal", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class HorarioPersonalSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_HORARIO_PERSONAL_IDHORARIOPERSONAL_GENERATOR", sequenceName="SIE.TB_HORARIO_PERSONAL_IDHORARIOPERSONAL_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_HORARIO_PERSONAL_IDHORARIOPERSONAL_GENERATOR")
	private Integer idhorariopersonal;

    private String descripcion;
    
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
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    //bi-directional many-to-one association to FechaSie
    @ManyToOne
	@JoinColumn(name="idfecha")
	private FechaSie tbFecha;
    
    @Transient
    private String diaInicioString;
    
    @Transient
    private String diaFinString;
    
    @Transient
    private List<String> diaString;
    
    public HorarioPersonalSie() {
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

	public Integer getIdhorariopersonal() {
		return idhorariopersonal;
	}
	
	public void setIdhorariopersonal(Integer idhorariopersonal) {
		this.idhorariopersonal = idhorariopersonal;
	}
	
	public String getDiaInicioString() {
		diaInicioString = DateUtil.formatoString(getDiainicio(), "dd/MM/yyyy");
		return diaInicioString;
	}
	
	public void setDiaInicioString(String diaInicioString) {
		this.diaInicioString = diaInicioString;
	}
	
	public String getDiaFinString() {
		diaFinString = DateUtil.formatoString(getDiafin(), "dd/MM/yyyy");
		return diaFinString;
	}
	
	public void setDiaFinString(String diaFinString) {
		this.diaFinString = diaFinString;
	}

	public List<String> getDiaString() {
		return diaString;
	}

	public void setDiaString(List<String> diaString) {
		this.diaString = diaString;
	}
	
}