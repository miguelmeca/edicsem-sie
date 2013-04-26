package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Transient;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.constants.DateUtil;


/**
 * The persistent class for the tb_cobranza database table.
 * 
 */
@Entity
@Table(name="tb_cobranza", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class CobranzaSie   implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TB_COBRANZA_IDCOBRANZA_GENERATOR", sequenceName="SIE.TB_COBRANZA_IDCOBRANZA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_COBRANZA_IDCOBRANZA_GENERATOR")
	private Integer idcobranza;
	
	private Integer idcliente;
	
	private Integer idcontrato;

	private String cantcuotas;

	private Integer diasretraso;

	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
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
	
	private BigDecimal importemasmora;
	
	private String nrooperacion;
	
	private Time horapago;

	private String lugarpago;
	
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
	
	@Transient
	private String fechaVencimientoString;
	
	@Transient
	private String fechaPagoString;
	
	@Transient
	private String nuevo;

	@Transient
	private String model;
	
    public CobranzaSie() {
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

	public Integer getIdcobranza() {
		return this.idcobranza;
	}
	public void setIdcobranza(Integer idcobranza) {
		this.idcobranza = idcobranza;
	}
	public Integer getIdcliente() {
		return this.idcliente;
	}
	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}
	public Integer getIdcontrato() {
		return this.idcontrato;
	}
	public void setIdcontrato(Integer idcontrato) {
		this.idcontrato = idcontrato;
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

	/**
	 * @return the fechaVencimientoString
	 */
	public String getFechaVencimientoString() {
		fechaVencimientoString = DateUtil.formatoString(getFecvencimiento(), "dd/MM/yyyy");
		return fechaVencimientoString;
	}

	/**
	 * @param fechaVencimientoString the fechaVencimientoString to set
	 */
	public void setFechaVencimientoString(String fechaVencimientoString) {
		this.fechaVencimientoString = fechaVencimientoString;
	}

	/**
	 * @return the fechaPagoString
	 */
	public String getFechaPagoString() {
		fechaPagoString = DateUtil.formatoString(getFecpago(), "dd/MM/yyyy");
		return fechaPagoString;
	}

	/**
	 * @param fechaPagoString the fechaPagoString to set
	 */
	public void setFechaPagoString(String fechaPagoString) {
		this.fechaPagoString = fechaPagoString;
	}

	/**
	 * @return the importemasmora
	 */
	public BigDecimal getImportemasmora() {
		return importemasmora;
	}

	/**
	 * @param importemasmora the importemasmora to set
	 */
	public void setImportemasmora(BigDecimal importemasmora) {
		this.importemasmora = importemasmora;
	}

	/**
	 * @return the nuevo
	 */
	public String getNuevo() {
		return nuevo;
	}

	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(String nuevo) {
		this.nuevo = nuevo;
	}

	public Time getHorapago() {
		return horapago;
	}

	public void setHorapago(Time horapago) {
		this.horapago = horapago;
	}

	public String getNrooperacion() {
		return nrooperacion;
	}

	public void setNrooperacion(String nrooperacion) {
		this.nrooperacion = nrooperacion;
	}

	public String getLugarpago() {
		return lugarpago;
	}

	public void setLugarpago(String lugarpago) {
		this.lugarpago = lugarpago;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
}