package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;
import com.edicsem.pe.sie.util.form.BaseMantenimientoForm;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the tb_domicilio_persona database table.
 * 
 */
@Entity
@Table(name="tb_domicilio_persona", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DomicilioPersonaSie  extends BaseMantenimientoForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DOMICILIO_PERSONA_IDDOMICILIOPERSONA_GENERATOR", sequenceName="SIE.TB_DOMICILIO_PERSONA_IDDOMICILIOPERSONA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DOMICILIO_PERSONA_IDDOMICILIOPERSONA_GENERATOR")
	private Integer iddomiciliopersona;

	private String domicilio;

	private Timestamp fechacreacion;

	private String referencia;

	//bi-directional many-to-one association to ClienteSie
	@OneToMany(mappedBy="tbDomicilioPersona")
	private Set<ClienteSie> tbClientes;

	//bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;

	//bi-directional many-to-one association to TipoCasaSie
    @ManyToOne
	@JoinColumn(name="idtipocasa")
	private TipoCasaSie tbTipoCasa;

	//bi-directional many-to-one association to UbigeoSie
    @ManyToOne
	@JoinColumn(name="idubigeo")
	private UbigeoSie tbUbigeo;

	//bi-directional many-to-one association to EmpleadoSie
	@OneToMany(mappedBy="tbDomicilioPersona")
	private Set<EmpleadoSie> tbEmpleados;

    public DomicilioPersonaSie() {
    }

	public Integer getIddomiciliopersona() {
		return this.iddomiciliopersona;
	}

	public void setIddomiciliopersona(Integer iddomiciliopersona) {
		this.iddomiciliopersona = iddomiciliopersona;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Set<ClienteSie> getTbClientes() {
		return this.tbClientes;
	}

	public void setTbClientes(Set<ClienteSie> tbClientes) {
		this.tbClientes = tbClientes;
	}
	
	public EstadoGeneralSie getTbEstadoGeneral() {
		return this.tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}
	
	public TipoCasaSie getTbTipoCasa() {
		return this.tbTipoCasa;
	}

	public void setTbTipoCasa(TipoCasaSie tbTipoCasa) {
		this.tbTipoCasa = tbTipoCasa;
	}
	
	public UbigeoSie getTbUbigeo() {
		return this.tbUbigeo;
	}

	public void setTbUbigeo(UbigeoSie tbUbigeo) {
		this.tbUbigeo = tbUbigeo;
	}
	
	public Set<EmpleadoSie> getTbEmpleados() {
		return this.tbEmpleados;
	}

	public void setTbEmpleados(Set<EmpleadoSie> tbEmpleados) {
		this.tbEmpleados = tbEmpleados;
	}
	
}