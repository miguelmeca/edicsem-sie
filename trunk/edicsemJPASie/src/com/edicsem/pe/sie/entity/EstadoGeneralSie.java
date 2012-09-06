package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tb_estado_general database table.
 * 
 */
@Entity
@Table(name="tb_estado_general", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class EstadoGeneralSie  extends BaseMantenimientoForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_ESTADO_GENERAL_IDESTADOGENERAL_GENERATOR", sequenceName="SIE.TB_ESTADO_GENERAL_IDESTADOGENERAL_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_ESTADO_GENERAL_IDESTADOGENERAL_GENERATOR")
	private Integer idestadogeneral;

	private String codestadogeneral;

	private String descripcion;

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

	//bi-directional many-to-one association to TipoCasaSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<TipoCasaSie> tbTipoCasas;

	//bi-directional many-to-one association to TipoDocumentoIdentidadSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<TipoDocumentoIdentidadSie> tbTipoDocumentoIdentidads;

	//bi-directional many-to-one association to TipoKardexProductoSie
	@OneToMany(mappedBy="tbEstadoGeneral")
	private Set<TipoKardexProductoSie> tbTipoKardexProductos;

    public EstadoGeneralSie() {
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
	
}