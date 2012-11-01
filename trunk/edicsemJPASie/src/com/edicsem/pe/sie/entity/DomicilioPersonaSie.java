package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_domicilio_persona database table.
 * 
 */
@Entity
@Table(name="tb_domicilio_persona", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DomicilioPersonaSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DOMICILIO_PERSONA_IDDOMICILIOPERSONA_GENERATOR", sequenceName="SIE.TB_DOMICILIO_PERSONA_IDDOMICILIOPERSONA_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DOMICILIO_PERSONA_IDDOMICILIOPERSONA_GENERATOR")
	private Integer iddomiciliopersona;

	private String domicilio;
	
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private String referencia;
 
	//bi-directional many-to-one association to ClienteSie
    @ManyToOne
	@JoinColumn(name="idcliente")
	private ClienteSie idcliente;
    
    //bi-directional many-to-one association to EmpleadoSie
    @ManyToOne
	@JoinColumn(name="idempleado")
	private EmpleadoSie idempleado;
    
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
    
    @Transient
	private String desUbigeo;
    
    @Transient
    private int item;

    public DomicilioPersonaSie() {
    }

    /**
	 * @return the desUbigeo
	 */
	public String getDesUbigeo() {
		return desUbigeo;
	}

	/**
	 * @param desUbigeo the desUbigeo to set
	 */
	public void setDesUbigeo(String desUbigeo) {
		this.desUbigeo = desUbigeo;
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

	public ClienteSie getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(ClienteSie idcliente) {
		this.idcliente = idcliente;
	}

	public EmpleadoSie getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(EmpleadoSie idempleado) {
		this.idempleado = idempleado;
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
	
	
}