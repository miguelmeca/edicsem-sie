package com.edicsem.pe.sie.beans;

import java.io.Serializable;
import java.util.Date;

import com.edicsem.pe.sie.util.constants.DateUtil;


public class EntregasPeruDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	private String empresa;
	
	private Date fecha;
	
	private String fechaString;
	
	private String boleta;
	
	private String numerodecontrato;
	
	private String nombredecliente;
	
	private String apepatcliente;
	
	private String apematcliente;
	
	private String dnidelcliente;
	
	private Date fechadecuempleanios;
	
	private String fechadecuempleaniosString;
	
	private String correo;
	
	private String numerotelefono;
	
	private String domiciliodelcliente;
	
	private String domiciliodistrito;
	
	private String planodomicilio;
	
	private String letrasectordomicilio;
	
	private String numerosectordomicilio;	
	
	private String lugardetrabajo;
	
	private String cargolaboral;
	
	private String telefonodeltrabajo;
	
	private int anexo;
	
	private String direcciondetrabajo;
	
	private String trabajodistrito;
	
	private int planotrabajo;
	
	private String letrasectortrabajo;
	
	private int numerosectortrabajo;
	
	private String lugardelaentrega;
	
	private String nombredelvendedor;
	
	private String nombredelexpositor;
	
	private String nombredelsupervisor;
	
	private int cantidaddemercaderia;
	
	private String codigodemercaderia;
	
	private int montodeadelanto;
	
	private String puntodeventa;
	
	private String nombredelrelacionista;
	
	private String distritodelpunto;
	
	private String eventodeventa;
	
	private Date fechadecompromiso;
	
	private int dosde;
	
	private int doshasta;
	
	private String encargadodelanetrega;
	
	private Date fechadellamadaovisita;
	
	private Date fechapostergada;
	
	private int tresde;
	
	private int treshasta;
	
	private Date fechafinal;
	
	private String estadofinal;
	
	private String observaciones;
	
	private String nombredelpatrocinado;
	
	private int comisiondelvendedor;
	
	private Date fechadepagoalvendedor;
	
	private int comisiondelexpositor;
	
	private Date fechadepagoalexpositor;
	
	private int comisiondelrelacionista;
	
	private Date fechadepagoalrelacionista;
	
	private int comisiondelsupervisor;
	
	private Date fechadepagoalsupervisor;
	
	private int comisiondelpatrocinador;
	
	private Date fechadepagoalpatrocinador;
	
	private int preciototal;
	
	private int puntaje;

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
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the boleta
	 */

	
	


	/**
	 * @return the numerodecontrato
	 */
	public String getNumerodecontrato() {
		return numerodecontrato;
	}

	/**
	 * @return the boleta
	 */
	public String getBoleta() {
		return boleta;
	}

	/**
	 * @param boleta the boleta to set
	 */
	public void setBoleta(String boleta) {
		this.boleta = boleta;
	}

	/**
	 * @param numerodecontrato the numerodecontrato to set
	 */
	public void setNumerodecontrato(String numerodecontrato) {
		this.numerodecontrato = numerodecontrato;
	}

	/**
	 * @return the nombredecliente
	 */
	public String getNombredecliente() {
		return nombredecliente;
	}

	/**
	 * @param nombredecliente the nombredecliente to set
	 */
	public void setNombredecliente(String nombredecliente) {
		this.nombredecliente = nombredecliente;
	}



	/**
	 * @return the dnidelcliente
	 */
	public String getDnidelcliente() {
		return dnidelcliente;
	}

	/**
	 * @param dnidelcliente the dnidelcliente to set
	 */
	public void setDnidelcliente(String dnidelcliente) {
		this.dnidelcliente = dnidelcliente;
	}

	/**
	 * @return the fechadecuempleanios
	 */
	public Date getFechadecuempleanios() {
		return fechadecuempleanios;
	}

	/**
	 * @param fechadecuempleanios the fechadecuempleanios to set
	 */
	public void setFechadecuempleanios(Date fechadecuempleanios) {
		this.fechadecuempleanios = fechadecuempleanios;
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
	 * @return the numerotelefono
	 */


	/**
	 * @return the domiciliodelcliente
	 */
	public String getDomiciliodelcliente() {
		return domiciliodelcliente;
	}

	/**
	 * @return the numerotelefono
	 */
	public String getNumerotelefono() {
		return numerotelefono;
	}

	/**
	 * @param numerotelefono the numerotelefono to set
	 */
	public void setNumerotelefono(String numerotelefono) {
		this.numerotelefono = numerotelefono;
	}

	/**
	 * @param domiciliodelcliente the domiciliodelcliente to set
	 */
	public void setDomiciliodelcliente(String domiciliodelcliente) {
		this.domiciliodelcliente = domiciliodelcliente;
	}

	/**
	 * @return the domiciliodistrito
	 */
	public String getDomiciliodistrito() {
		return domiciliodistrito;
	}

	/**
	 * @param domiciliodistrito the domiciliodistrito to set
	 */
	public void setDomiciliodistrito(String domiciliodistrito) {
		this.domiciliodistrito = domiciliodistrito;
	}

	/**
	 * @return the planodomicilio
	 */
	

	/**
	 * @return the letrasectordomicilio
	 */
	public String getLetrasectordomicilio() {
		return letrasectordomicilio;
	}

	/**
	 * @param letrasectordomicilio the letrasectordomicilio to set
	 */
	public void setLetrasectordomicilio(String letrasectordomicilio) {
		this.letrasectordomicilio = letrasectordomicilio;
	}

	/**
	 * @return the numerosectordomicilio
	 */
	

	/**
	 * @return the lugardetrabajo
	 */
	public String getLugardetrabajo() {
		return lugardetrabajo;
	}

	/**
	 * @return the planodomicilio
	 */
	public String getPlanodomicilio() {
		return planodomicilio;
	}

	/**
	 * @param planodomicilio the planodomicilio to set
	 */
	public void setPlanodomicilio(String planodomicilio) {
		this.planodomicilio = planodomicilio;
	}

	/**
	 * @return the numerosectordomicilio
	 */
	public String getNumerosectordomicilio() {
		return numerosectordomicilio;
	}

	/**
	 * @param numerosectordomicilio the numerosectordomicilio to set
	 */
	public void setNumerosectordomicilio(String numerosectordomicilio) {
		this.numerosectordomicilio = numerosectordomicilio;
	}

	/**
	 * @param lugardetrabajo the lugardetrabajo to set
	 */
	public void setLugardetrabajo(String lugardetrabajo) {
		this.lugardetrabajo = lugardetrabajo;
	}

	/**
	 * @return the cargolaboral
	 */
	public String getCargolaboral() {
		return cargolaboral;
	}

	/**
	 * @param cargolaboral the cargolaboral to set
	 */
	public void setCargolaboral(String cargolaboral) {
		this.cargolaboral = cargolaboral;
	}

	/**
	 * @return the telefonodeltrabajo
	 */


	/**
	 * @return the anexo
	 */
	public int getAnexo() {
		return anexo;
	}

	/**
	 * @return the telefonodeltrabajo
	 */
	public String getTelefonodeltrabajo() {
		return telefonodeltrabajo;
	}

	/**
	 * @param telefonodeltrabajo the telefonodeltrabajo to set
	 */
	public void setTelefonodeltrabajo(String telefonodeltrabajo) {
		this.telefonodeltrabajo = telefonodeltrabajo;
	}

	/**
	 * @param anexo the anexo to set
	 */
	public void setAnexo(int anexo) {
		this.anexo = anexo;
	}

	/**
	 * @return the direcciondetrabajo
	 */
	public String getDirecciondetrabajo() {
		return direcciondetrabajo;
	}

	/**
	 * @param direcciondetrabajo the direcciondetrabajo to set
	 */
	public void setDirecciondetrabajo(String direcciondetrabajo) {
		this.direcciondetrabajo = direcciondetrabajo;
	}

	/**
	 * @return the trabajodistrito
	 */
	public String getTrabajodistrito() {
		return trabajodistrito;
	}

	/**
	 * @param trabajodistrito the trabajodistrito to set
	 */
	public void setTrabajodistrito(String trabajodistrito) {
		this.trabajodistrito = trabajodistrito;
	}

	/**
	 * @return the planotrabajo
	 */
	public int getPlanotrabajo() {
		return planotrabajo;
	}

	/**
	 * @param planotrabajo the planotrabajo to set
	 */
	public void setPlanotrabajo(int planotrabajo) {
		this.planotrabajo = planotrabajo;
	}

	/**
	 * @return the letrasectortrabajo
	 */
	public String getLetrasectortrabajo() {
		return letrasectortrabajo;
	}

	/**
	 * @param letrasectortrabajo the letrasectortrabajo to set
	 */
	public void setLetrasectortrabajo(String letrasectortrabajo) {
		this.letrasectortrabajo = letrasectortrabajo;
	}

	/**
	 * @return the numerosectortrabajo
	 */
	public int getNumerosectortrabajo() {
		return numerosectortrabajo;
	}

	/**
	 * @param numerosectortrabajo the numerosectortrabajo to set
	 */
	public void setNumerosectortrabajo(int numerosectortrabajo) {
		this.numerosectortrabajo = numerosectortrabajo;
	}

	/**
	 * @return the lugardelaentrega
	 */
	public String getLugardelaentrega() {
		return lugardelaentrega;
	}

	/**
	 * @param lugardelaentrega the lugardelaentrega to set
	 */
	public void setLugardelaentrega(String lugardelaentrega) {
		this.lugardelaentrega = lugardelaentrega;
	}

	/**
	 * @return the nombredelvendedor
	 */
	public String getNombredelvendedor() {
		return nombredelvendedor;
	}

	/**
	 * @param nombredelvendedor the nombredelvendedor to set
	 */
	public void setNombredelvendedor(String nombredelvendedor) {
		this.nombredelvendedor = nombredelvendedor;
	}

	/**
	 * @return the nombredelexpositor
	 */
	public String getNombredelexpositor() {
		return nombredelexpositor;
	}

	/**
	 * @param nombredelexpositor the nombredelexpositor to set
	 */
	public void setNombredelexpositor(String nombredelexpositor) {
		this.nombredelexpositor = nombredelexpositor;
	}

	/**
	 * @return the nombredelsupervisor
	 */
	public String getNombredelsupervisor() {
		return nombredelsupervisor;
	}

	/**
	 * @param nombredelsupervisor the nombredelsupervisor to set
	 */
	public void setNombredelsupervisor(String nombredelsupervisor) {
		this.nombredelsupervisor = nombredelsupervisor;
	}

	/**
	 * @return the cantidaddemercaderia
	 */
	public int getCantidaddemercaderia() {
		return cantidaddemercaderia;
	}

	/**
	 * @param cantidaddemercaderia the cantidaddemercaderia to set
	 */
	public void setCantidaddemercaderia(int cantidaddemercaderia) {
		this.cantidaddemercaderia = cantidaddemercaderia;
	}

	/**
	 * @return the codigodemercaderia
	 */
	public String getCodigodemercaderia() {
		return codigodemercaderia;
	}

	/**
	 * @param codigodemercaderia the codigodemercaderia to set
	 */
	public void setCodigodemercaderia(String codigodemercaderia) {
		this.codigodemercaderia = codigodemercaderia;
	}

	/**
	 * @return the montodeadelanto
	 */
	public int getMontodeadelanto() {
		return montodeadelanto;
	}

	/**
	 * @param montodeadelanto the montodeadelanto to set
	 */
	public void setMontodeadelanto(int montodeadelanto) {
		this.montodeadelanto = montodeadelanto;
	}

	/**
	 * @return the puntodeventa
	 */
	public String getPuntodeventa() {
		return puntodeventa;
	}

	/**
	 * @param puntodeventa the puntodeventa to set
	 */
	public void setPuntodeventa(String puntodeventa) {
		this.puntodeventa = puntodeventa;
	}

	/**
	 * @return the nombredelrelacionista
	 */
	public String getNombredelrelacionista() {
		return nombredelrelacionista;
	}

	/**
	 * @param nombredelrelacionista the nombredelrelacionista to set
	 */
	public void setNombredelrelacionista(String nombredelrelacionista) {
		this.nombredelrelacionista = nombredelrelacionista;
	}

	/**
	 * @return the distrito
	 */
	

	/**
	 * @return the eventodeventa
	 */
	public String getEventodeventa() {
		return eventodeventa;
	}

	/**
	 * @return the distritodelpunto
	 */
	public String getDistritodelpunto() {
		return distritodelpunto;
	}

	/**
	 * @param distritodelpunto the distritodelpunto to set
	 */
	public void setDistritodelpunto(String distritodelpunto) {
		this.distritodelpunto = distritodelpunto;
	}

	/**
	 * @param eventodeventa the eventodeventa to set
	 */
	public void setEventodeventa(String eventodeventa) {
		this.eventodeventa = eventodeventa;
	}

	/**
	 * @return the fechadecompromiso
	 */
	public Date getFechadecompromiso() {
		return fechadecompromiso;
	}

	/**
	 * @param fechadecompromiso the fechadecompromiso to set
	 */
	public void setFechadecompromiso(Date fechadecompromiso) {
		this.fechadecompromiso = fechadecompromiso;
	}

	/**
	 * @return the dosde
	 */
	public int getDosde() {
		return dosde;
	}

	/**
	 * @param dosde the dosde to set
	 */
	public void setDosde(int dosde) {
		this.dosde = dosde;
	}

	/**
	 * @return the doshasta
	 */
	public int getDoshasta() {
		return doshasta;
	}

	/**
	 * @param doshasta the doshasta to set
	 */
	public void setDoshasta(int doshasta) {
		this.doshasta = doshasta;
	}

	/**
	 * @return the encargadodelanetrega
	 */
	public String getEncargadodelanetrega() {
		return encargadodelanetrega;
	}

	/**
	 * @param encargadodelanetrega the encargadodelanetrega to set
	 */
	public void setEncargadodelanetrega(String encargadodelanetrega) {
		this.encargadodelanetrega = encargadodelanetrega;
	}

	/**
	 * @return the fechadellamadaovisita
	 */
	public Date getFechadellamadaovisita() {
		return fechadellamadaovisita;
	}

	/**
	 * @param fechadellamadaovisita the fechadellamadaovisita to set
	 */
	public void setFechadellamadaovisita(Date fechadellamadaovisita) {
		this.fechadellamadaovisita = fechadellamadaovisita;
	}

	/**
	 * @return the fechapostergada
	 */
	public Date getFechapostergada() {
		return fechapostergada;
	}

	/**
	 * @param fechapostergada the fechapostergada to set
	 */
	public void setFechapostergada(Date fechapostergada) {
		this.fechapostergada = fechapostergada;
	}

	/**
	 * @return the tresde
	 */
	public int getTresde() {
		return tresde;
	}

	/**
	 * @param tresde the tresde to set
	 */
	public void setTresde(int tresde) {
		this.tresde = tresde;
	}

	/**
	 * @return the treshasta
	 */
	public int getTreshasta() {
		return treshasta;
	}

	/**
	 * @param treshasta the treshasta to set
	 */
	public void setTreshasta(int treshasta) {
		this.treshasta = treshasta;
	}

	/**
	 * @return the fechafinal
	 */
	public Date getFechafinal() {
		return fechafinal;
	}

	/**
	 * @param fechafinal the fechafinal to set
	 */
	public void setFechafinal(Date fechafinal) {
		this.fechafinal = fechafinal;
	}

	/**
	 * @return the estadofinal
	 */
	public String getEstadofinal() {
		return estadofinal;
	}

	/**
	 * @param estadofinal the estadofinal to set
	 */
	public void setEstadofinal(String estadofinal) {
		this.estadofinal = estadofinal;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the nombredelpatrocinado
	 */
	public String getNombredelpatrocinado() {
		return nombredelpatrocinado;
	}

	/**
	 * @param nombredelpatrocinado the nombredelpatrocinado to set
	 */
	public void setNombredelpatrocinado(String nombredelpatrocinado) {
		this.nombredelpatrocinado = nombredelpatrocinado;
	}

	/**
	 * @return the comisiondelvendedor
	 */
	public int getComisiondelvendedor() {
		return comisiondelvendedor;
	}

	/**
	 * @param comisiondelvendedor the comisiondelvendedor to set
	 */
	public void setComisiondelvendedor(int comisiondelvendedor) {
		this.comisiondelvendedor = comisiondelvendedor;
	}

	/**
	 * @return the fechadepagoalvendedor
	 */
	public Date getFechadepagoalvendedor() {
		return fechadepagoalvendedor;
	}

	/**
	 * @param fechadepagoalvendedor the fechadepagoalvendedor to set
	 */
	public void setFechadepagoalvendedor(Date fechadepagoalvendedor) {
		this.fechadepagoalvendedor = fechadepagoalvendedor;
	}

	/**
	 * @return the comisiondelexpositor
	 */
	public int getComisiondelexpositor() {
		return comisiondelexpositor;
	}

	/**
	 * @param comisiondelexpositor the comisiondelexpositor to set
	 */
	public void setComisiondelexpositor(int comisiondelexpositor) {
		this.comisiondelexpositor = comisiondelexpositor;
	}

	/**
	 * @return the fechadepagoalexpositor
	 */
	public Date getFechadepagoalexpositor() {
		return fechadepagoalexpositor;
	}

	/**
	 * @param fechadepagoalexpositor the fechadepagoalexpositor to set
	 */
	public void setFechadepagoalexpositor(Date fechadepagoalexpositor) {
		this.fechadepagoalexpositor = fechadepagoalexpositor;
	}

	/**
	 * @return the comisiondelrelacionista
	 */
	public int getComisiondelrelacionista() {
		return comisiondelrelacionista;
	}

	/**
	 * @param comisiondelrelacionista the comisiondelrelacionista to set
	 */
	public void setComisiondelrelacionista(int comisiondelrelacionista) {
		this.comisiondelrelacionista = comisiondelrelacionista;
	}

	/**
	 * @return the fechadepagoalrelacionista
	 */
	public Date getFechadepagoalrelacionista() {
		return fechadepagoalrelacionista;
	}

	/**
	 * @param fechadepagoalrelacionista the fechadepagoalrelacionista to set
	 */
	public void setFechadepagoalrelacionista(Date fechadepagoalrelacionista) {
		this.fechadepagoalrelacionista = fechadepagoalrelacionista;
	}

	/**
	 * @return the comisiondelsupervisor
	 */
	public int getComisiondelsupervisor() {
		return comisiondelsupervisor;
	}

	/**
	 * @param comisiondelsupervisor the comisiondelsupervisor to set
	 */
	public void setComisiondelsupervisor(int comisiondelsupervisor) {
		this.comisiondelsupervisor = comisiondelsupervisor;
	}

	/**
	 * @return the fechadepagoalsupervisor
	 */
	public Date getFechadepagoalsupervisor() {
		return fechadepagoalsupervisor;
	}

	/**
	 * @param fechadepagoalsupervisor the fechadepagoalsupervisor to set
	 */
	public void setFechadepagoalsupervisor(Date fechadepagoalsupervisor) {
		this.fechadepagoalsupervisor = fechadepagoalsupervisor;
	}

	/**
	 * @return the comisiondelpatrocinador
	 */
	public int getComisiondelpatrocinador() {
		return comisiondelpatrocinador;
	}

	/**
	 * @param comisiondelpatrocinador the comisiondelpatrocinador to set
	 */
	public void setComisiondelpatrocinador(int comisiondelpatrocinador) {
		this.comisiondelpatrocinador = comisiondelpatrocinador;
	}

	/**
	 * @return the fechadepagoalpatrocinador
	 */
	public Date getFechadepagoalpatrocinador() {
		return fechadepagoalpatrocinador;
	}

	/**
	 * @param fechadepagoalpatrocinador the fechadepagoalpatrocinador to set
	 */
	public void setFechadepagoalpatrocinador(Date fechadepagoalpatrocinador) {
		this.fechadepagoalpatrocinador = fechadepagoalpatrocinador;
	}

	/**
	 * @return the preciototal
	 */
	public int getPreciototal() {
		return preciototal;
	}

	/**
	 * @param preciototal the preciototal to set
	 */
	public void setPreciototal(int preciototal) {
		this.preciototal = preciototal;
	}

	/**
	 * @return the puntaje
	 */
	public int getPuntaje() {
		return puntaje;
	}

	/**
	 * @param puntaje the puntaje to set
	 */
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
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
	 * @return the fechadecuempleaniosString
	 */
	public String getFechadecuempleaniosString() {
		fechadecuempleaniosString = DateUtil.formatoString(getFechadecuempleanios(), "dd/MM/yyyy");
		return fechadecuempleaniosString;
	}

	/**
	 * @param fechadecuempleaniosString the fechadecuempleaniosString to set
	 */
	public void setFechadecuempleaniosString(String fechadecuempleaniosString) {
		this.fechadecuempleaniosString = fechadecuempleaniosString;
	}

	/**
	 * @return the fechaString
	 */
	public String getFechaString() {
		fechaString = DateUtil.formatoString(getFecha(), "dd/MM/yyyy");
		return fechaString;
	}

	/**
	 * @param fechaString the fechaString to set
	 */
	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}
	
	
	   
}