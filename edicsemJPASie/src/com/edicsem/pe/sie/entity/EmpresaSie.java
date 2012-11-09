package com.edicsem.pe.sie.entity;

import java.io.Serializable;
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

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_empresa database table.
 * 
 */
@Entity
@Table(name="tb_empresa", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class EmpresaSie  implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_TB_EMPRESA_IDEMPRESA")
	@SequenceGenerator(name="SEC_TB_EMPRESA_IDEMPRESA", sequenceName="SIE.TB_EMPRESA_IDEMPRESA_SEQ")
	@Column(name="idempresa")
	private Integer idempresa;

	private String descripcion;

	private String email;

	private String numruc;

	private String numtelefono;

	private String razonsocial;
	
	private String tipoVenta;

	//bi-directional many-to-one association to ContratoSie
	@OneToMany(mappedBy="tbEmpresa")
	private Set<ContratoSie> tbContratos;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	//bi-directional many-to-one association to MetaEmpresaSie
	@OneToMany(mappedBy="tbEmpresa")
	private Set<MetaEmpresaSie> tbMetaEmpresas;
	
	//bi-directional many-to-one association to ContratoSie
	@OneToMany(mappedBy="tbEmpresa")
	private Set<KardexSie> tbKardex;

	//bi-directional many-to-one association to DetEmpresaEmpleadoSie
	@OneToMany(mappedBy="tbEmpresa")
	private Set<DetEmpresaEmpleadoSie> tbDetEmpresaEmpleados;
	
    public EmpresaSie() {
    }

	public Integer getIdempresa() {
		return this.idempresa;
	}

	public void setIdempresa(Integer idempresa) {
		this.idempresa = idempresa;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumruc() {
		return this.numruc;
	}

	public void setNumruc(String numruc) {
		this.numruc = numruc;
	}

	public String getNumtelefono() {
		return this.numtelefono;
	}

	public void setNumtelefono(String numtelefono) {
		this.numtelefono = numtelefono;
	}

	public String getRazonsocial() {
		return this.razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public Set<ContratoSie> getTbContratos() {
		return this.tbContratos;
	}

	public void setTbContratos(Set<ContratoSie> tbContratos) {
		this.tbContratos = tbContratos;
	}
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
	public Set<MetaEmpresaSie> getTbMetaEmpresas() {
		return this.tbMetaEmpresas;
	}

	public void setTbMetaEmpresas(Set<MetaEmpresaSie> tbMetaEmpresas) {
		this.tbMetaEmpresas = tbMetaEmpresas;
	}

	public Set<KardexSie> getTbKardex() {
		return tbKardex;
	}

	public void setTbKardex(Set<KardexSie> tbKardex) {
		this.tbKardex = tbKardex;
	}

	public Set<DetEmpresaEmpleadoSie> getTbDetEmpresaEmpleados() {
		return tbDetEmpresaEmpleados;
	}

	public void setTbDetEmpresaEmpleados(
			Set<DetEmpresaEmpleadoSie> tbDetEmpresaEmpleados) {
		this.tbDetEmpresaEmpleados = tbDetEmpresaEmpleados;
	}

	public String getTipoVenta() {
		return tipoVenta;
	}

	public void setTipoVenta(String tipoVenta) {
		this.tipoVenta = tipoVenta;
	}
}