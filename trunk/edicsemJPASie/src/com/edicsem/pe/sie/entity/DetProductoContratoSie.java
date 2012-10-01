package com.edicsem.pe.sie.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.edicsem.pe.sie.util.constants.Constants;

/**
 * The persistent class for the tb_det_paquete database table.
 * 
 */
@Entity
@Table(name="tb_det_producto_contrato", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class DetProductoContratoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_DET_PRODUCTO_CONTRATO_GENERATOR", sequenceName="SIE.TB_DET_PRODUCTO_CONTRATO_IDDETPRODUCTOCONTRATO_SEQ", initialValue=1, allocationSize =1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_DET_PRODUCTO_CONTRATO_GENERATOR")
	@Column(name="iddetproductocontrato")
	private Integer iddetproductocontrato;

	private Integer cantidad;

	//bi-directional many-to-one association to PaqueteSie
    @ManyToOne
	@JoinColumn(name="idcontrato")
	private ContratoSie tbContrato;

	//bi-directional many-to-one association to ProductoSie
    @ManyToOne
	@JoinColumn(name="idproducto")
	private ProductoSie tbProducto;

    public DetProductoContratoSie() {
    }

	public Integer getIddetproductocontrato() {
		return iddetproductocontrato;
	}
	
	public void setIddetproductocontrato(Integer iddetproductocontrato) {
		this.iddetproductocontrato = iddetproductocontrato;
	}
  

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	 
	
	public ProductoSie getTbProducto() {
		return this.tbProducto;
	}

	public void setTbProducto(ProductoSie tbProducto) {
		this.tbProducto = tbProducto;
	}

	public ContratoSie getTbContrato() {
		return tbContrato;
	}

	public void setTbContrato(ContratoSie tbContrato) {
		this.tbContrato = tbContrato;
	}
	
}