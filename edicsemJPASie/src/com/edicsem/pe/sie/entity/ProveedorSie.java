package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tb_proveedor database table.
 * 
 */
@Entity
@Table(name="tb_proveedor", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class ProveedorSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_PROVEEDOR_IDPROVEEDOR_GENERATOR", sequenceName="SIE.TB_PROVEEDOR_IDPROVEEDOR_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_PROVEEDOR_IDPROVEEDOR_GENERATOR")
	private Integer idproveedor;

	private String codproveedor;

	private String direccion;

	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private String nombreempresa;

	private String numdocumentoproveedor;

	private String personacontacto;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to ComprobanteSie
	@OneToMany(mappedBy="tbProveedor")
	private Set<ComprobanteSie> tbComprobantes;

	//bi-directional many-to-one association to TipoDocumentoIdentidadSie
    @ManyToOne
	@JoinColumn(name="idtipodocumentoidentidad")
	private TipoDocumentoIdentidadSie tbTipoDocumentoIdentidad;

    public ProveedorSie() {
    }

	public Integer getIdproveedor() {
		return this.idproveedor;
	}

	public void setIdproveedor(Integer idproveedor) {
		this.idproveedor = idproveedor;
	}

	public String getCodproveedor() {
		return this.codproveedor;
	}

	public void setCodproveedor(String codproveedor) {
		this.codproveedor = codproveedor;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public String getNombreempresa() {
		return this.nombreempresa;
	}

	public void setNombreempresa(String nombreempresa) {
		this.nombreempresa = nombreempresa;
	}

	public String getNumdocumentoproveedor() {
		return this.numdocumentoproveedor;
	}

	public void setNumdocumentoproveedor(String numdocumentoproveedor) {
		this.numdocumentoproveedor = numdocumentoproveedor;
	}

	public String getPersonacontacto() {
		return this.personacontacto;
	}

	public void setPersonacontacto(String personacontacto) {
		this.personacontacto = personacontacto;
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

	public Set<ComprobanteSie> getTbComprobantes() {
		return this.tbComprobantes;
	}

	public void setTbComprobantes(Set<ComprobanteSie> tbComprobantes) {
		this.tbComprobantes = tbComprobantes;
	}
	
	public TipoDocumentoIdentidadSie getTbTipoDocumentoIdentidad() {
		return this.tbTipoDocumentoIdentidad;
	}

	public void setTbTipoDocumentoIdentidad(TipoDocumentoIdentidadSie tbTipoDocumentoIdentidad) {
		this.tbTipoDocumentoIdentidad = tbTipoDocumentoIdentidad;
	}
	
}