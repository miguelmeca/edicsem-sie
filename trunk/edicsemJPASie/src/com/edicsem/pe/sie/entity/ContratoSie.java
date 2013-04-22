package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the tb_contrato database table.
 * 
 */
@Entity
@Table(name="tb_contrato", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ContratoSie implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CONTRATO_IDCONTRATO_GENERATOR", sequenceName="SIE.TB_CONTRATO_IDCONTRATO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CONTRATO_IDCONTRATO_GENERATOR")
	private Integer idcontrato;

	private String codcontrato;

	private BigDecimal cuotainicial;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechacuotainicial;

    @Temporal( TemporalType.DATE)
	private Date fechaentrega;

	private Timestamp fechamodifica;

	private String horallamada;

	private String lugarentrega;
	
	private String llamar;

	private Integer numcuotas;

	private BigDecimal pagomensual;

	private BigDecimal pagosubinicial;
	
	private String usuariocreacion;

	private String usuariomodifica;
	
	private String rutaImagenContrato;
	
	//bi-directional many-to-one association to CobranzaSie
	@OneToMany(mappedBy="tbContrato")
	private Set<CobranzaSie> tbCobranzas;

	//bi-directional many-to-one association to ClienteSie
    @ManyToOne
	@JoinColumn(name="idcliente")
	private ClienteSie tbCliente;

	//bi-directional many-to-one association to EmpresaSie
    @ManyToOne
	@JoinColumn(name="idempresa")
	private EmpresaSie tbEmpresa;
    
    //bi-directional many-to-one association to PuntoVentaSie
    @ManyToOne
	@JoinColumn(name="idpuntoventa")
	private PuntoVentaSie tbPuntoVenta;
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneralentrega")
	private EstadoGeneralSie tbEstadoGeneralEntrega;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
	//bi-directional many-to-one association to TipoEventoVentaSie
    @ManyToOne
	@JoinColumn(name="idtipoevento")
	private TipoEventoVentaSie tbTipoEvento;
    
    //bi-directional many-to-one association to LugarVentaSie
    @ManyToOne
	@JoinColumn(name="idlugar")
	private LugarVentaSie tbLugarVenta;

	//bi-directional many-to-one association to RutaArchivoEscaneadoSie
	@OneToMany(mappedBy="tbContrato")
	private Set<RutaArchivoEscaneadoSie> tbRutaArchivoEscaneados;
	
	//bi-directional many-to-one association to DetProductoContratoSie
  	@OneToMany(mappedBy="tbContrato")
  	private Set<DetProductoContratoSie> tbDetProductoContrato;
  	
  	//bi-directional many-to-one association to DetContratoEmpleadoSie
  	@OneToMany(mappedBy="tbContrato")
  	private Set<DetContratoEmpleadoSie> tbDetContratoEmpleados;
  	
  	//bi-directional many-to-one association to IncidenciaSie
  	@OneToMany(mappedBy="tbContrato")
  	private Set<IncidenciaSie> tbIncidencias;
  	
  	//bi-directional many-to-one association to SeguimientoContratoSie
  	@OneToMany(mappedBy="tbContrato")
  	private Set<SeguimientoContratoSie> tbSeguimientoContrato;
  	
  	//bi-directional many-to-one association to HistoricoObservacionesSie
  	@OneToMany(mappedBy="tbContrato")
  	private Set<HistoricoObservacionesSie> tbHistoricoObservacion;
  	
  	//bi-directional many-to-one association to RefinanciarPagoSie
  	@OneToMany(mappedBy="tbContrato")
  	private Set<RefinanciarPagoSie> tbRefinanciarPago;
  	
  	@Transient
	private String fechaCreacionString;
  	
  	@Transient
	private String fechaEntregaString;

    public ContratoSie() {
    }

	public Integer getIdcontrato() {
		return this.idcontrato;
	}

	public void setIdcontrato(Integer idcontrato) {
		this.idcontrato = idcontrato;
	}

	public String getCodcontrato() {
		return this.codcontrato;
	}

	public void setCodcontrato(String codcontrato) {
		this.codcontrato = codcontrato;
	}

	public BigDecimal getCuotainicial() {
		return this.cuotainicial;
	}

	public void setCuotainicial(BigDecimal cuotainicial) {
		this.cuotainicial = cuotainicial;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFechacuotainicial() {
		return this.fechacuotainicial;
	}

	public void setFechacuotainicial(Date fechacuotainicial) {
		this.fechacuotainicial = fechacuotainicial;
	}

	public Date getFechaentrega() {
		return this.fechaentrega;
	}

	public void setFechaentrega(Date fechaentrega) {
		this.fechaentrega = fechaentrega;
	}

	public Timestamp getFechamodifica() {
		return this.fechamodifica;
	}

	public void setFechamodifica(Timestamp fechamodifica) {
		this.fechamodifica = fechamodifica;
	}

	public String getHorallamada() {
		return this.horallamada;
	}

	public void setHorallamada(String horallamada) {
		this.horallamada = horallamada;
	}

	public String getLugarentrega() {
		return this.lugarentrega;
	}

	public void setLugarentrega(String lugarentrega) {
		this.lugarentrega = lugarentrega;
	}

	public Integer getNumcuotas() {
		return this.numcuotas;
	}

	public void setNumcuotas(Integer numcuotas) {
		this.numcuotas = numcuotas;
	}

	public BigDecimal getPagomensual() {
		return this.pagomensual;
	}

	public void setPagomensual(BigDecimal pagomensual) {
		this.pagomensual = pagomensual;
	}

	public BigDecimal getPagosubinicial() {
		return this.pagosubinicial;
	}

	public void setPagosubinicial(BigDecimal pagosubinicial) {
		this.pagosubinicial = pagosubinicial;
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

	public Set<CobranzaSie> getTbCobranzas() {
		return this.tbCobranzas;
	}

	public void setTbCobranzas(Set<CobranzaSie> tbCobranzas) {
		this.tbCobranzas = tbCobranzas;
	}
	
	public ClienteSie getTbCliente() {
		return this.tbCliente;
	}

	public void setTbCliente(ClienteSie tbCliente) {
		this.tbCliente = tbCliente;
	}
	
	public EmpresaSie getTbEmpresa() {
		return this.tbEmpresa;
	}

	public void setTbEmpresa(EmpresaSie tbEmpresa) {
		this.tbEmpresa = tbEmpresa;
	}
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
	public Set<RutaArchivoEscaneadoSie> getTbRutaArchivoEscaneados() {
		return this.tbRutaArchivoEscaneados;
	}

	public void setTbRutaArchivoEscaneados(Set<RutaArchivoEscaneadoSie> tbRutaArchivoEscaneados) {
		this.tbRutaArchivoEscaneados = tbRutaArchivoEscaneados;
	}

	public Set<DetProductoContratoSie> getTbDetProductoContrato() {
		return tbDetProductoContrato;
	}

	public void setTbDetProductoContrato(
			Set<DetProductoContratoSie> tbDetProductoContrato) {
		this.tbDetProductoContrato = tbDetProductoContrato;
	}

	/**
	 * @return the tbDetContratoEmpleados
	 */
	public Set<DetContratoEmpleadoSie> getTbDetContratoEmpleados() {
		return tbDetContratoEmpleados;
	}

	/**
	 * @param tbDetContratoEmpleados the tbDetContratoEmpleados to set
	 */
	public void setTbDetContratoEmpleados(
			Set<DetContratoEmpleadoSie> tbDetContratoEmpleados) {
		this.tbDetContratoEmpleados = tbDetContratoEmpleados;
	}

	public Set<IncidenciaSie> getTbIncidencias() {
		return tbIncidencias;
	}

	public void setTbIncidencias(Set<IncidenciaSie> tbIncidencias) {
		this.tbIncidencias = tbIncidencias;
	}

	/**
	 * @return the llamar
	 */
	public String getLlamar() {
		return llamar;
	}

	/**
	 * @param llamar the llamar to set
	 */
	public void setLlamar(String llamar) {
		this.llamar = llamar;
	}

	public Set<SeguimientoContratoSie> getTbSeguimientoContrato() {
		return tbSeguimientoContrato;
	}

	public void setTbSeguimientoContrato(
			Set<SeguimientoContratoSie> tbSeguimientoContrato) {
		this.tbSeguimientoContrato = tbSeguimientoContrato;
	}

	
	public PuntoVentaSie getTbPuntoVenta() {
		return tbPuntoVenta;
	}

	public void setTbPuntoVenta(PuntoVentaSie tbPuntoVenta) {
		this.tbPuntoVenta = tbPuntoVenta;
	}
	/**
	 * @return the fechaCreacionString
	 */
	public String getFechaCreacionString() {
		fechaCreacionString = DateUtil.formatoString(getFechacreacion(), "dd/MM/yyyy");
		return fechaCreacionString;
	}

	/**
	 * @param fechaCreacionString the fechaCreacionString to set
	 */
	public void setFechaCreacionString(String fechaCreacionString) {
		this.fechaCreacionString = fechaCreacionString;
	}

	/**
	 * @return the rutaImagenContrato
	 */
	public String getRutaImagenContrato() {
		return rutaImagenContrato;
	}

	/**
	 * @param rutaImagenContrato the rutaImagenContrato to set
	 */
	public void setRutaImagenContrato(String rutaImagenContrato) {
		this.rutaImagenContrato = rutaImagenContrato;
	}

	/**
	 * @return the tbEstadoGeneralEntrega
	 */
	public EstadoGeneralSie getTbEstadoGeneralEntrega() {
		return tbEstadoGeneralEntrega;
	}

	/**
	 * @param tbEstadoGeneralEntrega the tbEstadoGeneralEntrega to set
	 */
	public void setTbEstadoGeneralEntrega(EstadoGeneralSie tbEstadoGeneralEntrega) {
		this.tbEstadoGeneralEntrega = tbEstadoGeneralEntrega;
	}

	/**
	 * @return the fechaEntregaString
	 */
	public String getFechaEntregaString() {
		fechaEntregaString = DateUtil.formatoString(getFechaentrega(), "dd/MM/yyyy");
		return fechaEntregaString;
	}
	
	public void setFechaEntregaString(String fechaEntregaString) {
		this.fechaEntregaString = fechaEntregaString;
	}
	
	public TipoEventoVentaSie getTbTipoEvento() {
		return tbTipoEvento;
	}
	
	public void setTbTipoEvento(TipoEventoVentaSie tbTipoEvento) {
		this.tbTipoEvento = tbTipoEvento;
	}

	public LugarVentaSie getTbLugarVenta() {
		return tbLugarVenta;
	}

	public void setTbLugarVenta(LugarVentaSie tbLugarVenta) {
		this.tbLugarVenta = tbLugarVenta;
	}

	/**
	 * @return the tbHistoricoObservacion
	 */
	public Set<HistoricoObservacionesSie> getTbHistoricoObservacion() {
		return tbHistoricoObservacion;
	}

	/**
	 * @param tbHistoricoObservacion the tbHistoricoObservacion to set
	 */
	public void setTbHistoricoObservacion(Set<HistoricoObservacionesSie> tbHistoricoObservacion) {
		this.tbHistoricoObservacion = tbHistoricoObservacion;
	}

	public Set<RefinanciarPagoSie> getTbRefinanciarPago() {
		return tbRefinanciarPago;
	}

	public void setTbRefinanciarPago(Set<RefinanciarPagoSie> tbRefinanciarPago) {
		this.tbRefinanciarPago = tbRefinanciarPago;
	}

}