package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_tipo_documento_identidad database table.
 * 
 */
@Entity
@Table(name="tb_tipo_documento_identidad")
public class TipoDocumentoIdentidadSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_TIPO_DOCUMENTO_IDENTIDAD_IDTIPODOCUMENTOIDENTIDAD_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_TIPO_DOCUMENTO_IDENTIDAD_IDTIPODOCUMENTOIDENTIDAD_GENERATOR")
	private Integer idtipodocumentoidentidad;

	private String descripcion;

	//bi-directional many-to-one association to ClienteSie
	@OneToMany(mappedBy="tbTipoDocumentoIdentidad")
	private Set<ClienteSie> tbClientes;

	//bi-directional many-to-one association to EmpleadoSie
	@OneToMany(mappedBy="tbTipoDocumentoIdentidad")
	private Set<EmpleadoSie> tbEmpleados;

	//bi-directional many-to-one association to ProveedorSie
	@OneToMany(mappedBy="tbTipoDocumentoIdentidad")
	private Set<ProveedorSie> tbProveedors;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

    public TipoDocumentoIdentidadSie() {
    }

	public Integer getIdtipodocumentoidentidad() {
		return this.idtipodocumentoidentidad;
	}

	public void setIdtipodocumentoidentidad(Integer idtipodocumentoidentidad) {
		this.idtipodocumentoidentidad = idtipodocumentoidentidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<ClienteSie> getTbClientes() {
		return this.tbClientes;
	}

	public void setTbClientes(Set<ClienteSie> tbClientes) {
		this.tbClientes = tbClientes;
	}
	
	public Set<EmpleadoSie> getTbEmpleados() {
		return this.tbEmpleados;
	}

	public void setTbEmpleados(Set<EmpleadoSie> tbEmpleados) {
		this.tbEmpleados = tbEmpleados;
	}
	
	public Set<ProveedorSie> getTbProveedors() {
		return this.tbProveedors;
	}

	public void setTbProveedors(Set<ProveedorSie> tbProveedors) {
		this.tbProveedors = tbProveedors;
	}
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}