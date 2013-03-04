package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.edicsem.pe.sie.util.constants.Constants;


/**
 * The persistent class for the tb_verifica_producto database table.
 * 
 */
@Entity
@Table(name="tb_verifica_producto", schema = Constants.ESQUEMA_SIE_POSTGRE)
public class VerificaProductoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_VERIFICA_PRODUCTO_IDVERIFICAPRODUCTO_GENERATOR", sequenceName="SIE.TB_VERIFICA_PRODUCTO_IDVERIFICAPRODUCTO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_VERIFICA_PRODUCTO_IDVERIFICAPRODUCTO_GENERATOR")
	private Integer idverificaproducto;

	private Integer cantidad;

	//bi-directional many-to-one association to ProductoSie
    @ManyToOne
	@JoinColumn(name="idproducto")
	private ProductoSie tbProducto;

	//bi-directional many-to-one association to VerificaClienteSie
    @ManyToOne
	@JoinColumn(name="idverificacliente")
	private VerificaClienteSie tbVerificaCliente;
    
    @Transient
    private int item;

    public VerificaProductoSie() {
    }

	public Integer getIdverificaproducto() {
		return this.idverificaproducto;
	}

	public void setIdverificaproducto(Integer idverificaproducto) {
		this.idverificaproducto = idverificaproducto;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public VerificaClienteSie getTbVerificaCliente() {
		return this.tbVerificaCliente;
	}

	public void setTbVerificaCliente(VerificaClienteSie tbVerificaCliente) {
		this.tbVerificaCliente = tbVerificaCliente;
	}

	public ProductoSie getTbProducto() {
		return tbProducto;
	}

	public void setTbProducto(ProductoSie tbProducto) {
		this.tbProducto = tbProducto;
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