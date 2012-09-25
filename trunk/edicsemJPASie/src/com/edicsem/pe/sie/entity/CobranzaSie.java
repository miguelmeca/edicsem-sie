package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the tb_cobranza database table.
 * 
 */
@Entity
@Table(name="tb_cobranza", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class CobranzaSie   implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CobranzaSiePK id;

	private String cantcuotas;

	private Integer diasretraso;

	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

    @Temporal( TemporalType.DATE)
	private Date fecpago;

    @Temporal( TemporalType.DATE)
	private Date fecvencimiento;

	private BigDecimal impcobrado;

	private BigDecimal impinicial;

	private String infocorp;

	private String numletra;

	private String pago;

    @Temporal( TemporalType.DATE)
	private Date registroreniec;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to ClienteSie
    @ManyToOne
	@JoinColumn(name="idcliente" , insertable = false, updatable = false)
	private ClienteSie tbCliente;

	//bi-directional many-to-one association to ContratoSie
    @ManyToOne
	@JoinColumn(name="idcontrato", insertable = false, updatable = false)
	private ContratoSie tbContrato;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	//bi-directional many-to-one association to CobranzaOperadoraSie
	@OneToMany(mappedBy="tbCobranza")
	private Set<CobranzaOperadoraSie> tbCobranzaOperadoras;

    public CobranzaSie() {
    }

	public CobranzaSiePK getId() {
		return this.id;
	}

	public void setId(CobranzaSiePK id) {
		this.id = id;
	}
	
	public String getCantcuotas() {
		return this.cantcuotas;
	}

	public void setCantcuotas(String cantcuotas) {
		this.cantcuotas = cantcuotas;
	}

	public Integer getDiasretraso() {
		return this.diasretraso;
	}

	public void setDiasretraso(Integer diasretraso) {
		this.diasretraso = diasretraso;
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

	public Date getFecpago() {
		return this.fecpago;
	}

	public void setFecpago(Date fecpago) {
		this.fecpago = fecpago;
	}

	public Date getFecvencimiento() {
		return this.fecvencimiento;
	}

	public void setFecvencimiento(Date fecvencimiento) {
		this.fecvencimiento = fecvencimiento;
	}

	public BigDecimal getImpcobrado() {
		return this.impcobrado;
	}

	public void setImpcobrado(BigDecimal impcobrado) {
		this.impcobrado = impcobrado;
	}

	public BigDecimal getImpinicial() {
		return this.impinicial;
	}

	public void setImpinicial(BigDecimal impinicial) {
		this.impinicial = impinicial;
	}

	public String getInfocorp() {
		return this.infocorp;
	}

	public void setInfocorp(String infocorp) {
		this.infocorp = infocorp;
	}

	public String getNumletra() {
		return this.numletra;
	}

	public void setNumletra(String numletra) {
		this.numletra = numletra;
	}

	public String getPago() {
		return this.pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}

	public Date getRegistroreniec() {
		return this.registroreniec;
	}

	public void setRegistroreniec(Date registroreniec) {
		this.registroreniec = registroreniec;
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

	public ClienteSie getTbCliente() {
		return this.tbCliente;
	}

	public void setTbCliente(ClienteSie tbCliente) {
		this.tbCliente = tbCliente;
	}
	
	public ContratoSie getTbContrato() {
		return this.tbContrato;
	}

	public void setTbContrato(ContratoSie tbContrato) {
		this.tbContrato = tbContrato;
	}
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
	public Set<CobranzaOperadoraSie> getTbCobranzaOperadoras() {
		return this.tbCobranzaOperadoras;
	}

	public void setTbCobranzaOperadoras(Set<CobranzaOperadoraSie> tbCobranzaOperadoras) {
		this.tbCobranzaOperadoras = tbCobranzaOperadoras;
	}
	
}