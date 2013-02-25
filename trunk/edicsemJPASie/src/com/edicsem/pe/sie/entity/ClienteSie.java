package com.edicsem.pe.sie.entity;

import java.io.Serializable;
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
 * The persistent class for the tb_cliente database table.
 * 
 */
@Entity
@Table(name="tb_cliente", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ClienteSie implements Serializable {
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
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;
	
	@Temporal( TemporalType.DATE)
	private Date fecnacimiento;

	private String nombrecliente;

	private String numdocumento;

	private String telftrabajo;

	private String titulartelefono;
	
	private Integer tipocliente;
	
	private String planoTrabajo;

	private String letraTrabajo;

	private String sectorTrabajo;
	
	private String genero;
	
	//bi-directional many-to-one association to TelefonoPersonaSie
  	@OneToMany(mappedBy="idcliente")
  	private Set<DomicilioPersonaSie> tbDomicilioPersona;
    
    //bi-directional many-to-one association to TelefonoPersonaSie
  	@OneToMany(mappedBy="idcliente")
  	private Set<TelefonoPersonaSie> tbTelefonoPersona;

	//bi-directional many-to-one association to TipoDocumentoIdentidadSie
    @ManyToOne
	@JoinColumn(name="idtipodocumentoidentidad")
	private TipoDocumentoIdentidadSie tbTipoDocumentoIdentidad;
    
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	//bi-directional many-to-one association to CobranzaDTO
	@OneToMany(mappedBy="tbCliente")
	private Set<CobranzaSie> tbCobranzas;

	//bi-directional many-to-one association to ContratoSie
	@OneToMany(mappedBy="tbCliente")
	private Set<ContratoSie> tbContratos;
	
	@Transient
	private String nombresCompletos;
	
	@Transient
	private String fechaNacimientoString;
	
	@Transient
	private int item;

	@Transient
	private String tipoClienteString;

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
 
	
	public Set<DomicilioPersonaSie> getTbDomicilioPersona() {
		return tbDomicilioPersona;
	}

	public void setTbDomicilioPersona(Set<DomicilioPersonaSie> tbDomicilioPersona) {
		this.tbDomicilioPersona = tbDomicilioPersona;
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

	public Set<TelefonoPersonaSie> getTbTelefonoPersona() {
		return tbTelefonoPersona;
	}

	public void setTbTelefonoPersona(Set<TelefonoPersonaSie> tbTelefonoPersona) {
		this.tbTelefonoPersona = tbTelefonoPersona;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
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
	 * @return the nombresCompletos
	 */
	public String getNombresCompletos() {
		nombresCompletos =nombrecliente + " " + apepatcliente + " " +apematcliente; 
		return nombresCompletos;
	}

	/**
	 * @param nombresCompletos the nombresCompletos to set
	 */
	public void setNombresCompletos(String nombresCompletos) {
		this.nombresCompletos = nombresCompletos;
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
	 * @return the item
	 */
	public int getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(int item) {
		this.item = item;
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

	public String getTipoClienteString() {
		
		if(tipocliente==null){
			tipoClienteString="";
		}
		else if(tipocliente==1){
			tipoClienteString="Puntual";
		}
		else if(tipocliente==2){
			tipoClienteString="Regular";
		}
		else if(tipocliente==3){
			tipoClienteString="Moroso";
		}
		else if(tipocliente==4){
			tipoClienteString="Extremo";
		}else{
			tipoClienteString="";
		}
		return tipoClienteString;
	}

	public void setTipoClienteString(String tipoClienteString) {
		this.tipoClienteString = tipoClienteString;
	}

	public String getPlanoTrabajo() {
		return planoTrabajo;
	}

	public void setPlanoTrabajo(String planoTrabajo) {
		this.planoTrabajo = planoTrabajo;
	}

	public String getLetraTrabajo() {
		return letraTrabajo;
	}

	public void setLetraTrabajo(String letraTrabajo) {
		this.letraTrabajo = letraTrabajo;
	}

	public String getSectorTrabajo() {
		return sectorTrabajo;
	}

	public void setSectorTrabajo(String sectorTrabajo) {
		this.sectorTrabajo = sectorTrabajo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}