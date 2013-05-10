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
 * The persistent class for the tb_config_puntaje database table.
 * 
 */
@Entity
@Table(name="tb_config_puntaje", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ConfigPuntajeSie   implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_CONFIG_PUNTAJE_IDCONFIGPUNTAJE_GENERATOR", sequenceName="SIE.TB_CONFIG_PUNTAJE_IDCONFIGPUNTAJE_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CONFIG_PUNTAJE_IDCONFIGPUNTAJE_GENERATOR")
	private Integer idconfigpuntaje;
	
	private Integer valor;
	
	private String usuariocreacion;

	private String usuariomodifica;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;
	
	private Timestamp fechamodifica;
	
	//bi-directional many-to-one association to TipoClienteSie
    @ManyToOne
	@JoinColumn(name="idtipocliente")
	private TipoClienteSie tbTipoCliente;
    
    //bi-directional many-to-one association to ParametroActividadSie
    @ManyToOne
	@JoinColumn(name="idparametroactividad")
	private ParametroActividadSie tbParametroActividad;
    
    //bi-directional many-to-one association to CargoEmpleadoSie
    @ManyToOne
	@JoinColumn(name="idcargoempleado")
	private CargoEmpleadoSie tbCargoempleado;
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    public ConfigPuntajeSie() {
    }

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
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

	public TipoClienteSie getTbTipoCliente() {
		return tbTipoCliente;
	}

	public void setTbTipoCliente(TipoClienteSie tbTipoCliente) {
		this.tbTipoCliente = tbTipoCliente;
	}

	public ParametroActividadSie getTbParametroActividad() {
		return tbParametroActividad;
	}

	public void setTbParametroActividad(ParametroActividadSie tbParametroActividad) {
		this.tbParametroActividad = tbParametroActividad;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public CargoEmpleadoSie getTbCargoempleado() {
		return tbCargoempleado;
	}

	public void setTbCargoempleado(CargoEmpleadoSie tbCargoempleado) {
		this.tbCargoempleado = tbCargoempleado;
	}

	public Integer getIdconfigpuntaje() {
		return idconfigpuntaje;
	}

	public void setIdconfigpuntaje(Integer idconfigpuntaje) {
		this.idconfigpuntaje = idconfigpuntaje;
	}
	
}