package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the tb_incidencia database table.
 * 
 */
@Entity
@Table(name="tb_incidencia", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class IncidenciaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_INCIDENCIA_IDINCIDENCIA_GENERATOR", sequenceName="SIE.TB_INCIDENCIA_IDINCIDENCIA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_INCIDENCIA_IDINCIDENCIA_GENERATOR")
	private Integer idincidencia;

	private String descripcion;

	private String detalle;
	
	private String usuariosenviomsj;
	
	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechafin;

	private Timestamp fechamodifica;
	
	//bi-directional many-to-one association to ContratoSie
    @ManyToOne
	@JoinColumn(name="idcontrato")
	private ContratoSie tbContrato;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to ObservacionIncidenciaSie
	@OneToMany(mappedBy="tbIncidencia")
	private Set<ObservacionIncidenciaSie> tbObservacionIncidencia;
	
    public IncidenciaSie() {
    }

	public Integer getIdincidencia() {
		return this.idincidencia;
	}

	public void setIdincidencia(Integer idincidencia) {
		this.idincidencia = idincidencia;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public Timestamp getFechamodifica() {
		return this.fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
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

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public Set<ObservacionIncidenciaSie> getTbObservacionIncidencia() {
		return tbObservacionIncidencia;
	}

	public void setTbObservacionIncidencia(
			Set<ObservacionIncidenciaSie> tbObservacionIncidencia) {
		this.tbObservacionIncidencia = tbObservacionIncidencia;
	}

	public ContratoSie getTbContrato() {
		return tbContrato;
	}

	public void setTbContrato(ContratoSie tbContrato) {
		this.tbContrato = tbContrato;
	}

	public String getUsuariosenviomsj() {
		return usuariosenviomsj;
	}

	public void setUsuariosenviomsj(String usuariosenviomsj) {
		this.usuariosenviomsj = usuariosenviomsj;
	}
	
}