package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tb_cliente database table.
 * 
 */
@Entity
@Table(name="tb_cliente", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ClienteSie  extends BaseMantenimientoForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_CLIENTE_IDCLIENTE_GENERATOR", sequenceName="SIE.TB_CLIENTE_IDCLIENTE_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_CLIENTE_IDCLIENTE_GENERATOR")
	private Integer idcliente;

	private String apematcliente;

	private String apepatcliente;

	private String cargotrabajo;

	private String contrasena;

	private String correo;

	private String directrabajo;

	private String empresatrabajo;

	private Timestamp fechacreacion;

	private String fecnacimiento;

	private String nombrecliente;

	private String numdocumento;

	private String telftrabajo;

	private String titulartelefono;

	//bi-directional many-to-one association to DomicilioPersonaSie
    @ManyToOne
	@JoinColumn(name="iddomiciliocliente")
	private DomicilioPersonaSie tbDomicilioPersona;

	//bi-directional many-to-one association to TelefonoPersonaSie
    @ManyToOne
	@JoinColumn(name="idtelefonocliente")
	private TelefonoPersonaSie tbTelefonoPersona;

	//bi-directional many-to-one association to TipoDocumentoIdentidadSie
    @ManyToOne
	@JoinColumn(name="idtipodocumentoidentidad")
	private TipoDocumentoIdentidadSie tbTipoDocumentoIdentidad;

	//bi-directional many-to-one association to CobranzaSie
	@OneToMany(mappedBy="tbCliente")
	private Set<CobranzaSie> tbCobranzas;

	//bi-directional many-to-one association to ContratoSie
	@OneToMany(mappedBy="tbCliente")
	private Set<ContratoSie> tbContratos;

    public ClienteSie() {
    }

	public Integer getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}

	public String getApematcliente() {
		return this.apematcliente;
	}

	public void setApematcliente(String apematcliente) {
		this.apematcliente = apematcliente;
	}

	public String getApepatcliente() {
		return this.apepatcliente;
	}

	public void setApepatcliente(String apepatcliente) {
		this.apepatcliente = apepatcliente;
	}

	public String getCargotrabajo() {
		return this.cargotrabajo;
	}

	public void setCargotrabajo(String cargotrabajo) {
		this.cargotrabajo = cargotrabajo;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDirectrabajo() {
		return this.directrabajo;
	}

	public void setDirectrabajo(String directrabajo) {
		this.directrabajo = directrabajo;
	}

	public String getEmpresatrabajo() {
		return this.empresatrabajo;
	}

	public void setEmpresatrabajo(String empresatrabajo) {
		this.empresatrabajo = empresatrabajo;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getFecnacimiento() {
		return this.fecnacimiento;
	}

	public void setFecnacimiento(String fecnacimiento) {
		this.fecnacimiento = fecnacimiento;
	}

	public String getNombrecliente() {
		return this.nombrecliente;
	}

	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	public String getNumdocumento() {
		return this.numdocumento;
	}

	public void setNumdocumento(String numdocumento) {
		this.numdocumento = numdocumento;
	}

	public String getTelftrabajo() {
		return this.telftrabajo;
	}

	public void setTelftrabajo(String telftrabajo) {
		this.telftrabajo = telftrabajo;
	}

	public String getTitulartelefono() {
		return this.titulartelefono;
	}

	public void setTitulartelefono(String titulartelefono) {
		this.titulartelefono = titulartelefono;
	}

	public DomicilioPersonaSie getTbDomicilioPersona() {
		return this.tbDomicilioPersona;
	}

	public void setTbDomicilioPersona(DomicilioPersonaSie tbDomicilioPersona) {
		this.tbDomicilioPersona = tbDomicilioPersona;
	}
	
	public TelefonoPersonaSie getTbTelefonoPersona() {
		return this.tbTelefonoPersona;
	}

	public void setTbTelefonoPersona(TelefonoPersonaSie tbTelefonoPersona) {
		this.tbTelefonoPersona = tbTelefonoPersona;
	}
	
	public TipoDocumentoIdentidadSie getTbTipoDocumentoIdentidad() {
		return this.tbTipoDocumentoIdentidad;
	}

	public void setTbTipoDocumentoIdentidad(TipoDocumentoIdentidadSie tbTipoDocumentoIdentidad) {
		this.tbTipoDocumentoIdentidad = tbTipoDocumentoIdentidad;
	}
	
	public Set<CobranzaSie> getTbCobranzas() {
		return this.tbCobranzas;
	}

	public void setTbCobranzas(Set<CobranzaSie> tbCobranzas) {
		this.tbCobranzas = tbCobranzas;
	}
	
	public Set<ContratoSie> getTbContratos() {
		return this.tbContratos;
	}

	public void setTbContratos(Set<ContratoSie> tbContratos) {
		this.tbContratos = tbContratos;
	}
	
}