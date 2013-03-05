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


/**
 * The persistent class for the tb_empleado database table.
 * 
 */
@Entity
@Table(name="tb_empleado", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class EmpleadoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_EMPLEADO_IDEMPLEADO_GENERATOR", sequenceName="SIE.TB_EMPLEADO_IDEMPLEADO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_EMPLEADO_IDEMPLEADO_GENERATOR")
	private Integer idempleado;

	private String apematemp;

	private String apepatemp;

	private String contrasena;
	
	private String genero;
	
	@Transient
	private int cargo;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;
	
    @Temporal( TemporalType.DATE)
	private Date fechanacimiento;

	private String nombreemp;

	private String numdocumento;

	private String usuario;

	private String usuariocreacion;

	private String usuariomodifica;
	
	private String correo;
	
	@Transient
	private String nombresCompletos;
	
	//bi-directional many-to-one association to CobranzaOperadoraSie
	@OneToMany(mappedBy="tbEmpleado")
	private Set<CobranzaOperadoraSie> tbCobranzaOperadoras;

	//bi-directional many-to-one association to ContratoEmpleadoSie
	@OneToMany(mappedBy="tbEmpleado1")
	private Set<ContratoEmpleadoSie> tbContratoEmpleados1;

	//bi-directional many-to-one association to ContratoEmpleadoSie
	@OneToMany(mappedBy="tbEmpleado2")
	private Set<ContratoEmpleadoSie> tbContratoEmpleados2;
    
    //bi-directional many-to-one association to TelefonoPersonaSie
  	@OneToMany(mappedBy="idempleado")
  	private Set<TelefonoPersonaSie> tbTelefonoPersona;
  	
	//bi-directional many-to-one association to DomicilioPersonaSie
  	@OneToMany(mappedBy="idempleado")
  	private Set<DomicilioPersonaSie> tbDomicilioPersona;
  	
	//bi-directional many-to-one association to TipoDocumentoIdentidadSie
    @ManyToOne
	@JoinColumn(name="idtipodocumentoidentidad")
	private TipoDocumentoIdentidadSie tbTipoDocumentoIdentidad;

	//bi-directional many-to-one association to HorarioAsistenciaSie
	@OneToMany(mappedBy="tbEmpleado")
	private Set<HorarioAsistenciaSie> tbHorarioAsistencias;

	//bi-directional many-to-one association to HorarioPersonalSie
	@OneToMany(mappedBy="tbEmpleado")
	private Set<HorarioPersonalSie> tbHorarioPersonals;
	
	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    //bi-directional many-to-one association to MetaEmpleadoSie
  	@OneToMany(mappedBy="tbEmpleado")
  	private Set<MetaEmpleadoSie> tbMetaEmpleados;
  	
	//bi-directional many-to-one association to DetContratoEmpleadoSie
	@OneToMany(mappedBy="tbEmpleado")
	private Set<DetContratoEmpleadoSie> tbDetContratoEmpleados;
	
	//bi-directional many-to-one association to FiltroHorarioVentaSie
	@OneToMany(mappedBy="tbEmpleado")
	private Set<FiltroHorarioVentaSie> tbFiltroHorarioVentas;
	
	//bi-directional many-to-one association to DetSancionEmpleadoSie
	@OneToMany(mappedBy="tbEmpleado")
	private Set<DetSancionEmpleadoSie> tbDetSancionEmpleado;
  	
  	//bi-directional many-to-one association to DomicilioPersonaSie
  	@OneToMany(mappedBy="idempleado")
  	private Set<DetpagoSie> tbDetpago;
  	
  	//bi-directional many-to-one association to DetGrupoEmpleadoSie
  	@OneToMany(mappedBy="tbempleado")
  	private Set<DetGrupoEmpleadoSie> tbDetGrupoEmpleado;
  	
  	//bi-directional many-to-one association to ControlKardexSie
  	@OneToMany(mappedBy="tbEmpleado")
  	private Set<ControlKardexSie> tbControlKardex;
  	
	public EmpleadoSie() {
    }

	public Integer getIdempleado() {
		return this.idempleado;
	}

	public void setIdempleado(Integer idempleado) {
		this.idempleado = idempleado;
	}

	public String getApematemp() {
		return this.apematemp;
	}

	public void setApematemp(String apematemp) {
		this.apematemp = apematemp;
	}

	public String getApepatemp() {
		return this.apepatemp;
	}

	public void setApepatemp(String apepatemp) {
		this.apepatemp = apepatemp;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNombreemp() {
		return this.nombreemp;
	}

	public void setNombreemp(String nombreemp) {
		this.nombreemp = nombreemp;
	}

	public String getNumdocumento() {
		return this.numdocumento;
	}

	public void setNumdocumento(String numdocumento) {
		this.numdocumento = numdocumento;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	
	public Set<CobranzaOperadoraSie> getTbCobranzaOperadoras() {
		return this.tbCobranzaOperadoras;
	}

	public void setTbCobranzaOperadoras(Set<CobranzaOperadoraSie> tbCobranzaOperadoras) {
		this.tbCobranzaOperadoras = tbCobranzaOperadoras;
	}
	
	public Set<ContratoEmpleadoSie> getTbContratoEmpleados1() {
		return this.tbContratoEmpleados1;
	}

	public void setTbContratoEmpleados1(Set<ContratoEmpleadoSie> tbContratoEmpleados1) {
		this.tbContratoEmpleados1 = tbContratoEmpleados1;
	}
	
	public Set<ContratoEmpleadoSie> getTbContratoEmpleados2() {
		return this.tbContratoEmpleados2;
	}

	public void setTbContratoEmpleados2(Set<ContratoEmpleadoSie> tbContratoEmpleados2) {
		this.tbContratoEmpleados2 = tbContratoEmpleados2;
	}
	
	public Set<TelefonoPersonaSie> getTbTelefonoPersona() {
		return tbTelefonoPersona;
	}

	public void setTbTelefonoPersona(Set<TelefonoPersonaSie> tbTelefonoPersona) {
		this.tbTelefonoPersona = tbTelefonoPersona;
	}

	public TipoDocumentoIdentidadSie getTbTipoDocumentoIdentidad() {
		return this.tbTipoDocumentoIdentidad;
	}

	public void setTbTipoDocumentoIdentidad(TipoDocumentoIdentidadSie tbTipoDocumentoIdentidad) {
		this.tbTipoDocumentoIdentidad = tbTipoDocumentoIdentidad;
	}
	
	public Set<HorarioAsistenciaSie> getTbHorarioAsistencias() {
		return this.tbHorarioAsistencias;
	}

	public void setTbHorarioAsistencias(Set<HorarioAsistenciaSie> tbHorarioAsistencias) {
		this.tbHorarioAsistencias = tbHorarioAsistencias;
	}
	
	public Set<HorarioPersonalSie> getTbHorarioPersonals() {
		return this.tbHorarioPersonals;
	}

	public void setTbHorarioPersonals(Set<HorarioPersonalSie> tbHorarioPersonals) {
		this.tbHorarioPersonals = tbHorarioPersonals;
	}

	/**
	 * @return the nombresCompletos
	 */
	public String getNombresCompletos() {
		nombresCompletos =nombreemp + " " + apepatemp + " " +apematemp; 
		return nombresCompletos;
	}

	/**
	 * @param nombresCompletos the nombresCompletos to set
	 */
	public void setNombresCompletos(String nombresCompletos) {
		this.nombresCompletos = nombresCompletos;
	}

    public Set<DetSancionEmpleadoSie> getTbDetSancionEmpleado() {
		return tbDetSancionEmpleado;
	}

	public void setTbDetSancionEmpleado(Set<DetSancionEmpleadoSie> tbDetSancionEmpleado) {
		this.tbDetSancionEmpleado = tbDetSancionEmpleado;
	}

	public Set<DomicilioPersonaSie> getTbDomicilioPersona() {
		return tbDomicilioPersona;
	}

	public void setTbDomicilioPersona(Set<DomicilioPersonaSie> tbDomicilioPersona) {
		this.tbDomicilioPersona = tbDomicilioPersona;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

	public Set<MetaEmpleadoSie> getTbMetaEmpleados() {
		return tbMetaEmpleados;
	}

	public void setTbMetaEmpleados(Set<MetaEmpleadoSie> tbMetaEmpleados) {
		this.tbMetaEmpleados = tbMetaEmpleados;
	}
	
	public Set<DetContratoEmpleadoSie> getTbDetContratoEmpleados() {
		return tbDetContratoEmpleados;
	}
	
	public void setTbDetContratoEmpleados(
			Set<DetContratoEmpleadoSie> tbDetContratoEmpleados) {
		this.tbDetContratoEmpleados = tbDetContratoEmpleados;
	}

	public Set<FiltroHorarioVentaSie> getTbFiltroHorarioVentas() {
		return tbFiltroHorarioVentas;
	}

	public void setTbFiltroHorarioVentas(
			Set<FiltroHorarioVentaSie> tbFiltroHorarioVentas) {
		this.tbFiltroHorarioVentas = tbFiltroHorarioVentas;
	}
	
	public Set<DetpagoSie> getTbDetpago() {
		return tbDetpago;
	}

	public void setTbDetpago(Set<DetpagoSie> tbDetpago) {
		this.tbDetpago = tbDetpago;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * @return the cargo
	 */
	public int getCargo() {
		return cargo;
	}

	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}

	public Set<DetGrupoEmpleadoSie> getTbDetGrupoEmpleado() {
		return tbDetGrupoEmpleado;
	}

	public void setTbDetGrupoEmpleado(Set<DetGrupoEmpleadoSie> tbDetGrupoEmpleado) {
		this.tbDetGrupoEmpleado = tbDetGrupoEmpleado;
	}

	public Set<ControlKardexSie> getTbControlKardex() {
		return tbControlKardex;
	}

	public void setTbControlKardex(Set<ControlKardexSie> tbControlKardex) {
		this.tbControlKardex = tbControlKardex;
	}

}