package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_cita database table.
 * 
 */
@Entity
@Table(name="tb_cita", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class CitaSie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_CITA_IDCITA_GENERATOR", sequenceName="SIE.TB_CITA_IDCITA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CITA_IDCITA_GENERATOR")
	private Integer idcita;

	//bi-directional many-to-one association to ClienteSie
    @ManyToOne
	@JoinColumn(name="idcliente")
	private ClienteSie idcliente;
    
    //bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie idempleado;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    private String usuariocreacion;

	private String usuariomodifica;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	public CitaSie() {
    }

	public Integer getIdcita() {
		return idcita;
	}

	public void setIdcita(Integer idcita) {
		this.idcita = idcita;
	}

	public ClienteSie getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(ClienteSie idcliente) {
		this.idcliente = idcliente;
	}

	public EmpleadoSie getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(EmpleadoSie idempleado) {
		this.idempleado = idempleado;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
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

	public Timestamp getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodifica() {
		return fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
	}
}