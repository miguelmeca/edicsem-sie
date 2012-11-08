package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the tb_filtro_horario_venta database table.
 * 
 */
@Entity
@Table(name="tb_filtro_horario_venta", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class FiltroHorarioVentaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_FILTRO_HORARIO_VENTA_IDFILTROHORARIO_GENERATOR", sequenceName="SIE.TB_FILTRO_HORARIO_VENTA_IDFILTROHORARIO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_FILTRO_HORARIO_VENTA_IDFILTROHORARIO_GENERATOR")
	private Integer idfiltrohorario;

    @Temporal( TemporalType.DATE)
	private Date diafin;

    @Temporal( TemporalType.DATE)
	private Date diainicio;

	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	@Column(name="hora_ingreso")
	private Time horaIngreso;

	@Column(name="hora_salida")
	private Time horaSalida;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	//bi-directional many-to-one association to FechaSie
    @ManyToOne
	@JoinColumn(name="idfecha")
	private FechaSie tbFecha;

	private String observacion;

	private String usuariocreacion;

	private String usuariomodifica;
	
	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;
    
	//bi-directional many-to-one association to PuntoVentaSie
    @ManyToOne
	@JoinColumn(name="idpuntoventa")
	private PuntoVentaSie tbPuntoventa;

	//bi-directional many-to-one association to TipoFiltroSie
    @ManyToOne
	@JoinColumn(name="tipofiltro")
	private TipoFiltroSie tbTipoFiltro;

    public FiltroHorarioVentaSie() {
    }

	public Integer getIdfiltrohorario() {
		return this.idfiltrohorario;
	}

	public void setIdfiltrohorario(Integer idfiltrohorario) {
		this.idfiltrohorario = idfiltrohorario;
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

	public TipoFiltroSie getTbTipoFiltro() {
		return this.tbTipoFiltro;
	}

	public void setTbTipoFiltro(TipoFiltroSie tbTipoFiltro) {
		this.tbTipoFiltro = tbTipoFiltro;
	}

	public FechaSie getTbFecha() {
		return tbFecha;
	}

	public void setTbFecha(FechaSie tbFecha) {
		this.tbFecha = tbFecha;
	}

	public PuntoVentaSie getTbPuntoventa() {
		return tbPuntoventa;
	}

	public void setTbPuntoventa(PuntoVentaSie tbPuntoventa) {
		this.tbPuntoventa = tbPuntoventa;
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
	
}