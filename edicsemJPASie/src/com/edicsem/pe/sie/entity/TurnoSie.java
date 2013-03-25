package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_turno database table.
 * 
 */
@Entity
@Table(name="tb_turno", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class TurnoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TURNO_IDTURNO_GENERATOR", sequenceName="SIE.TB_TURNO_IDTURNO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TURNO_IDTURNO_GENERATOR")
	private Integer idturno;
	
	private String descripcion;
    
	private Time horaInicio;
	
	private Time horaFin;
	
	//bi-directional many-to-one association to FechaSie
    @ManyToOne
	@JoinColumn(name="idfecha")
	private FechaSie tbFecha;
	  
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechamodifica;

	private String usuariocreacion;

	private String usuariomodifica;
    
    //bi-directional many-to-one association to TipoTurnoSie
    @ManyToOne
	@JoinColumn(name="idtipoturno")
	private TipoTurnoSie tbTipoTurno;
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    //bi-directional many-to-one association to DetTurnoEmplSie
  	@OneToMany(mappedBy="tbTurno")
  	private Set<DetTurnoEmplSie> tbDetTurnoEmpl;
    
    public TurnoSie() {
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
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public Integer getIdturno() {
		return idturno;
	}

	public void setIdturno(Integer idturno) {
		this.idturno = idturno;
	}

	public FechaSie getTbFecha() {
		return tbFecha;
	}

	public void setTbFecha(FechaSie tbFecha) {
		this.tbFecha = tbFecha;
	}

	public Time getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<DetTurnoEmplSie> getTbDetTurnoEmpl() {
		return tbDetTurnoEmpl;
	}

	public void setTbDetTurnoEmpl(Set<DetTurnoEmplSie> tbDetTurnoEmpl) {
		this.tbDetTurnoEmpl = tbDetTurnoEmpl;
	}

	public TipoTurnoSie getTbTipoTurno() {
		return tbTipoTurno;
	}

	public void setTbTipoTurno(TipoTurnoSie tbTipoTurno) {
		this.tbTipoTurno = tbTipoTurno;
	}
	
}