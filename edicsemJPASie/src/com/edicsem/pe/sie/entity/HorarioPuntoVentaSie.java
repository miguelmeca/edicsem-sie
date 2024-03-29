package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Time;
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
import javax.persistence.Transient;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;


/**
 * The persistent class for the tb_horario_punto_venta database table.
 * 
 */
@Entity
@Table(name="tb_horario_punto_venta", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class HorarioPuntoVentaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_HORARIO_PUNTO_VENTA_IDHORARIOPUNTO_GENERATOR", sequenceName="SIE.TB_HORARIO_PUNTO_VENTA_IDHORARIOPUNTO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_HORARIO_PUNTO_VENTA_IDHORARIOPUNTO_GENERATOR")
	private Integer idhorariopunto;

    @Temporal( TemporalType.DATE)
	private Date diafin;

    @Temporal( TemporalType.DATE)
	private Date diainicio;
    
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	@Column(name="hora_ingreso")
	private Time horaIngreso;

	@Column(name="hora_salida")
	private Time horaSalida;

	private String observacion;

	private String usuariocreacion;

	private String usuariomodifica;
	
	@Transient
	private String  fechaInicio;
	
	@Transient
	private String  fechahasta;

	//bi-directional many-to-one association to PuntoVenta
    @ManyToOne
	@JoinColumn(name="idpuntoventa")
	private PuntoVentaSie tbPuntoVenta;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    //bi-directional many-to-one association to FechaSie
    @ManyToOne
	@JoinColumn(name="idfecha")
	private FechaSie tbFecha;
    
    public HorarioPuntoVentaSie() {
    }

	public Integer getIdhorariopunto() {
		return this.idhorariopunto;
	}

	public void setIdhorariopunto(Integer idhorariopunto) {
		this.idhorariopunto = idhorariopunto;
	}

	public Date getDiafin() {
		return this.diafin;
	}

	public void setDiafin(Date diafin) {
		this.diafin = diafin;
	}

	public Date getDiainicio() {
		return this.diainicio;
	}

	public void setDiainicio(Date diainicio) {
		this.diainicio = diainicio;
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
	
	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
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

	public PuntoVentaSie getTbPuntoVenta() {
		return tbPuntoVenta;
	}

	public void setTbPuntoVenta(PuntoVentaSie tbPuntoVenta) {
		this.tbPuntoVenta = tbPuntoVenta;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	/**
	 * @return the tbFecha
	 */
	public FechaSie getTbFecha() {
		return tbFecha;
	}

	/**
	 * @param tbFecha the tbFecha to set
	 */
	public void setTbFecha(FechaSie tbFecha) {
		this.tbFecha = tbFecha;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		fechaInicio = DateUtil.convertDateToString(diainicio);
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechahasta
	 */
	public String getFechahasta() {
		fechahasta = DateUtil.convertDateToString(diafin);
		return fechahasta;
	}

	/**
	 * @param fechahasta the fechahasta to set
	 */
	public void setFechahasta(String fechahasta) {
		this.fechahasta = fechahasta;
	}
	
}