package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the tb_verifica_cliente database table.
 * 
 */
@Entity
@Table(name="tb_verifica_cliente")
public class VerificaClienteSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_VERIFICA_CLIENTE_IDVERIFICACLIENTE_GENERATOR", sequenceName="SIE.TB_VERIFICA_CLIENTE_IDVERIFICACLIENTE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_VERIFICA_CLIENTE_IDVERIFICACLIENTE_GENERATOR")
	private Integer idverificacliente;

	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

    @Temporal( TemporalType.DATE)
	private Date fecnacimiento;

    //bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie tbEmpleado;
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	private Integer idtipodocumentoidentidad;

	private String nombrecliente;

	private String numdocumento;

	private String observaciones;

	//bi-directional many-to-one association to VerificaProductoSie
	@OneToMany(mappedBy="tbVerificaCliente")
	private Set<VerificaProductoSie> tbVerificaProductos;

	//bi-directional many-to-one association to VerificaTelefonoSie
	@OneToMany(mappedBy="tbVerificaCliente")
	private Set<VerificaTelefonoSie> tbVerificaTelefonos;

    public VerificaClienteSie() {
    }

	public Integer getIdverificacliente() {
		return this.idverificacliente;
	}

	public void setIdverificacliente(Integer idverificacliente) {
		this.idverificacliente = idverificacliente;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getFecnacimiento() {
		return this.fecnacimiento;
	}

	public void setFecnacimiento(Date fecnacimiento) {
		this.fecnacimiento = fecnacimiento;
	}

	public Integer getIdtipodocumentoidentidad() {
		return this.idtipodocumentoidentidad;
	}

	public void setIdtipodocumentoidentidad(Integer idtipodocumentoidentidad) {
		this.idtipodocumentoidentidad = idtipodocumentoidentidad;
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

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Set<VerificaProductoSie> getTbVerificaProductos() {
		return this.tbVerificaProductos;
	}

	public void setTbVerificaProductos(Set<VerificaProductoSie> tbVerificaProductos) {
		this.tbVerificaProductos = tbVerificaProductos;
	}
	
	public Set<VerificaTelefonoSie> getTbVerificaTelefonos() {
		return this.tbVerificaTelefonos;
	}

	public void setTbVerificaTelefonos(Set<VerificaTelefonoSie> tbVerificaTelefonos) {
		this.tbVerificaTelefonos = tbVerificaTelefonos;
	}

	public EmpleadoSie getTbEmpleado() {
		return tbEmpleado;
	}

	public void setTbEmpleado(EmpleadoSie tbEmpleado) {
		this.tbEmpleado = tbEmpleado;
	}

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
}