package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_estado_general database table.
 * 
 */
@Entity
@Table(name="tb_estado_general", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class EstadoGeneralSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ESTADO_GENERAL_IDESTADOGENERAL_GENERATOR", sequenceName="SIE.TB_ESTADO_GENERAL_IDESTADOGENERAL_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ESTADO_GENERAL_IDESTADOGENERAL_GENERATOR")
	private Integer idestadogeneral;

	private String codestadogeneral;

	private String descripcion;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	//bi-directional many-to-one association to CargoEmpleadoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<CargoEmpleadoSie> tbCargoEmpleados;

	//bi-directional many-to-one association to CobranzaSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<CobranzaSie> tbCobranzas;

	//bi-directional many-to-one association to ContratoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<ContratoSie> tbContratos;

	//bi-directional many-to-one association to DomicilioPersonaSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<DomicilioPersonaSie> tbDomicilioPersonas;

	//bi-directional many-to-one association to EmpresaSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<EmpresaSie> tbEmpresas;

	//bi-directional many-to-one association to ParametroSistemaSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<ParametroSistemaSie> tbParametroSistemas;

	//bi-directional many-to-one association to PermisoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<PermisoSie> tbPermisos;

	//bi-directional many-to-one association to ProductoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<ProductoSie> tbProductos;

	//bi-directional many-to-one association to RutaArchivoEscaneadoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<RutaArchivoEscaneadoSie> tbRutaArchivoEscaneados;

	//bi-directional many-to-one association to TelefonoPersonaSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<TelefonoPersonaSie> tbTelefonoPersonas;
	
	//bi-directional many-to-one association to ContratoEmpleadoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<ContratoEmpleadoSie> tbContratoEmpleados;

	//bi-directional many-to-one association to TipoCasaSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<TipoCasaSie> tbTipoCasas;
	
	//bi-directional many-to-one association to ClienteSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<ClienteSie> tbClientes;
	
	//bi-directional many-to-one association to EmpleadoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<EmpleadoSie> tbEmpleados;
	
	//bi-directional many-to-one association to DetContratoEmpleadoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<DetContratoEmpleadoSie> tbDetContratoEmpleados;

	//bi-directional many-to-one association to TipoDocumentoIdentidadSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<TipoDocumentoIdentidadSie> tbTipoDocumentoIdentidads;

	//bi-directional many-to-one association to TipoKardexProductoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<TipoKardexProductoSie> tbTipoKardexProductos;
	
	//bi-directional many-to-one association to ProveedorSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<ProveedorSie> tbProveedor;
	
	//bi-directional many-to-one association to PuntoVentaSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<PuntoVentaSie> tbPuntoVentas;
	
	//bi-directional many-to-one association to HorarioPersonalSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<HorarioPersonalSie> tbHorarioPersonals;
	
	//bi-directional many-to-one association to IncidenciaSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<IncidenciaSie> tbIncidencias;
	
	//bi-directional many-to-one association to ObservacionIncidenciaSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<ObservacionIncidenciaSie> tbObservacionIncidencias;
	
	//bi-directional many-to-one association to FiltroHorarioVentaSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<FiltroHorarioVentaSie> tbFiltroHorarioVentas;

	//bi-directional many-to-one association to HorarioPuntoVentaSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<HorarioPuntoVentaSie> tbHorarioPuntos;
	
	//bi-directional many-to-one association to FactorSancionSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<FactorSancionSie> tbFactorSancion;
	
	//bi-directional many-to-one association to SancionSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<SancionSie> tbSancion;
	
	//bi-directional many-to-one association to DetSancionCargoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<DetSancionCargoSie> tbDetSancionCargo;
	
	//bi-directional many-to-one association to DetSancionEmpleadoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<DetSancionEmpleadoSie> tbDetSancionEmpleado;
	
	//bi-directional many-to-one association to PaqueteSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<PaqueteSie> tbPaquetes;
	
	//bi-directional many-to-one association to DetPaqueteSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<DetPaqueteSie> tbDetPaquetes;
	
	//bi-directional many-to-one association to DetProductoContratoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<DetProductoContratoSie> tbDetProductoContrato;
	
	//bi-directional many-to-one association to MotivoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<MotivoSie> tbMotivo;
	
	//bi-directional many-to-one association to SeguimientoContratoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<SeguimientoContratoSie> tbSeguimientoContrato;
	
	//bi-directional many-to-one association to ControlKardexSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<ControlKardexSie> tbControlKardex;

	public EstadoGeneralSie() {
    }

    public Set<DetSancionEmpleadoSie> getTbDetSancionEmpleado() {
		return tbDetSancionEmpleado;
	}

	public void setTbDetSancionEmpleado(
			Set<DetSancionEmpleadoSie> tbDetSancionEmpleado) {
		this.tbDetSancionEmpleado = tbDetSancionEmpleado;
	}

	public Integer getIdestadogeneral() {
		return this.idestadogeneral;
	}

	public void setIdestadogeneral(Integer idestadogeneral) {
		this.idestadogeneral = idestadogeneral;
	}

	public String getCodestadogeneral() {
		return this.codestadogeneral;
	}

	public void setCodestadogeneral(String codestadogeneral) {
		this.codestadogeneral = codestadogeneral;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Set<CargoEmpleadoSie> getTbCargoEmpleados() {
		return this.tbCargoEmpleados;
	}

	public void setTbCargoEmpleados(Set<CargoEmpleadoSie> tbCargoEmpleados) {
		this.tbCargoEmpleados = tbCargoEmpleados;
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
	
	public Set<DomicilioPersonaSie> getTbDomicilioPersonas() {
		return this.tbDomicilioPersonas;
	}

	public void setTbDomicilioPersonas(Set<DomicilioPersonaSie> tbDomicilioPersonas) {
		this.tbDomicilioPersonas = tbDomicilioPersonas;
	}
	
	public Set<EmpresaSie> getTbEmpresas() {
		return this.tbEmpresas;
	}

	public void setTbEmpresas(Set<EmpresaSie> tbEmpresas) {
		this.tbEmpresas = tbEmpresas;
	}
	
	public Set<ParametroSistemaSie> getTbParametroSistemas() {
		return this.tbParametroSistemas;
	}

	public void setTbParametroSistemas(Set<ParametroSistemaSie> tbParametroSistemas) {
		this.tbParametroSistemas = tbParametroSistemas;
	}
	
	public Set<PermisoSie> getTbPermisos() {
		return this.tbPermisos;
	}

	public void setTbPermisos(Set<PermisoSie> tbPermisos) {
		this.tbPermisos = tbPermisos;
	}
	
	public Set<ProductoSie> getTbProductos() {
		return this.tbProductos;
	}

	public void setTbProductos(Set<ProductoSie> tbProductos) {
		this.tbProductos = tbProductos;
	}
	
	public Set<RutaArchivoEscaneadoSie> getTbRutaArchivoEscaneados() {
		return this.tbRutaArchivoEscaneados;
	}

	public void setTbRutaArchivoEscaneados(Set<RutaArchivoEscaneadoSie> tbRutaArchivoEscaneados) {
		this.tbRutaArchivoEscaneados = tbRutaArchivoEscaneados;
	}
	
	public Set<TelefonoPersonaSie> getTbTelefonoPersonas() {
		return this.tbTelefonoPersonas;
	}

	public void setTbTelefonoPersonas(Set<TelefonoPersonaSie> tbTelefonoPersonas) {
		this.tbTelefonoPersonas = tbTelefonoPersonas;
	}
	
	public Set<TipoCasaSie> getTbTipoCasas() {
		return this.tbTipoCasas;
	}

	public void setTbTipoCasas(Set<TipoCasaSie> tbTipoCasas) {
		this.tbTipoCasas = tbTipoCasas;
	}
	
	public Set<TipoDocumentoIdentidadSie> getTbTipoDocumentoIdentidads() {
		return this.tbTipoDocumentoIdentidads;
	}

	public void setTbTipoDocumentoIdentidads(Set<TipoDocumentoIdentidadSie> tbTipoDocumentoIdentidads) {
		this.tbTipoDocumentoIdentidads = tbTipoDocumentoIdentidads;
	}
	
	public Set<TipoKardexProductoSie> getTbTipoKardexProductos() {
		return this.tbTipoKardexProductos;
	}

	public void setTbTipoKardexProductos(Set<TipoKardexProductoSie> tbTipoKardexProductos) {
		this.tbTipoKardexProductos = tbTipoKardexProductos;
	}

	public Set<ClienteSie> getTbClientes() {
		return tbClientes;
	}

	public void setTbClientes(Set<ClienteSie> tbClientes) {
		this.tbClientes = tbClientes;
	}

	public Set<EmpleadoSie> getTbEmpleados() {
		return tbEmpleados;
	}

	public void setTbEmpleados(Set<EmpleadoSie> tbEmpleados) {
		this.tbEmpleados = tbEmpleados;
	}

	public Set<ProveedorSie> getTbProveedor() {
		return tbProveedor;
	}

	public void setTbProveedor(Set<ProveedorSie> tbProveedor) {
		this.tbProveedor = tbProveedor;
	}

	public Set<PuntoVentaSie> getTbPuntoVentas() {
		return tbPuntoVentas;
	}

	public void setTbPuntoVentas(Set<PuntoVentaSie> tbPuntoVentas) {
		this.tbPuntoVentas = tbPuntoVentas;
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

	public Set<HorarioPersonalSie> getTbHorarioPersonals() {
		return tbHorarioPersonals;
	}

	public void setTbHorarioPersonals(Set<HorarioPersonalSie> tbHorarioPersonals) {
		this.tbHorarioPersonals = tbHorarioPersonals;
	}

	public Set<IncidenciaSie> getTbIncidencias() {
		return tbIncidencias;
	}

	public void setTbIncidencias(Set<IncidenciaSie> tbIncidencias) {
		this.tbIncidencias = tbIncidencias;
	}

	public Set<ObservacionIncidenciaSie> getTbObservacionIncidencias() {
		return tbObservacionIncidencias;
	}

	public void setTbObservacionIncidencias(
			Set<ObservacionIncidenciaSie> tbObservacionIncidencias) {
		this.tbObservacionIncidencias = tbObservacionIncidencias;
	}

	public Set<FiltroHorarioVentaSie> getTbFiltroHorarioVentas() {
		return tbFiltroHorarioVentas;
	}

	public void setTbFiltroHorarioVentas(
			Set<FiltroHorarioVentaSie> tbFiltroHorarioVentas) {
		this.tbFiltroHorarioVentas = tbFiltroHorarioVentas;
	}

	public Set<HorarioPuntoVentaSie> getTbHorarioPuntos() {
		return tbHorarioPuntos;
	}

	public void setTbHorarioPuntos(Set<HorarioPuntoVentaSie> tbHorarioPuntos) {
		this.tbHorarioPuntos = tbHorarioPuntos;
	}

	public Set<FactorSancionSie> getTbFactorSancion() {
		return tbFactorSancion;
	}

	public void setTbFactorSancion(Set<FactorSancionSie> tbFactorSancion) {
		this.tbFactorSancion = tbFactorSancion;
	}

	public Set<SancionSie> getTbSancion() {
		return tbSancion;
	}

	public void setTbSancion(Set<SancionSie> tbSancion) {
		this.tbSancion = tbSancion;
	}

	public Set<DetSancionCargoSie> getTbDetSancionCargo() {
		return tbDetSancionCargo;
	}

	public void setTbDetSancionCargo(Set<DetSancionCargoSie> tbDetSancionCargo) {
		this.tbDetSancionCargo = tbDetSancionCargo;
	}

	public Set<ContratoEmpleadoSie> getTbContratoEmpleados() {
		return tbContratoEmpleados;
	}

	public void setTbContratoEmpleados(Set<ContratoEmpleadoSie> tbContratoEmpleados) {
		this.tbContratoEmpleados = tbContratoEmpleados;
	}
	
	public Set<PaqueteSie> getTbPaquetes() {
		return tbPaquetes;
	}
	
	public void setTbPaquetes(Set<PaqueteSie> tbPaquetes) {
		this.tbPaquetes = tbPaquetes;
	}
	
	public Set<DetPaqueteSie> getTbDetPaquetes() {
		return tbDetPaquetes;
	}
	
	public void setTbDetPaquetes(Set<DetPaqueteSie> tbDetPaquetes) {
		this.tbDetPaquetes = tbDetPaquetes;
	}

	/**
	 * @return the tbDetProductoContrato
	 */
	public Set<DetProductoContratoSie> getTbDetProductoContrato() {
		return tbDetProductoContrato;
	}

	/**
	 * @param tbDetProductoContrato the tbDetProductoContrato to set
	 */
	public void setTbDetProductoContrato(
			Set<DetProductoContratoSie> tbDetProductoContrato) {
		this.tbDetProductoContrato = tbDetProductoContrato;
	}

	public Set<SeguimientoContratoSie> getTbSeguimientoContrato() {
		return tbSeguimientoContrato;
	}

	public void setTbSeguimientoContrato(
			Set<SeguimientoContratoSie> tbSeguimientoContrato) {
		this.tbSeguimientoContrato = tbSeguimientoContrato;
	}

	public Set<MotivoSie> getTbMotivo() {
		return tbMotivo;
	}

	public void setTbMotivo(Set<MotivoSie> tbMotivo) {
		this.tbMotivo = tbMotivo;
	}

	public Set<ControlKardexSie> getTbControlKardex() {
		return tbControlKardex;
	}

	public void setTbControlKardex(Set<ControlKardexSie> tbControlKardex) {
		this.tbControlKardex = tbControlKardex;
	}

	
}