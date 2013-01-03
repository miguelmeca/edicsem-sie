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
 * The persistent class for the tb_det_paquete database table.
 * 
 */
@Entity
@Table(name="tb_det_paquete", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DetPaqueteSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DET_PAQUETE_IDDETPAQUETE_GENERATOR", sequenceName="SIE.TB_DET_PAQUETE_IDDETPAQUETE_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DET_PAQUETE_IDDETPAQUETE_GENERATOR")
	@Column(name="idDetPaquete")
	private Integer idDetPaquete;
	@Column(columnDefinition="DEFAULT LOCALTIMESTAMP", nullable =  false ,insertable =  false )
	private Timestamp fechacreacion;

	private Timestamp fechamodifica;

	private Integer unidproducto;

	private String usuariocreacion;

	private String usuariomodifica;

	//bi-directional many-to-one association to PaqueteSie
    @ManyToOne
	@JoinColumn(name="idpaquete")
	private PaqueteSie tbPaquete;

	//bi-directional many-to-one association to ProductoSie
    @ManyToOne
	@JoinColumn(name="idproducto")
	private ProductoSie tbProducto;
    
    //bi-directional many-to-one association to EstadoGeneralSie
    @ManyToOne
	@JoinColumn(name="idestadogeneral")
	private EstadoGeneralSie tbEstadoGeneral;
    
    @Transient
    private int item;
    
    @Transient
    private String observacion;
    
    private int cantidad;
    

    public DetPaqueteSie() {
    }

	public Integer getIdDetPaquete() {
		return this.idDetPaquete;
	}

	public void setIdDetPaquete(Integer idDetPaquete) {
		this.idDetPaquete = idDetPaquete;
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

	public Integer getUnidproducto() {
		return this.unidproducto;
	}

	public void setUnidproducto(Integer unidproducto) {
		this.unidproducto = unidproducto;
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

	public PaqueteSie getTbPaquete() {
		return this.tbPaquete;
	}

	public void setTbPaquete(PaqueteSie tbPaquete) {
		this.tbPaquete = tbPaquete;
	}
	
	public ProductoSie getTbProducto() {
		return this.tbProducto;
	}

	public void setTbProducto(ProductoSie tbProducto) {
		this.tbProducto = tbProducto;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

	public EstadoGeneralSie getTbEstadoGeneral() {
		return tbEstadoGeneral;
	}

	public void setTbEstadoGeneral(EstadoGeneralSie tbEstadoGeneral) {
		this.tbEstadoGeneral = tbEstadoGeneral;
	}

}