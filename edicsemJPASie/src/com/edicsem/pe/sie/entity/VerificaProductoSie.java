package com.edicsem.pe.sie.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_verifica_producto database table.
 * 
 */
@Entity
@Table(name="tb_verifica_producto")
public class VerificaProductoSie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_VERIFICA_PRODUCTO_IDVERIFICAPRODUCTO_GENERATOR", sequenceName="TB_VERIFICA_PRODUCTO_IDVERIFICAPRODUCTO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_VERIFICA_PRODUCTO_IDVERIFICAPRODUCTO_GENERATOR")
	private Integer idverificaproducto;

	private Integer cantidad;

	private String idproducto;

	//bi-directional many-to-one association to VerificaClienteSie
    @ManyToOne
	@JoinColumn(name="idverificacliente")
	private VerificaClienteSie tbVerificaCliente;

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

	public String getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(String idproducto) {
		this.idproducto = idproducto;
	}

	public VerificaClienteSie getTbVerificaCliente() {
		return this.tbVerificaCliente;
	}

	public void setTbVerificaCliente(VerificaClienteSie tbVerificaCliente) {
		this.tbVerificaCliente = tbVerificaCliente;
	}
	
}