package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;


/**
 * The persistent class for the tb_cobranza_operadora database table.
 * 
 */
@Entity
@Table(name="tb_cobranza_operadora", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class CobranzaOperadoraSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_COBRANZA_OPERADORA_IDCOBRANZAOPERADORA_GENERATOR" , sequenceName="SIE.TB_COBRANZA_OPERADORA_IDCOBRANZAOPERADORA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_COBRANZA_OPERADORA_IDCOBRANZAOPERADORA_GENERATOR")
	private Integer idCobranzaOperadora;

	private String observaciones;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;
	
	private String usuariomodifica;
	
	private Timestamp fechamodifica;
	
	private Timestamp fechaprogramada;
	
	private Date fechapromesapago;
	
	private Date fechaexpira;

	//bi-directional many-to-one association to CobranzaDTO
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="idcliente", referencedColumnName="idcliente"),
		@JoinColumn(name="idcobranza", referencedColumnName="idcobranza"),
		@JoinColumn(name="idcontrato", referencedColumnName="idcontrato")
		})
	private CobranzaSie tbCobranza;

	//bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;

	//bi-directional many-to-one association to TipoLlamadaSie
    @ManyToOne
	@JoinColumn(name="idtipo_llamada")
	private TipoLlamadaSie tbTipoLlamada;
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    @Transient
    private String fechaProgramadaString;

    public CobranzaOperadoraSie() {
    }

	public Integer getIdCobranzaOperadora() {
		return this.idCobranzaOperadora;
	}

	public void setIdCobranzaOperadora(Integer idCobranzaOperadora) {
		this.idCobranzaOperadora = idCobranzaOperadora;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public CobranzaSie getTbCobranza() {
		return this.tbCobranza;
	}

	public void setTbCobranza(CobranzaSie tbCobranza) {
		this.tbCobranza = tbCobranza;
	}
	
	public EmpleadoSie getTbEmpleado() {
		return this.tbEmpleado;
	}

	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}
	
	public TipoLlamadaSie getTbTipoLlamada() {
		return this.tbTipoLlamada;
	}

	public void setTbTipoLlamada(TipoLlamadaSie tbTipoLlamada) {
		this.tbTipoLlamada = tbTipoLlamada;
	}

	/**
	 * @return the fechacreacion
	 */
	public Timestamp getFechacreacion() {
		return fechacreacion;
	}

	/**
	 * @param fechacreacion the fechacreacion to set
	 */
	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public String getUsuariomodifica() {
		return usuariomodifica;
	}

	public void setUsuariomodifica(String usuariomodifica) {
		this.usuariomodifica = usuariomodifica;
	}

	public Timestamp getFechamodifica() {
		return fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public String getFechaProgramadaString() {
		fechaProgramadaString = DateUtil.formatoString(getFechaprogramada(), "dd/MM/yyyy HH:mm");
		return fechaProgramadaString;
	}

	public void setFechaProgramadaString(String fechaProgramadaString) {
		this.fechaProgramadaString = fechaProgramadaString;
	}

	public Date getFechapromesapago() {
		return fechapromesapago;
	}

	public void setFechapromesapago(Date fechapromesapago) {
		this.fechapromesapago = fechapromesapago;
	}

	public Timestamp getFechaprogramada() {
		return fechaprogramada;
	}

	public void setFechaprogramada(Timestamp fechaprogramada) {
		this.fechaprogramada = fechaprogramada;
	}

	public Date getFechaexpira() {
		return fechaexpira;
	}

	public void setFechaexpira(Date fechaexpira) {
		this.fechaexpira = fechaexpira;
	}
	
}