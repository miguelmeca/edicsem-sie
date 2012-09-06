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
 * The persistent class for the tb_contrato database table.
 * 
 */
@Entity
@Table(name="tb_contrato", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ContratoSie  extends BaseMantenimientoForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CONTRATO_IDCONTRATO_GENERATOR", sequenceName="SIE.TB_CONTRATO_IDCONTRATO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CONTRATO_IDCONTRATO_GENERATOR")
	private Integer idcontrato;

	private String codcontrato;

	private BigDecimal cuotainicial;

	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fechacuotainicial;

    @Temporal( TemporalType.DATE)
	private Date fechaentrega;

	private Timestamp fechamodifica;

	private String horallamada;

	private String lugarentrega;

	private Integer numcuotas;

	private BigDecimal pagomensual;

	private BigDecimal pagosubinicial;

	private String tipoventa;

	private String usuariocreacion;

	private String usuariomodifica;

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

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	//bi-directional many-to-one association to ContratoEmpleadoSie
	@OneToMany(mappedBy="tbContrato")
	private Set<ContratoEmpleadoSie> tbContratoEmpleados;

	//bi-directional many-to-one association to RutaArchivoEscaneadoSie
	@OneToMany(mappedBy="tbContrato")
	private Set<RutaArchivoEscaneadoSie> tbRutaArchivoEscaneados;

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

	public String getTipoventa() {
		return this.tipoventa;
	}

	public void setTipoventa(String tipoventa) {
		this.tipoventa = tipoventa;
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
	
	public Set<ContratoEmpleadoSie> getTbContratoEmpleados() {
		return this.tbContratoEmpleados;
	}

	public void setTbContratoEmpleados(Set<ContratoEmpleadoSie> tbContratoEmpleados) {
		this.tbContratoEmpleados = tbContratoEmpleados;
	}
	
	public Set<RutaArchivoEscaneadoSie> getTbRutaArchivoEscaneados() {
		return this.tbRutaArchivoEscaneados;
	}

	public void setTbRutaArchivoEscaneados(Set<RutaArchivoEscaneadoSie> tbRutaArchivoEscaneados) {
		this.tbRutaArchivoEscaneados = tbRutaArchivoEscaneados;
	}
	
}