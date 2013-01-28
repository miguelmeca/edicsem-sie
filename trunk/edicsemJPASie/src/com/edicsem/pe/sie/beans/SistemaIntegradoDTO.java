package com.edicsem.pe.sie.beans;

import java.io.Serializable;
import java.util.Date;

import com.edicsem.pe.sie.util.constants.DateUtil;


public class SistemaIntegradoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String apematcliente;

	private String apepatcliente;

	private String nombrecliente;

	private String cargotrabajo;

	private String contrasena;

	private String correo;

	private String directrabajo;

	private String empresatrabajo;
	
	private Date fecnacimiento;
	
	private String fechaNacimientoString;

	private String numdocumento;

	private String telftrabajo;
	
	private String anexo;

	private String titulartelefono;
	
	private String empresa;
	
	private Date fechaEntrega;
	
	private String codContrato;
	
	private String numTelefono;
	
	private String direccion;
	
	private String distrito;
	
	private Integer tipocliente;
	
	private String planoDistrito;
	
	private String letraSector;
	
	private String numSector;
	
	private String planoTrabajo;
	
	private String letraSectorTrabajo;
	
	private String numSectorTrabajo;
	
	private Integer cantMercaderia;
	
	private String tipoMercaderia;
	
	private Integer cantCuotas;
	
	private String numLetra;
	
	private double importeInicial;
	
	private double importeCobrado;
	
	private double importemasmora;
	
	private Date fechaVencimiento;
	
	private String lugarPago;
	
	private String banco;
	
	private String zonaCobranza;
	
	private Date fehaPago;
	
	private String encargadoCobranza;
	
	private String historia;

    public SistemaIntegradoDTO() {
    	
    }

	/**
	 * @return the apematcliente
	 */
	public String getApematcliente() {
		return apematcliente;
	}

	/**
	 * @param apematcliente the apematcliente to set
	 */
	public void setApematcliente(String apematcliente) {
		this.apematcliente = apematcliente;
	}

	/**
	 * @return the apepatcliente
	 */
	public String getApepatcliente() {
		return apepatcliente;
	}

	/**
	 * @param apepatcliente the apepatcliente to set
	 */
	public void setApepatcliente(String apepatcliente) {
		this.apepatcliente = apepatcliente;
	}

	/**
	 * @return the nombrecliente
	 */
	public String getNombrecliente() {
		return nombrecliente;
	}

	/**
	 * @param nombrecliente the nombrecliente to set
	 */
	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	/**
	 * @return the cargotrabajo
	 */
	public String getCargotrabajo() {
		return cargotrabajo;
	}

	/**
	 * @param cargotrabajo the cargotrabajo to set
	 */
	public void setCargotrabajo(String cargotrabajo) {
		this.cargotrabajo = cargotrabajo;
	}

	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the directrabajo
	 */
	public String getDirectrabajo() {
		return directrabajo;
	}

	/**
	 * @param directrabajo the directrabajo to set
	 */
	public void setDirectrabajo(String directrabajo) {
		this.directrabajo = directrabajo;
	}

	/**
	 * @return the empresatrabajo
	 */
	public String getEmpresatrabajo() {
		return empresatrabajo;
	}

	/**
	 * @param empresatrabajo the empresatrabajo to set
	 */
	public void setEmpresatrabajo(String empresatrabajo) {
		this.empresatrabajo = empresatrabajo;
	}

	/**
	 * @return the fecnacimiento
	 */
	public Date getFecnacimiento() {
		return fecnacimiento;
	}

	/**
	 * @param fecnacimiento the fecnacimiento to set
	 */
	public void setFecnacimiento(Date fecnacimiento) {
		this.fecnacimiento = fecnacimiento;
	}

	/**
	 * @return the numdocumento
	 */
	public String getNumdocumento() {
		return numdocumento;
	}

	/**
	 * @param numdocumento the numdocumento to set
	 */
	public void setNumdocumento(String numdocumento) {
		this.numdocumento = numdocumento;
	}

	/**
	 * @return the telftrabajo
	 */
	public String getTelftrabajo() {
		return telftrabajo;
	}

	/**
	 * @param telftrabajo the telftrabajo to set
	 */
	public void setTelftrabajo(String telftrabajo) {
		this.telftrabajo = telftrabajo;
	}

	/**
	 * @return the anexo
	 */
	public String getAnexo() {
		return anexo;
	}

	/**
	 * @param anexo the anexo to set
	 */
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	/**
	 * @return the titulartelefono
	 */
	public String getTitulartelefono() {
		return titulartelefono;
	}

	/**
	 * @param titulartelefono the titulartelefono to set
	 */
	public void setTitulartelefono(String titulartelefono) {
		this.titulartelefono = titulartelefono;
	}

	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return the codContrato
	 */
	public String getCodContrato() {
		return codContrato;
	}

	/**
	 * @param codContrato the codContrato to set
	 */
	public void setCodContrato(String codContrato) {
		this.codContrato = codContrato;
	}

	/**
	 * @return the numTelefono
	 */
	public String getNumTelefono() {
		return numTelefono;
	}

	/**
	 * @param numTelefono the numTelefono to set
	 */
	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the distrito
	 */
	public String getDistrito() {
		return distrito;
	}

	/**
	 * @param distrito the distrito to set
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	/**
	 * @return the tipocliente
	 */
	public Integer getTipocliente() {
		return tipocliente;
	}

	/**
	 * @param tipocliente the tipocliente to set
	 */
	public void setTipocliente(Integer tipocliente) {
		this.tipocliente = tipocliente;
	}

	/**
	 * @return the planoDistrito
	 */
	public String getPlanoDistrito() {
		return planoDistrito;
	}

	/**
	 * @param planoDistrito the planoDistrito to set
	 */
	public void setPlanoDistrito(String planoDistrito) {
		this.planoDistrito = planoDistrito;
	}

	/**
	 * @return the letraSector
	 */
	public String getLetraSector() {
		return letraSector;
	}

	/**
	 * @param letraSector the letraSector to set
	 */
	public void setLetraSector(String letraSector) {
		this.letraSector = letraSector;
	}

	/**
	 * @return the numSector
	 */
	public String getNumSector() {
		return numSector;
	}

	/**
	 * @param numSector the numSector to set
	 */
	public void setNumSector(String numSector) {
		this.numSector = numSector;
	}

	/**
	 * @return the planoTrabajo
	 */
	public String getPlanoTrabajo() {
		return planoTrabajo;
	}

	/**
	 * @param planoTrabajo the planoTrabajo to set
	 */
	public void setPlanoTrabajo(String planoTrabajo) {
		this.planoTrabajo = planoTrabajo;
	}

	/**
	 * @return the letraSectorTrabajo
	 */
	public String getLetraSectorTrabajo() {
		return letraSectorTrabajo;
	}

	/**
	 * @param letraSectorTrabajo the letraSectorTrabajo to set
	 */
	public void setLetraSectorTrabajo(String letraSectorTrabajo) {
		this.letraSectorTrabajo = letraSectorTrabajo;
	}

	/**
	 * @return the numSectorTrabajo
	 */
	public String getNumSectorTrabajo() {
		return numSectorTrabajo;
	}

	/**
	 * @param numSectorTrabajo the numSectorTrabajo to set
	 */
	public void setNumSectorTrabajo(String numSectorTrabajo) {
		this.numSectorTrabajo = numSectorTrabajo;
	}

	/**
	 * @return the cantMercaderia
	 */
	public Integer getCantMercaderia() {
		return cantMercaderia;
	}

	/**
	 * @param cantMercaderia the cantMercaderia to set
	 */
	public void setCantMercaderia(Integer cantMercaderia) {
		this.cantMercaderia = cantMercaderia;
	}

	/**
	 * @return the tipoMercaderia
	 */
	public String getTipoMercaderia() {
		return tipoMercaderia;
	}

	/**
	 * @param tipoMercaderia the tipoMercaderia to set
	 */
	public void setTipoMercaderia(String tipoMercaderia) {
		this.tipoMercaderia = tipoMercaderia;
	}

	/**
	 * @return the cantCuotas
	 */
	public Integer getCantCuotas() {
		return cantCuotas;
	}

	/**
	 * @param cantCuotas the cantCuotas to set
	 */
	public void setCantCuotas(Integer cantCuotas) {
		this.cantCuotas = cantCuotas;
	}

	/**
	 * @return the numLetra
	 */
	public String getNumLetra() {
		return numLetra;
	}

	/**
	 * @param numLetra the numLetra to set
	 */
	public void setNumLetra(String numLetra) {
		this.numLetra = numLetra;
	}

	/**
	 * @return the importeInicial
	 */
	public double getImporteInicial() {
		return importeInicial;
	}

	/**
	 * @param importeInicial the importeInicial to set
	 */
	public void setImporteInicial(double importeInicial) {
		this.importeInicial = importeInicial;
	}

	/**
	 * @return the importeCobrado
	 */
	public double getImporteCobrado() {
		return importeCobrado;
	}

	/**
	 * @param importeCobrado the importeCobrado to set
	 */
	public void setImporteCobrado(double importeCobrado) {
		this.importeCobrado = importeCobrado;
	}

	/**
	 * @return the importemasmora
	 */
	public double getImportemasmora() {
		return importemasmora;
	}

	/**
	 * @param importemasmora the importemasmora to set
	 */
	public void setImportemasmora(double importemasmora) {
		this.importemasmora = importemasmora;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the lugarPago
	 */
	public String getLugarPago() {
		return lugarPago;
	}

	/**
	 * @param lugarPago the lugarPago to set
	 */
	public void setLugarPago(String lugarPago) {
		this.lugarPago = lugarPago;
	}

	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * @return the zonaCobranza
	 */
	public String getZonaCobranza() {
		return zonaCobranza;
	}

	/**
	 * @param zonaCobranza the zonaCobranza to set
	 */
	public void setZonaCobranza(String zonaCobranza) {
		this.zonaCobranza = zonaCobranza;
	}

	/**
	 * @return the fehaPago
	 */
	public Date getFehaPago() {
		return fehaPago;
	}

	/**
	 * @param fehaPago the fehaPago to set
	 */
	public void setFehaPago(Date fehaPago) {
		this.fehaPago = fehaPago;
	}

	/**
	 * @return the encargadoCobranza
	 */
	public String getEncargadoCobranza() {
		return encargadoCobranza;
	}

	/**
	 * @param encargadoCobranza the encargadoCobranza to set
	 */
	public void setEncargadoCobranza(String encargadoCobranza) {
		this.encargadoCobranza = encargadoCobranza;
	}

	/**
	 * @return the fechaNacimientoString
	 */
	public String getFechaNacimientoString() {
		fechaNacimientoString = DateUtil.formatoString(getFecnacimiento(), "dd/MM/yyyy");
		return fechaNacimientoString;
	}

	/**
	 * @param fechaNacimientoString the fechaNacimientoString to set
	 */
	public void setFechaNacimientoString(String fechaNacimientoString) {
		this.fechaNacimientoString = fechaNacimientoString;
	}
    
}